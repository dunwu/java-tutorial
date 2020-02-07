package io.github.dunwu.javaee.listener;

import io.github.dunwu.javaee.listener.bean.PersonInfo;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单点登录处理类
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class LoginSessionListener implements HttpSessionAttributeListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();

		// 登录
		if (name.equals("personInfo")) {

			PersonInfo personInfo = (PersonInfo) event.getValue();

			if (map.get(personInfo.getAccount()) != null) {

				// map 中有记录，表明该帐号在其他机器上登录过，将以前的登录失效
				HttpSession session = map.get(personInfo.getAccount());
				PersonInfo oldPersonInfo = (PersonInfo) session.getAttribute("personInfo");

				logger.debug("帐号" + oldPersonInfo.getAccount() + "在" + oldPersonInfo.getIp() + "已经登录，该登录将被迫下线。");

				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线。");
			}

			// 将session以用户名为索引，放入map中
			map.put(personInfo.getAccount(), event.getSession());
			logger.debug("帐号" + personInfo.getAccount() + "在" + personInfo.getIp() + "登录。");
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();

		// 注销
		if (name.equals("personInfo")) {
			// 将该session从map中移除
			PersonInfo personInfo = (PersonInfo) event.getValue();
			map.remove(personInfo.getAccount());
			logger.debug("帐号" + personInfo.getAccount() + "注销。");
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String name = event.getName();

		// 没有注销的情况下，用另一个帐号登录
		if (name.equals("personInfo")) {

			// 移除旧的的登录信息
			PersonInfo oldPersonInfo = (PersonInfo) event.getValue();
			map.remove(oldPersonInfo.getAccount());

			// 新的登录信息
			PersonInfo personInfo = (PersonInfo) event.getSession().getAttribute("personInfo");

			// 也要检查新登录的帐号是否在别的机器上登录过
			if (map.get(personInfo.getAccount()) != null) {
				// map 中有记录，表明该帐号在其他机器上登录过，将以前的登录失效
				HttpSession session = map.get(personInfo.getAccount());
				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线。");
			}
			map.put("personInfo", event.getSession());
		}
	}

}
