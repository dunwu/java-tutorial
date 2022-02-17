package io.github.dunwu.javatech.zk.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * ZooKeeper 设置数据示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-07-12
 */
public class ZooKeeperSetData {

    private static final String HOST = "localhost";

    private static ZooKeeper zk;

    private static ZooKeeperConnection conn;

    public static void main(String[] args) throws InterruptedException {
        String path = "/MyFirstZnode";
        byte[] data = "Success".getBytes(); // Assign data which is to be updated.

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect(HOST);
            update(path, data); // Update znode data to the specified path
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Method to update the data in a znode. Similar to getData but without watcher.
    public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.setData(path, data, zk.exists(path, true).getVersion());
    }

}
