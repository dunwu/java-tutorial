package io.github.dunwu.javatech.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-10
 */
public class JavaParserLexicalPreservationTest {

    @Test
    @DisplayName("JavaParser 词汇保存1")
    public void lexicalPreservationStarter() {
        String code = "class A { }";
        CompilationUnit cu = StaticJavaParser.parse(code);
        LexicalPreservingPrinter.setup(cu);
        System.out.println(LexicalPreservingPrinter.print(cu));
    }

    @Test
    @DisplayName("JavaParser 词汇保存2")
    public void lexicalPreservationComplete() {
        String code = "// Hey, this is a comment\n\n\n// Another one\n\nclass A { }";
        CompilationUnit cu = StaticJavaParser.parse(code);
        LexicalPreservingPrinter.setup(cu);

        System.out.println(LexicalPreservingPrinter.print(cu));

        System.out.println("----------------");

        ClassOrInterfaceDeclaration myClass = cu.getClassByName("A").get();
        myClass.setName("MyNewClassName");
        System.out.println(LexicalPreservingPrinter.print(cu));

        System.out.println("----------------");

        myClass = cu.getClassByName("MyNewClassName").get();
        myClass.setName("MyNewClassName");
        myClass.addModifier(Modifier.Keyword.PUBLIC);
        System.out.println(LexicalPreservingPrinter.print(cu));

        System.out.println("----------------");

        myClass = cu.getClassByName("MyNewClassName").get();
        myClass.setName("MyNewClassName");
        myClass.addModifier(Modifier.Keyword.PUBLIC);
        cu.setPackageDeclaration("io.github.dunwu.javatech.java.samples");
        System.out.println(LexicalPreservingPrinter.print(cu));
    }

}
