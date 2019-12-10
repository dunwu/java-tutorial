package io.github.dunwu.javalib.mvel;

import org.mvel2.MVEL;

import java.io.Serializable;

public class MvelRule extends BasicRule {

    /**
     * 判断条件是否匹配
     */
    @Override
    public boolean evaluate(RuleContext ruleContext) {
        try {
            return (Boolean) MVEL.eval(getCondition(), ruleContext);
        } catch (Exception e) {
            throw new RuntimeException(String.format("条件[%s]匹配发生异常:", getCondition()), e);
        }
    }

    /**
     * 执行条件匹配后的操作
     */
    @Override
    public void execute(RuleContext ruleContext) {
        try {
            Serializable exp = MVEL.compileExpression(getAction(), ruleContext);
            MVEL.executeExpression(exp, ruleContext);
        } catch (Exception e) {
            throw new RuntimeException(String.format("后续操作[%s]执行发生异常:", getAction()), e);
        }
    }

}
