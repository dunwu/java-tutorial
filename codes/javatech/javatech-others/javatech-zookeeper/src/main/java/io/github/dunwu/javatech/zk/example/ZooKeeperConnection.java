package io.github.dunwu.javatech.zk.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper 连接示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-07-12
 */
public class ZooKeeperConnection {

    private static final String HOST = "localhost";

    final CountDownLatch connectedSignal = new CountDownLatch(1);

    // declare zookeeper instance to access ZooKeeper ensemble
    private ZooKeeper zoo;

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeperConnection zooKeeperConnection = new ZooKeeperConnection();
        ZooKeeper zk = zooKeeperConnection.connect(HOST);
        States state = zk.getState();
        System.out.println("ZooKeeper isAlive:" + state.isAlive());
        zk.close();
    }

    // Method to connect zookeeper ensemble.
    public ZooKeeper connect(String host) throws IOException, InterruptedException {

        zoo = new ZooKeeper(host, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent we) {
                if (we.getState() == KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }

}
