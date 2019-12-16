package io.github.dunwu.javalib.mvel;

public class RuleEngineParams {

    /**
     * The engine name.
     */
    protected String name;

    /**
     * 满足任意条件（即遇到第一个匹配规则时停止） Parameter to skip next applicable rules when a rule is applied.
     */
    private boolean skipOnFirstAppliedRule;

    /**
     * 满足所有条件（即遇到第第一个未匹配规则时停止） Parameter to skip next applicable rules when a rule has failed.
     */
    private boolean skipOnFirstUnAppliedRule;

    /**
     * Parameter to skip next applicable rules when a rule has failed.
     */
    private boolean skipOnFirstFailedRule;

    /**
     * Parameter to skip next rules if priority exceeds a user defined threshold.
     */
    private int priorityThreshold;

    /**
     * Parameter to mute loggers.
     */
    private boolean silentMode;

    public RuleEngineParams(String name, boolean skipOnFirstAppliedRule, boolean skipOnFirstUnAppliedRule,
        boolean skipOnFirstFailedRule, int priorityThreshold, boolean silentMode) {
        this.name = name;
        this.skipOnFirstAppliedRule = skipOnFirstAppliedRule;
        this.skipOnFirstUnAppliedRule = skipOnFirstUnAppliedRule;
        this.skipOnFirstFailedRule = skipOnFirstFailedRule;
        this.priorityThreshold = priorityThreshold;
        this.silentMode = silentMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriorityThreshold() {
        return priorityThreshold;
    }

    public void setPriorityThreshold(int priorityThreshold) {
        this.priorityThreshold = priorityThreshold;
    }

    public boolean isSilentMode() {
        return silentMode;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    public boolean isSkipOnFirstAppliedRule() {
        return skipOnFirstAppliedRule;
    }

    public void setSkipOnFirstAppliedRule(boolean skipOnFirstAppliedRule) {
        this.skipOnFirstAppliedRule = skipOnFirstAppliedRule;
    }

    public boolean isSkipOnFirstFailedRule() {
        return skipOnFirstFailedRule;
    }

    public void setSkipOnFirstFailedRule(boolean skipOnFirstFailedRule) {
        this.skipOnFirstFailedRule = skipOnFirstFailedRule;
    }

    public boolean isSkipOnFirstUnAppliedRule() {
        return skipOnFirstUnAppliedRule;
    }

    public void setSkipOnFirstUnAppliedRule(boolean skipOnFirstUnAppliedRule) {
        this.skipOnFirstUnAppliedRule = skipOnFirstUnAppliedRule;
    }

}
