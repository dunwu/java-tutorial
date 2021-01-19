package io.github.dunwu.javatech;

import cn.hutool.core.util.StrUtil;

import java.util.*;

/**
 * @author peng.zhang
 * @date 2021/1/19
 */
public class LoadBalanceDemo {

    private static final Random random = new Random();

    /**
     * 生成 10 个不一样的 IP 地址
     */
    private static List<String> init10IpList() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add("127.0.0." + i);
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
    private static Map<Node, Long> loadBalance10000(LoadBalance<Node> algorithm, List<Node> nodes) {
        Map<Node, Long> staticMap = new TreeMap<>();

        List<String> ipList = init10IpList();
        int ipLength = ipList.size();
        for (int i = 0; i < 10000; i++) {
            String ip = ipList.get(random.nextInt(ipLength));
            Node node = algorithm.select(nodes, ip);
            System.out.println(StrUtil.format("ip = {}, node url = {}", ip, node.getUrl()));
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
        List<Node> nodes = initNodeList(100, false, false);

        // System.out.println("======================= 随机负载均衡 =======================");
        // loadBalance10000(new RandomLoadBalance<>(), nodes);
        //
        // System.out.println("======================= 加权随机负载均衡 =======================");
        // loadBalance10000(new WeightRandomLoadBalance<>(), nodes);
        //
        // System.out.println("======================= 轮询负载均衡 =======================");
        // loadBalance10000(new RoundRobinLoadBalance<>(), nodes);
        //
        // System.out.println("======================= 加权轮询负载均衡 =======================");
        // loadBalance10000(new WeightRoundRobinLoadBalance<>(), nodes);
        //
        // System.out.println("======================= 源地址哈希负载均衡 =======================");
        // loadBalance10000(new IpHashLoadBalance<>(), nodes);
        //
        // System.out.println("======================= 最小活跃数负载均衡 =======================");
        // loadBalance10000(new LeastActiveLoadBalance<>(), nodes);

        System.out.println("======================= 一致性哈希负载均衡 =======================");
        loadBalance10000(new ConsistentHashLoadBalance<>(), nodes);
    }

}
