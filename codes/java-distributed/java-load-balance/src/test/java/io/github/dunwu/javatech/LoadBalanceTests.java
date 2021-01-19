package io.github.dunwu.javatech;

import org.junit.Test;

import java.util.*;

/**
 * 负载均衡测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-20
 */
public class LoadBalanceTests {

    private Random random = new Random();

    /**
     * 生成 10 个不一样的 IP 地址
     */
    private List<String> init10IpList() {
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
    private List<Node> initNodeList(Integer num, boolean sameWeight, boolean sameActive) {

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
    private Map<Node, Long> loadBalance10000(LoadBalance<Node> algorithm, List<Node> nodes) {
        Map<Node, Long> staticMap = new TreeMap<>();

        List<String> ipList = init10IpList();
        int ipLength = ipList.size();
        for (int i = 0; i < 10000; i++) {
            String ip = ipList.get(random.nextInt(ipLength));
            Node node = algorithm.select(nodes, ip);
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

    @Test
    public void randomLoadBalanceTest() {
        System.out.println("======================= 随机负载均衡 =======================");
        LoadBalance<Node> loadBalance = new RandomLoadBalance<>();
        List<Node> nodes = initNodeList(100, false, false);
        loadBalance10000(loadBalance, nodes);
    }

    @Test
    public void weightRandomLoadBalanceTest() {
        System.out.println("======================= 加权随机负载均衡 =======================");
        LoadBalance<Node> loadBalance = new WeightRandomLoadBalance<>();
        List<Node> nodes = initNodeList(100, true, false);
        loadBalance10000(loadBalance, nodes);
    }

    @Test
    public void roundRobinLoadBalanceTest() {
        System.out.println("======================= 轮询负载均衡 =======================");
        LoadBalance<Node> loadBalance = new RoundRobinLoadBalance<>();
        List<Node> nodes = initNodeList(100, false, false);
        loadBalance10000(loadBalance, nodes);
    }

    @Test
    public void weightRoundRobinLoadBalanceTest() {
        System.out.println("======================= 加权轮询负载均衡 =======================");
        LoadBalance<Node> loadBalance = new WeightRoundRobinLoadBalance<>();
        List<Node> nodes = initNodeList(100, false, false);
        loadBalance10000(loadBalance, nodes);
    }

    @Test
    public void ipHashLoadBalanceTest() {
        System.out.println("======================= 源地址哈希负载均衡 =======================");
        LoadBalance<Node> loadBalance = new IpHashLoadBalance<>();
        List<Node> nodes = initNodeList(100, false, false);
        loadBalance10000(loadBalance, nodes);
    }
    //
    // @Test
    // public void roundRobinLoadBalanceUpdateNodes() {
    //     List<Node> oldNodes = initNodes();
    //     List<Node> newNodes = oldNodes.subList(0, 80);
    //
    //     LoadBalance<Node> oldLoadBalance = new RoundRobinLoadBalance<>();
    //     oldLoadBalance.buildNodes(oldNodes);
    //     LoadBalance<Node> newLoadBalance = new RoundRobinLoadBalance<>();
    //     newLoadBalance.buildNodes(newNodes);
    //
    //     double count = 0.0d;
    //     int size = newNodes.size();
    //     for (int i = 0; i < newNodes.size(); i++) {
    //         Node oldNode = oldLoadBalance.select();
    //         Node newNode = newLoadBalance.select();
    //         if (oldNode.equals(newNode)) count++;
    //     }
    //     System.out.println(count / size);
    // }

    // @Test
    // public void consistentHashLoadBalanceTest() {
    //     System.out.println("======================= 一致性 Hash 负载均衡 =======================");
    //     LoadBalance<Node> loadBalance = new ConsistentHashLoadBalance<>();
    //     List<Node> nodes = initNodes(100, true);
    //     staticLoadBalance(loadBalance, nodes);
    // }
    //
    // @Test
    // public void leastActiveLoadBalanceTest() {
    //     LoadBalance<Node> loadBalance = new LeastActiveLoadBalance<>();
    //     List<Node> nodes = initNodes(100, false);
    //     staticLoadBalance(loadBalance, nodes);
    // }
    //
    // @Test
    // public void leastActiveLoadBalanceUpdateNodesTest() {
    //     List<Node> oldNodes = initNodes(100, true);
    //     List<Node> newNodes = oldNodes.subList(0, 80);
    //
    //     LoadBalance<Node> loadBalance = new LeastActiveLoadBalance<>();
    //
    //     double count = 0.0d;
    //     int size = newNodes.size();
    //     for (int i = 0; i < newNodes.size(); i++) {
    //         Node oldNode = loadBalance.select(oldNodes);
    //         Node newNode = loadBalance.select(newNodes);
    //         if (oldNode.equals(newNode)) count++;
    //     }
    //     System.out.println(count / size);
    // }

    /**
     * 测试节点新增删除后的变化程度
     */
    // @Test
    // public void testNodeAddAndRemove() {
    //     // 构造 10000 随机请求
    //     List<String> keys = new ArrayList<>();
    //     for (int i = 0; i < 10000; i++) {
    //         keys.add(UUID.randomUUID().toString());
    //     }
    //
    //     List<Node> nodes = new ArrayList<>();
    //     for (int i = 1; i <= 100; i++) {
    //         Node node = new Node("192.168.0." + i);
    //         nodes.add(node);
    //     }
    //
    //     List<Node> newNodes = nodes.subList(0, 80);
    //     ConsistentHashLoadBalance<Node> oldLoadBalance = new ConsistentHashLoadBalance<>();
    //     oldLoadBalance.buildNodes(nodes);
    //     ConsistentHashLoadBalance<Node> newLoadBalance = new ConsistentHashLoadBalance<>();
    //     newLoadBalance.buildNodes(newNodes);
    //
    //     int count = 0;
    //     for (String key : keys) {
    //         Node oldNode = oldLoadBalance.next(key);
    //         Node newNode = newLoadBalance.next(key);
    //         if (oldNode.equals(newNode)) count++;
    //     }
    //     System.out.println(count / 10000D);
    // }

    // @Test
    // public void randomLoadBalanceUpdateNodesTest() {
    //     List<Node> oldNodes = initNodes(100, true);
    //     List<Node> newNodes = oldNodes.subList(0, 80);
    //
    //     LoadBalance<Node> loadBalance = new RandomLoadBalance<>();
    //
    //     double count = 0.0d;
    //     int size = newNodes.size();
    //     for (int i = 0; i < newNodes.size(); i++) {
    //         Node oldNode = loadBalance.select(oldNodes);
    //         Node newNode = loadBalance.select(newNodes);
    //         if (oldNode.equals(newNode)) count++;
    //     }
    //     System.out.println(count / size);
    // }
}
