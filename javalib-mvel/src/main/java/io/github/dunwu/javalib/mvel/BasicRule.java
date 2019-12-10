package io.github.dunwu.javalib.mvel;

public class BasicRule implements Rule, Comparable<Rule> {

    protected String name;

    private String description;

    private int priority;

    private String condition;

    private String action;

    public BasicRule() {
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicRule basicRule = (BasicRule) o;

        if (priority != basicRule.priority) {
            return false;
        }
        if (!name.equals(basicRule.name)) {
            return false;
        }
        return !(description != null ? !description.equals(basicRule.description) : basicRule.description != null);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Rule rule) {
        if (priority < rule.getPriority()) {
            return -1;
        } else if (priority > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public boolean validate() {
        if (condition == null || condition.length() == 0) {
            throw new IllegalArgumentException("The rule condition must not be null or empty");
        }
        if (action == null || action.length() == 0) {
            throw new IllegalArgumentException("The rule action must not be null or empty");
        }
        return true;
    }

    @Override
    public boolean evaluate(RuleContext ruleContext) {
        return false;
    }

    @Override
    public void execute(RuleContext ruleContext) {
        // do nothing
    }

    public void setAction(String action) {
        this.action = action;
    }

}
