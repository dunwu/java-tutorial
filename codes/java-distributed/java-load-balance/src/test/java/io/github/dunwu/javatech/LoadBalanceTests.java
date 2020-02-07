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

    /**
     * 生成 100 个样本节点，权重值为 10 以内的随机数
     */
    private List<Node> initNodes() {
        Random random = new Random();
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Node node = new Node("192.168.0." + i, random.nextInt(10));
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 统计负载均衡命中次数，样本数为 10000 次访问
     */
    private Map<Node, Long> staticLoadBalance(LoadBalance<Node> algorithm) {
        Map<Node, Long> staticMap = new TreeMap<>();

        for (int i = 0; i < 10000; i++) {
            Node node = algorithm.next();
            // System.out.printf(">>>> url = %s\n", node.url);
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
    public void randomLoadBalanceDistribution() {
        List<Node> nodes = initNodes();

        LoadBalance<Node> loadBalance = new RandomLoadBalance<>();
        loadBalance.buildInList(nodes);
        System.out.println("======================= 随机负载均衡 =======================");
        staticLoadBalance(loadBalance);

        LoadBalance<Node> loadBalance2 = new RandomLoadBalance<>(true);
        loadBalance2.buildInList(nodes);
        System.out.println("======================= 加权随机负载均衡 =======================");
        staticLoadBalance(loadBalance2);
    }

    @Test
    public void randomLoadBalanceUpdateNodes() {
        List<Node> oldNodes = initNodes();
        List<Node> newNodes = oldNodes.subList(0, 80);

        LoadBalance<Node> oldLoadBalance = new RandomLoadBalance<>();
        oldLoadBalance.buildInList(oldNodes);
        LoadBalance<Node> newLoadBalance = new RandomLoadBalance<>();
        newLoadBalance.buildInList(newNodes);

        double count = 0.0d;
        int size = newNodes.size();
        for (int i = 0; i < newNodes.size(); i++) {
            Node oldNode = oldLoadBalance.next();
            Node newNode = newLoadBalance.next();
            if (oldNode.equals(newNode)) count++;
        }
        System.out.println(count / size);
    }

    @Test
    public void roundRobinLoadBalanceDistribution() {
        List<Node> nodes = initNodes();

        LoadBalance<Node> loadBalance = new RoundRobinLoadBalance<>();
        loadBalance.buildInList(nodes);
        System.out.println("======================= 轮询负载均衡 =======================");
        staticLoadBalance(loadBalance);

        LoadBalance<Node> loadBalance2 = new RoundRobinLoadBalance<>(true);
        loadBalance2.buildInList(nodes);
        System.out.println("======================= 加权轮询负载均衡 =======================");
        staticLoadBalance(loadBalance2);
    }

    @Test
    public void roundRobinLoadBalanceUpdateNodes() {
        List<Node> oldNodes = initNodes();
        List<Node> newNodes = oldNodes.subList(0, 80);

        LoadBalance<Node> oldLoadBalance = new RoundRobinLoadBalance<>();
        oldLoadBalance.buildInList(oldNodes);
        LoadBalance<Node> newLoadBalance = new RoundRobinLoadBalance<>();
        newLoadBalance.buildInList(newNodes);

        double count = 0.0d;
        int size = newNodes.size();
        for (int i = 0; i < newNodes.size(); i++) {
            Node oldNode = oldLoadBalance.next();
            Node newNode = newLoadBalance.next();
            if (oldNode.equals(newNode)) count++;
        }
        System.out.println(count / size);
    }

    @Test
    public void consistentHashLoadBalanceDistribution() {
        LoadBalance<Node> loadBalance = new ConsistentHashLoadBalance<>();
        loadBalance.buildInList(initNodes());
        System.out.println("======================= 一致性 Hash 负载均衡 =======================");
        staticLoadBalance(loadBalance);
    }

    /**
     * 测试节点新增删除后的变化程度
     */
    @Test
    public void testNodeAddAndRemove() {
        // 构造 10000 随机请求
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            keys.add(UUID.randomUUID().toString());
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Node node = new Node("192.168.0." + i);
            nodes.add(node);
        }

        List<Node> newNodes = nodes.subList(0, 80);
        ConsistentHashLoadBalance<Node> oldLoadBalance = new ConsistentHashLoadBalance<>();
        oldLoadBalance.buildInList(nodes);
        ConsistentHashLoadBalance<Node> newLoadBalance = new ConsistentHashLoadBalance<>();
        newLoadBalance.buildInList(newNodes);

        int count = 0;
        for (String key : keys) {
            Node oldNode = oldLoadBalance.next(key);
            Node newNode = newLoadBalance.next(key);
            if (oldNode.equals(newNode)) count++;
        }
        System.out.println(count / 10000D);
    }

}
