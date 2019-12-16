package io.github.dunwu.javalib.mvel;

import java.util.Set;
import java.util.TreeSet;

public class MvelRuleSet {

    private String name;

    private TreeSet<MvelRule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() == 0) {
            name = RuleConstant.DEFAULT_RULE_NAME;
        }
        this.name = name;
    }

    public Set<MvelRule> getRules() {
        if (rules == null) {
            rules = new TreeSet<>();
        }
        return rules;
    }

    public void setRules(TreeSet<MvelRule> rules) {
        this.rules = rules;
    }

}
