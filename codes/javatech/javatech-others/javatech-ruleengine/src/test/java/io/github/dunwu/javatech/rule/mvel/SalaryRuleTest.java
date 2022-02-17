package io.github.dunwu.javatech.rule.mvel;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SalaryRuleTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String SALARY_RULE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\SalaryRule.json";

    private RuleEngine ruleEngine;

    @BeforeEach
    public void before() {
        logger.info("Begin");
        RuleEngineParams params = new RuleEngineParams("SalaryEngine", true, false, true,
            RuleConstant.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
        ruleEngine = new DefaultRuleEngine(params);

        String json = FileUtil.readString(new File(SALARY_RULE_PATH), "utf-8");
        MvelRuleSet ruleSet = JSON.parseObject(json, MvelRuleSet.class);
        ruleEngine.registerRule(ruleSet);
    }

    @Test
    public void test_salaryRule() {
        RuleContext ruleContext = new RuleContext();
        ruleContext.put("fee", 0.0);

        ruleContext.put("salary", 1000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(0, ruleContext.get("fee"));

        ruleContext.put("salary", 4000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(15.0, ruleContext.get("fee"));

        ruleContext.put("salary", 7000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(245.0, ruleContext.get("fee"));

        ruleContext.put("salary", 10000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(745.0, ruleContext.get("fee"));

        ruleContext.put("salary", 18000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(2620.0, ruleContext.get("fee"));

        ruleContext.put("salary", 40005);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(8196.50, ruleContext.get("fee"));

        ruleContext.put("salary", 70005);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(17771.75, ruleContext.get("fee"));

        ruleContext.put("salary", 100000);
        ruleEngine.launch(ruleContext);
        Assertions.assertEquals(29920.00, ruleContext.get("fee"));
    }

    @AfterEach
    public void after() {
        logger.info("End");
    }

}
