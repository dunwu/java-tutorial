package io.github.dunwu.javalib.mvel;

public interface Rule {

    /**
     * Getter for rule name.
     *
     * @return the rule name
     */
    String getName();

    /**
     * Getter for rule description.
     *
     * @return rule description
     */
    String getDescription();

    /**
     * Getter for rule priority.
     *
     * @return rule priority
     */
    int getPriority();

    /**
     * Getter for the rule condition
     *
     * @return rule condition
     */
    String getCondition();

    /**
     * Getter for the rule action
     *
     * @return rule action
     */
    String getAction();

    /**
     * validate
     *
     * @return boolean
     */
    boolean validate();

    /**
     * Rule conditions abstraction : this method encapsulates the rule's conditions.
     *
     * @return true if the rule should be applied, false else
     */
    boolean evaluate(RuleContext ruleContext);

    /**
     * Rule actions abstraction : this method encapsulates the rule's actions.
     *
     * @throws Exception thrown if an exception occurs during actions performing
     */
    void execute(RuleContext ruleContext) throws Exception;

}
