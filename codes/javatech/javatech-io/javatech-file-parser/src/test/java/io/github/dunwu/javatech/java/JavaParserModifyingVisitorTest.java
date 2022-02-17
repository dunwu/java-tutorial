package io.github.dunwu.javatech.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

/**
 * JavaParser 美化打印测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-07
 */
public class JavaParserModifyingVisitorTest {

    private static final String FILE_PATH =
        "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";
    private static final Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");

    @Test
    @DisplayName("JavaParser VoidVisitor 测试 1")
    public void voidVisitorStarter() throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));
        System.out.println(cu.toString());
    }

    @Test
    @DisplayName("JavaParser VoidVisitor 测试 2")
    public void prettyPrintComplete() throws FileNotFoundException {

        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));

        ModifierVisitor<?> numericLiteralVisitor = new IntegerLiteralModifier();
        numericLiteralVisitor.visit(cu, null);

        System.out.println(cu.toString());
    }

    private static class IntegerLiteralModifier extends ModifierVisitor<Void> {

        @Override
        public FieldDeclaration visit(FieldDeclaration fd, Void arg) {
            super.visit(fd, arg);
            fd.getVariables().forEach(v ->
                v.getInitializer().ifPresent(i ->
                    i.ifIntegerLiteralExpr(il ->
                        v.setInitializer(formatWithUnderscores(il.getValue()))
                    )
                )
            );
            return fd;
        }

    }

    static String formatWithUnderscores(String value) {
        String withoutUnderscores = value.replaceAll("_", "");
        return LOOK_AHEAD_THREE.matcher(withoutUnderscores).replaceAll("$1_");
    }

}
