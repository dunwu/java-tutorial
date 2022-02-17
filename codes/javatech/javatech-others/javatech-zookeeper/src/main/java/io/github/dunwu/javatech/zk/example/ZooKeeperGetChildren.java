package io.github.dunwu.javatech.zk.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * ZooKeeper 获取 znode 的所有子节点示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-07-12
 */
public class ZooKeeperGetChildren {

    private static final String HOST = "localhost";

    private static ZooKeeper zk;

    private static ZooKeeperConnection conn;

    public static void main(String[] args) throws InterruptedException, KeeperException {
        String path = "/MyFirstZnode"; // Assign path to the znode

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect(HOST);
            Stat stat = znode_exists(path); // Stat checks the path

            if (stat != null) {
                // getChildren method - get all the children of znode.It has two args,
                // path and watch
                List<String> children = zk.getChildren(path, false);
                for (int i = 0; i < children.size(); i++) {
                    System.out.println(children.get(i)); // Print children's
                }
            } else {
                System.out.println("Node does not exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

}
