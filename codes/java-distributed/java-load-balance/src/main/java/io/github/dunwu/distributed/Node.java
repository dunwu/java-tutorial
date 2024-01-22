package io.github.dunwu.distributed;

import java.util.Objects;

/**
 * 负载均衡节点
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-23
 */
public class Node implements Comparable<Node> {

    public static final Integer DEFAULT_WEIGHT = 1;
    public static final Integer DEFAULT_ACTIVE = 0;

    protected String url;

    protected Integer weight;

    protected Integer active;

    public Node(String url) {
        this(url, DEFAULT_WEIGHT, DEFAULT_ACTIVE);
    }

    public Node(String url, Integer weight, Integer active) {
        this.url = url;
        this.weight = weight;
        this.active = active;
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
            ", active=" + active +
            '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

}
