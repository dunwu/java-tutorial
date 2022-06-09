package io.github.dunwu.javatech.java.samples;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommentGenerator {

    private static final String FILE_PATH =
        "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";

    private static final Pattern FIND_UPPERCASE = Pattern.compile("(.)(\\p{Upper})");

    public static void main(String[] args) throws Exception {

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        List<MethodDeclaration> methodDeclarations = new ArrayList<>();
        VoidVisitorAdapter<List<MethodDeclaration>> unDocumentedMethodCollector = new UnDocumentedMethodCollector();
        unDocumentedMethodCollector.visit(cu, methodDeclarations);

        cu.findAll(MethodDeclaration.class).stream()
          .filter(md -> !md.getJavadoc().isPresent())
          .forEach(md -> md.setJavadocComment(generateJavaDoc(md)));

        System.out.println(cu.toString());
    }

    private static class UnDocumentedMethodCollector extends VoidVisitorAdapter<List<MethodDeclaration>> {

        @Override
        public void visit(MethodDeclaration md, List<MethodDeclaration> collector) {
            super.visit(md, collector);
            // value == null
            if (!md.getJavadoc().isPresent()) {
                collector.add(md);
            }
        }

    }

    private static String generateJavaDoc(MethodDeclaration md) {
        return " " + camelCaseToTitleFormat(md.getNameAsString()) + " ";
    }

    private static String camelCaseToTitleFormat(String text) {
        String split = FIND_UPPERCASE.matcher(text).replaceAll("$1 $2");
        return split.substring(0, 1).toUpperCase() + split.substring(1);
    }

}
