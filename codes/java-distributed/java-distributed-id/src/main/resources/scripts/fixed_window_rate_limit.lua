-- 缓存 Key
local key = KEYS[1]
-- 访问请求数
local permits = tonumber(ARGV[1])
-- 过期时间
local seconds = tonumber(ARGV[2])
-- 限流阈值
local limit = tonumber(ARGV[3])

-- 获取统计值
local count = tonumber(redis.call('GET', key) or "0")

if count + permits > limit then
    -- 请求拒绝
    return -1
else
    -- 请求通过
    redis.call('INCRBY', key, permits)
    redis.call('EXPIRE', key, seconds)
    return count + permits
end