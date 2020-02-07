package io.github.dunwu.javatech;

import java.util.Objects;

/**
 * 负载均衡节点
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-23
 */
public class Node implements Comparable<Node> {

    public static final Integer DEFAULT_WEIGHT = 1;

    protected String url;

    protected Integer weight;

    public Node(String url) {
        this.url = url;
        this.weight = DEFAULT_WEIGHT;
    }

    public Node(String url, Integer weight) {
        this.url = url;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return url.compareTo(o.url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;
        return url.equals(node.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "Node{" +
            "url='" + url + '\'' +
            ", weight=" + weight +
            '}';
    }

    public String getUrl() {
        return url;
    }

    public Node setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Node setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

}
