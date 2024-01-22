package io.github.dunwu.distributed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 负载均衡算法测试例
 *
 * @author peng.zhang
 * @date 2021/1/19
 */
public class LoadBalanceDemo {

    private static final Random random = new Random();

    public static String randomIpv4() {
        int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
            { 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
            { 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
            { 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
            { 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
            { -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
            { -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
            { -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
            { -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
            { -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0]
            + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    private static String num2ip(final int ip) {
        int[] b = new int[4];
        String result = "";
        b[0] = (ip >> 24) & 0xff;
        b[1] = ((ip >> 16) & 0xff);
        b[2] = ((ip >> 8) & 0xff);
        b[3] = (ip & 0xff);
        result = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "."
            + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
        return result;
    }

    /**
     * 生成 num 个随机 IP 地址
     */
    private static List<String> initRandomIpList(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(randomIpv4());
        }
        return list;
    }

    /**
     * 生成 num 个样本节点
     *
     * @param num        节点数
     * @param sameWeight 各节点权重是否相同
     * @param sameActive 各节点活跃数是否相同
     */
    private static List<Node> initNodeList(Integer num, boolean sameWeight, boolean sameActive) {

        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            Node node = new Node("192.168.0." + i);
            if (!sameWeight) {
                node.setWeight(random.nextInt(10));
            }
            if (!sameActive) {
                node.setActive(random.nextInt(10));
            }
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 统计负载均衡命中次数，样本数为 10000 次访问
     */
    private static Map<Node, Long> loadBalance10000(LoadBalance<Node> algorithm, List<Node> nodes,
        List<String> ipList) {
        Map<Node, Long> staticMap = new TreeMap<>();

        int ipLength = ipList.size();
        for (int i = 0; i < 10000; i++) {
            String ip = ipList.get(random.nextInt(ipLength));
            Node node = algorithm.select(nodes, ip);
            // 打印每一次负载均衡的选择结果
            // System.out.println(StrUtil.format("ip = {}, node url = {}", ip, node.getUrl()));
            if (staticMap.containsKey(node)) {
                Long value = staticMap.get(node);
                staticMap.put(node, ++value);
            } else {
                staticMap.put(node, 1L);
            }
        }

        System.out.println("======================= 统计数据 =======================");
        staticMap.forEach((key, value) -> {
            System.out.printf("key = %s, value = %s\n", key, value);
        });
        System.out.printf("方差：%s, ", StatisticsUtil.variance(staticMap.values().toArray(new Long[0])));
        System.out.printf("标准差：%s\n", StatisticsUtil.standardDeviation(staticMap.values().toArray(new Long[] {})));
        return staticMap;
    }

    public static void main(String[] args) {
        // 构造 100 个候选服务器节点
        List<Node> nodes = initNodeList(100, false, false);
        // 构造 100 个随机IP
        List<String> ipList = initRandomIpList(100);

        // ============================================================================
        // 基于以上构造数据，对每种算法都 负载均衡选择 10000 次，然后统计方差、标准差，查看负载均衡效果。

        System.out.println("======================= 随机负载均衡 =======================");
        loadBalance10000(new RandomLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 加权随机负载均衡 =======================");
        loadBalance10000(new WeightRandomLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 轮询负载均衡 =======================");
        loadBalance10000(new RoundRobinLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 加权轮询负载均衡 =======================");
        loadBalance10000(new WeightRoundRobinLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 源地址哈希负载均衡 =======================");
        loadBalance10000(new IpHashLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 最小活跃数负载均衡 =======================");
        loadBalance10000(new LeastActiveLoadBalance<>(), nodes, ipList);

        System.out.println("======================= 一致性哈希负载均衡 =======================");
        loadBalance10000(new ConsistentHashLoadBalance<>(), nodes, ipList);
    }

}
