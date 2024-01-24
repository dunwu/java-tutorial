local tokenKey = KEYS[1]
local timeKey = KEYS[2]

-- 申请令牌数
local permits = tonumber(ARGV[1])
-- QPS
local qps = tonumber(ARGV[2])
-- 桶的容量
local capacity = tonumber(ARGV[3])
-- 当前时间（单位：毫秒）
local nowMillis = tonumber(ARGV[4])
-- 填满令牌桶所需要的时间
local fillTime = capacity / qps
local ttl = math.min(capacity, math.floor(fillTime * 2))

local currentTokenNum = tonumber(redis.call("GET", tokenKey))
if currentTokenNum == nil then
    currentTokenNum = capacity
end

local endTimeMillis = tonumber(redis.call("GET", timeKey))
if endTimeMillis == nil then
    endTimeMillis = 0
end

local gap = nowMillis - endTimeMillis
local newTokenNum = math.max(0, gap * qps / 1000)
local currentTokenNum = math.min(capacity, currentTokenNum + newTokenNum)

if currentTokenNum < permits then
    -- 请求拒绝
    return -1
else
    -- 请求通过
    local finalTokenNum = currentTokenNum - permits
    redis.call("SETEX", tokenKey, ttl, finalTokenNum)
    redis.call("SETEX", timeKey, ttl, nowMillis)
    return finalTokenNum
end
