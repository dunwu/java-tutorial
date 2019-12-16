package io.github.dunwu.javalib.mvel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRuleEngine implements RuleEngine {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The rules set.
     */
    protected Set<Rule> rules;

    /**
     * The engine parameters
     */
    protected RuleEngineParams params;

    /**
     * The rule fact
     */
    protected RuleContext fact;

    /**
     * ruleSet Map
     */
    private Map<String, TreeSet<Rule>> ruleSetMap = new ConcurrentHashMap<>();

    public DefaultRuleEngine(RuleEngineParams params) {
        this.params = params;
        this.rules = new TreeSet<>();
        if (params.isSilentMode()) {
            // cancle log
        }
    }

    @Override
    public RuleEngineParams getParams() {
        return params;
    }

    @Override
    public void registerRule(Rule rule) {
        // 检查规则
        if (rule.validate()) {
            rules.add(rule);
        }
    }

    @Override
    public void registerRule(MvelRuleSet ruleSet) {
        ruleSet.getRules().forEach(rule -> registerRule(rule));
        logRegisteredRules();
    }

    private void logRegisteredRules() {
        logger.info("Registered rules:");
        for (Rule rule : rules) {
            logger.info("Rule { name = {}, description = {}, priority = {}}", rule.getName(), rule.getDescription(),
                rule.getPriority());
        }
    }

    @Override
    public void unregisterRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public void clearRules() {
        ruleSetMap.clear();
    }

    @Override
    public Set<Rule> getRules() {
        return rules;
    }

    @Override
    public Map<Rule, Boolean> checkRules() {
        logger.info("Checking rules");
        sortRules();
        Map<Rule, Boolean> result = new HashMap<>();
        for (Rule rule : rules) {
            result.put(rule, rule.evaluate(fact));
        }
        return result;
    }

    @Override
    public void launch(RuleContext fact) {
        if (rules.isEmpty()) {
            logger.warn("No rules registered! Nothing to apply");
            return;
        }

        logEngineParams();
        sortRules();
        applyRules(fact);
    }

    private void logEngineParams() {
        logger.info("----- Params -----");
        logger.info("Engine name: {}", params.getName());
        logger.info("Rule priority threshold: {}", params.getPriorityThreshold());
        logger.info("Skip on first applied rule: {}", params.isSkipOnFirstAppliedRule());
        logger.info("Skip on first unapplied rule: {}", params.isSkipOnFirstUnAppliedRule());
        logger.info("Skip on first failed rule: {}", params.isSkipOnFirstFailedRule());
    }

    private void applyRules(RuleContext fact) {
        logger.info("Rules evaluation started");

        for (Rule rule : rules) {
            final String name = rule.getName();
            final int priority = rule.getPriority();

            if (priority > params.getPriorityThreshold()) {
                logger.info(
                    "Rule priority threshold ({}) exceeded at rule {} with priority={}, next rules will be skipped",
                    new Object[] { params.getPriorityThreshold(), name, priority });
                break;
            }

            if (rule.evaluate(fact)) {
                logger.info("Rule [{}] triggered", name);
                try {
                    rule.execute(fact);
                    logger.info("Rule {} performed successfully", name);
                    if (params.isSkipOnFirstAppliedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstAppliedRule is set");
                        break;
                    }
                    if (params.isSkipOnFirstUnAppliedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstUnAppliedRule is set");
                        break;
                    }
                } catch (Exception exception) {
                    logger.error("Rule [{}] performed with error {}", name, exception);

                    if (params.isSkipOnFirstFailedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstFailedRule is set");
                        break;
                    }
                }
            } else {
                logger.info("Rule [{}] has been evaluated to false, it has not been executed", name);
            }
        }
    }

    private void sortRules() {
        rules = new TreeSet<>(rules);
    }

}
