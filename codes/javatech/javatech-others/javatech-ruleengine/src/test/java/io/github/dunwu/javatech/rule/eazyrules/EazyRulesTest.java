package io.github.dunwu.javatech.rule.eazyrules;

import cn.hutool.core.io.resource.ResourceUtil;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.net.URL;

/**
 * <a href="https://github.com/j-easy/easy-rules">Easy Rules</a> 使用测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-05-15
 */
public class EazyRulesTest {

    @Test
    @DisplayName("测试 easy-rules 注解方式")
    public void testWeatherRule() {
        WeatherRule rule = new WeatherRule();
        Rules rules = new Rules();
        rules.register(rule);
        testRule(rules);
    }

    @Test
    @DisplayName("测试 Fluent API")
    public void testFluentApi() {
        Rule rule = new RuleBuilder()
            .name("weather rule")
            .description("if it rains then take an umbrella")
            .when(facts -> facts.get("rain").equals(true))
            .then(facts -> System.out.println("It rains, take an umbrella!"))
            .build();
        Rules rules = new Rules();
        rules.register(rule);
        testRule(rules);
    }

    @Test
    @DisplayName("测试 MVEL Expression")
    public void testExpression() {
        Rule rule = new MVELRule()
            .name("weather rule")
            .description("if it rains then take an umbrella")
            .when("rain == true")
            .then("System.out.println(\"It rains, take an umbrella!\");");
        Rules rules = new Rules();
        rules.register(rule);
        testRule(rules);
    }

    @Test
    @DisplayName("测试 Rule File")
    public void testRuleFile() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        URL url = ResourceUtil.getResource("weather-rule.yml");
        Rule rule = ruleFactory.createRule(new FileReader(url.getFile()));
        Rules rules = new Rules();
        rules.register(rule);
        testRule(rules);
    }

    public void testRule(Rules rules) {

        // define facts
        Facts facts = new Facts();
        facts.put("rain", true);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }

}
