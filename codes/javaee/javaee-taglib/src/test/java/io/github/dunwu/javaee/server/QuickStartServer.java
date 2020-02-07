package io.github.dunwu.javaee.server;

import org.eclipse.jetty.server.Server;

/**
 * 快速启动 jetty 服务器，方便测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
public class QuickStartServer {

	// private static int STARTUP_TYPE = JettyFactory.IDE_ECLIPSE;
	private static int STARTUP_TYPE = JettyFactory.IDE_INTELLIJ;

	public static void main(String[] args) throws Exception {
		Server server = JettyFactory.initServer();
		JettyFactory.initWebAppContext(server, STARTUP_TYPE);

		try {
			JettyFactory.startServer(server);

			// 等待用户输入回车重载应用
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadWebAppContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
