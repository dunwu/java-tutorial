package io.github.dunwu.javaee.server;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * JettyFactory 可以工作在 Eclipse 和 Intellij 中，用来启动 jetty 服务。 Intellij 并不支持jetty，所以要想类似eclipse一样的使用jetty，需要配置webdefault.xml。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@SuppressWarnings("unused")
public class JettyFactory {

	public static final int IDE_ECLIPSE = 0;

	public static final int IDE_INTELLIJ = 1;

	private static final int PORT = 8080;

	private static final String CONTEXT = "/";

	private static final String RESOURCE_BASE_PATH = "src/main/webapp";

	private static final String WEB_XML_PATH = "/WEB-INF/web.xml";

    private static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web", "tiles" };

	private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault.xml";

	public static Server initServer() {
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);
		WebAppContext webAppContext = new WebAppContext();
		Server server = new Server(PORT);
		server.setHandler(webAppContext);
		return server;
	}

	public static void initWebAppContext(Server server, int type) throws Exception {
		System.out.println("[INFO] Application loading");
		WebAppContext webAppContext = (WebAppContext) server.getHandler();
		webAppContext.setContextPath(CONTEXT);
		webAppContext.setResourceBase(getAbsolutePath() + RESOURCE_BASE_PATH);
		webAppContext.setDescriptor(getAbsolutePath() + RESOURCE_BASE_PATH + WEB_XML_PATH);

		if (IDE_INTELLIJ == type) {
			webAppContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
			supportJspAndSetTldJarNames(server, TLD_JAR_NAMES);
		} else {
			webAppContext.setParentLoaderPriority(true);
		}

		System.out.println("[INFO] Application loaded");
	}

	public static String getAbsolutePath() {
		String path = null;
		String folderPath = JettyFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath()
			.substring(1);
		if (folderPath.indexOf("target") > 0) {
			path = folderPath.substring(0, folderPath.indexOf("target"));
		}
		return path;
	}

	public static void supportJspAndSetTldJarNames(Server server, String... jarNames) {
		WebAppContext context = (WebAppContext) server.getHandler();
		// This webapp will use jsps and jstl. We need to enable the
		// AnnotationConfiguration in
		// order to correctly set up the jsp container
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
			.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
			"org.eclipse.jetty.annotations.AnnotationConfiguration");
		// Set the ContainerIncludeJarPattern so that jetty examines these container-path
		// jars for
		// tlds, web-fragments etc.
		// If you omit the jar that contains the jstl .tlds, the jsp engine will scan for
		// them
		// instead.
		ArrayList jarNameExprssions = Lists.newArrayList(".*/[^/]*servlet-api-[^/]*\\.jar$",
			".*/javax.servlet.jsp.jstl-.*\\.jar$", ".*/[^/]*taglibs.*\\.jar$");

		for (String jarName : jarNames) {
			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
		}

		context.setAttribute("org.eclipse.jetty.io.github.dunwu.javaee.server.webapp.ContainerIncludeJarPattern",
			StringUtils.join(jarNameExprssions, '|'));
	}

	public static void reloadWebAppContext(Server server) throws Exception {
		WebAppContext webAppContext = (WebAppContext) server.getHandler();
		System.out.println("[INFO] Application reloading");
		webAppContext.stop();
		WebAppClassLoader classLoader = new WebAppClassLoader(webAppContext);
		classLoader.addClassPath(getAbsolutePath() + "target/classes");
		classLoader.addClassPath(getAbsolutePath() + "target/test-classes");
		webAppContext.setClassLoader(classLoader);
		webAppContext.start();
		System.out.println("[INFO] Application reloaded");
	}

	public static void startServer(Server server) throws Exception {
		System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");
		server.start();
		System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
		System.out.println("[HINT] Hit Enter to reload the application quickly");
	}

}
