package io.github.dunwu.javaee.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.*;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class PersonInfoListener implements HttpSessionActivationListener, HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = -4780592776386225973L;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String name;

    private Date dateCreated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    // 从硬盘加载后
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info(this + "已经成功从硬盘中加载。sessionId: " + session.getId());
    }

    // 即将被钝化到硬盘时
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info(this + "即将保存到硬盘。sessionId: " + session.getId());
    }

    // 被放进session前
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        String name = event.getName();
        logger.info(this + "被绑定到session \"" + session.getId() + "\"的" + name + "属性上");

        // 记录放到session中的时间
        this.setDateCreated(new Date());
    }

    // 从session中移除后
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        String name = event.getName();
        logger.info(this + "被从session \"" + session.getId() + "\"的" + name + "属性上移除");
    }

    @Override
    public String toString() {
        return "PersonInfoListener(" + name + ")";
    }

}
