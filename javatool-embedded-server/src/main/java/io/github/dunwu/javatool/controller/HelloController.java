package io.github.dunwu.javatool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc 的第一个程序
 *
 * @author Zhang Peng
 * @since 2016.07.29
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * 在本例中，Spring将会将数据传给 hello.jsp
     * <p>
     * 访问形式：http://localhost:8080/hello?name=张三
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public ModelAndView hello(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "你好，" + name);
        mav.setViewName("hello");
        return mav;
    }

    /**
     * <p>
     * 测试 logback 分级日志。配置项见src/main/resouces/logback.xml
     * <p>
     * 访问形式：http://localhost:8080/log
     */
    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log() {
        String msg = "print log, current level: {}";
        log.trace(msg, "trace");
        log.debug(msg, "debug");
        log.info(msg, "info");
        log.warn(msg, "warn");
        log.error(msg, "error");
        return msg;
    }

}
