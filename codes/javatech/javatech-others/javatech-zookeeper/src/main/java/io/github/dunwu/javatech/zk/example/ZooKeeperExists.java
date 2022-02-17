package io.github.dunwu.javatech.zk.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ZooKeeper 判断 Znode 是否存在示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-07-12
 */
public class ZooKeeperExists {

    private static final String HOST = "localhost";

    private static ZooKeeper zk;

    private static ZooKeeperConnection conn;

    public static void main(String[] args) throws InterruptedException, KeeperException {
        String path = "/MyFirstZnode"; // Assign znode to the specified path

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect(HOST);
            Stat stat = znodeExists(path); // Stat checks the path of the znode

            if (stat != null) {
                System.out.println("Node exists and the node version is " + stat.getVersion());
            } else {
                System.out.println("Node does not exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Catches error messages
        }
    }

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znodeExists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

}
