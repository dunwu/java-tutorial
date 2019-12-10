package io.github.dunwu.javalib.mvel;

public class RuleEngineBuilder {

    private RuleEngineParams params;

    private RuleEngineBuilder() {
        params = new RuleEngineParams(RuleConstant.DEFAULT_ENGINE_NAME, false, false, false,
            RuleConstant.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
    }

    public static RuleEngineBuilder newRuleEngine() {
        return new RuleEngineBuilder();
    }

    public RuleEngine build() {
        return new DefaultRuleEngine(params);
    }

    public RuleEngineBuilder named(final String name) {
        params.setName(name);
        return this;
    }

    public RuleEngineBuilder withSkipOnFirstAppliedRule(final boolean skipOnFirstAppliedRule) {
        params.setSkipOnFirstAppliedRule(skipOnFirstAppliedRule);
        return this;
    }

    public RuleEngineBuilder withSkipOnFirstFailedRule(final boolean skipOnFirstFailedRule) {
        params.setSkipOnFirstFailedRule(skipOnFirstFailedRule);
        return this;
    }

    public RuleEngineBuilder withRulePriorityThreshold(final int priorityThreshold) {
        params.setPriorityThreshold(priorityThreshold);
        return this;
    }

    public RuleEngineBuilder withSilentMode(final boolean silentMode) {
        params.setSilentMode(silentMode);
        return this;
    }

}
