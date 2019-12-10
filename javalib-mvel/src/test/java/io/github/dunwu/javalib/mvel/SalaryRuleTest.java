package io.github.dunwu.javalib.mvel;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SalaryRuleTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String SALARY_RULE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\SalaryRule.json";

    private RuleEngine ruleEngine;

    @Before
    public void before() throws IOException {
        logger.info("Begin");
        RuleEngineParams params = new RuleEngineParams("SalaryEngine", true, false, true,
            RuleConstant.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
        ruleEngine = new DefaultRuleEngine(params);

        String json = FileUtils.readFileToString(new File(SALARY_RULE_PATH), "utf-8");
        MvelRuleSet ruleSet = JSON.parseObject(json, MvelRuleSet.class);
        ruleEngine.registerRule(ruleSet);
    }

    @Test
    public void test_salaryRule() {
        RuleContext ruleContext = new RuleContext();
        ruleContext.put("fee", 0.0);

        ruleContext.put("salary", 1000);
        ruleEngine.launch(ruleContext);
        assertEquals(0, ruleContext.get("fee"));

        ruleContext.put("salary", 4000);
        ruleEngine.launch(ruleContext);
        assertEquals(15.0, ruleContext.get("fee"));

        ruleContext.put("salary", 7000);
        ruleEngine.launch(ruleContext);
        assertEquals(245.0, ruleContext.get("fee"));

        ruleContext.put("salary", 10000);
        ruleEngine.launch(ruleContext);
        assertEquals(745.0, ruleContext.get("fee"));

        ruleContext.put("salary", 18000);
        ruleEngine.launch(ruleContext);
        assertEquals(2620.0, ruleContext.get("fee"));

        ruleContext.put("salary", 40005);
        ruleEngine.launch(ruleContext);
        assertEquals(8196.50, ruleContext.get("fee"));

        ruleContext.put("salary", 70005);
        ruleEngine.launch(ruleContext);
        assertEquals(17771.75, ruleContext.get("fee"));

        ruleContext.put("salary", 100000);
        ruleEngine.launch(ruleContext);
        assertEquals(29920.00, ruleContext.get("fee"));
    }

    @After
    public void after() {
        logger.info("End");
    }

}
