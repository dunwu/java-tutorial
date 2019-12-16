package io.github.dunwu.javalib.mvel;

import org.junit.Assert;
import org.junit.Test;
import org.mvel2.MVEL;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mike Brock
 */
public class ClassTests {

    private final String dir = "src/test/java/" + getClass().getPackage().getName().replaceAll("\\.", "/");

    @Test
    public void testScript() throws IOException {
        final Object o = MVEL.evalFile(new File(dir + "/demo.mvel"), new HashMap<String, Object>());
    }

    @Test
    public void testEval() {
        String expression = "foobar > 99";
        Map vars = new HashMap();
        vars.put("foobar", new Integer(100));
        Boolean result = (Boolean) MVEL.eval(expression, vars);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCompileExpression() {
        String expression = "foobar > 99";
        Serializable compiled = MVEL.compileExpression(expression);
        Map vars = new HashMap();
        vars.put("foobar", new Integer(100));
        Boolean result = (Boolean) MVEL.executeExpression(compiled, vars);
        Assert.assertEquals(true, result);
    }

}
