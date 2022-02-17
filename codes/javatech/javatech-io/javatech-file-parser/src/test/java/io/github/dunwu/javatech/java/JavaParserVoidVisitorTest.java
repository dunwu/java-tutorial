package io.github.dunwu.javatech.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaParser 美化打印测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-07
 */
public class JavaParserVoidVisitorTest {

    private static final String FILE_PATH =
        "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";

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

        VoidVisitor<Void> methodNameVisitor = new MethodNamePrinter();
        methodNameVisitor.visit(cu, null);
        List<String> methodNames = new ArrayList<>();
        VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
        methodNameCollector.visit(cu, methodNames);
        methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
    }

    private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {

        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            System.out.println("Method Name Printed: " + md.getName());
        }

    }

    private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(MethodDeclaration md, List<String> collector) {
            super.visit(md, collector);
            collector.add(md.getNameAsString());
        }

    }

}
