package io.github.dunwu.javatech.java;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.configuration.Indentation;
import com.github.javaparser.printer.configuration.PrettyPrinterConfiguration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-07
 */
public class JavaParserTest {

    final String FILE_PATH = "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";

    @Test
    @DisplayName("获得 import")
    public void testGetImports() {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(new File(FILE_PATH));
            Assertions.assertThat(CollectionUtil.isEmpty(compilationUnit.getImports()));
            compilationUnit.getImports().forEach(i -> {
                System.out.println(i.getName());
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("获得类型")
    public void testGetTypes() {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(new File(FILE_PATH));
            Assertions.assertThat(CollectionUtil.isEmpty(compilationUnit.getImports()));
            compilationUnit.findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(c -> !c.isInterface() && !c.isAbstract())
                .forEach(c -> {
                    System.out.println(c.getFullyQualifiedName().get());
                    NodeList<ClassOrInterfaceType> eList = c.getExtendedTypes();
                    for (ClassOrInterfaceType e : eList) {
                        System.out.println("\t" + e.asString());
                    }
                });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("获得枚举属性")
    @ParameterizedTest(name = "{0}")
    @CsvSource({ "src/test/java/io/github/dunwu/javatech/java/samples/CodeEnum.java",
        "src/test/java/io/github/dunwu/javatech/java/samples/WebSocketMsgType.java",
        "src/test/java/io/github/dunwu/javatech/java/samples/CaptchaTypeEnum.java",
        "src/test/java/io/github/dunwu/javatech/java/samples/CodeBiEnum.java" })
    void testGetEnums(String path) {
        File file = new File(path);
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(file);
            Assertions.assertThat(CollectionUtil.isEmpty(compilationUnit.getImports()));
            compilationUnit.findAll(EnumDeclaration.class)
                .forEach(c -> {
                    c.getFullyQualifiedName().ifPresent(i -> {
                        System.out.printf("枚举类型：%s, ", i);
                    });
                    c.getJavadocComment().ifPresent(i -> {
                        System.out.printf("枚举类型注释：%s", getFilteredCommentString(i));
                    });
                    System.out.println();

                    c.getEntries().forEach(e -> {
                        System.out.printf("枚举 Entry：%s, ", e.getName());
                        e.getJavadocComment().ifPresent(i -> {
                            System.out.printf("枚举 Entry 注释：%s", getFilteredCommentString(i));
                        });
                        System.out.println();

                        NodeList<Expression> arguments = e.getArguments();
                        if (CollectionUtil.isNotEmpty(arguments)) {
                            for (int i = 0; i < arguments.size(); i++) {
                                System.out.printf("\t参数 %d：%s", i + 1,
                                    arguments.get(i).asLiteralStringValueExpr().getValue());
                            }
                            System.out.println();
                        }
                    });
                });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getFilteredCommentString(JavadocComment comment) {

        if (comment == null) {
            return null;
        }

        List<String> lines = StrUtil.split(comment.getContent(), '\n');
        if (CollectionUtil.isEmpty(lines)) {
            return null;
        }

        String[] finalCommentLines = lines.stream()
            .map(line -> {
                if (StrUtil.isBlank(line)) {
                    return line;
                }
                // 去除所有 html 标签
                line = line.replaceAll("<[^>]*>", "");
                line = line.trim();
                if (line.startsWith("*")) {
                    line = line.substring(1).trim();
                }
                return line;
            })
            .filter(StrUtil::isNotBlank)
            .filter(line -> !line.startsWith("@"))
            .toArray(String[]::new);
        return StrUtil.concat(false, finalCommentLines);
    }

    @Test
    public void testGenerateSimpleClass() {
        ClassOrInterfaceDeclaration myClass = new ClassOrInterfaceDeclaration();
        myClass.setComment(new LineComment("A very cool class!"));
        myClass.setName("MyClass");
        myClass.addField("String", "foo");

        PrettyPrinterConfiguration conf = new PrettyPrinterConfiguration();
        conf.setIndentSize(1);
        conf.setIndentType(Indentation.IndentType.SPACES);
        conf.setPrintComments(false);
        PrettyPrinter prettyPrinter = new PrettyPrinter(conf);
        System.out.println(prettyPrinter.print(myClass));
    }

    @Test
    public void getTypeOfReference() throws FileNotFoundException {
        final String FILE_PATH = "src/test/java/io/github/dunwu/javatech/java/samples/Bar.java";

        TypeSolver typeSolver = new CombinedTypeSolver();

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
            .getConfiguration()
            .setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        cu.findAll(AssignExpr.class).forEach(ae -> {
            ResolvedType resolvedType = ae.calculateResolvedType();
            System.out.println(ae.toString() + " is a: " + resolvedType);
        });
    }

    @Test
    public void resolveMethodCalls() throws FileNotFoundException {
        final String FILE_PATH = "src/test/java/io/github/dunwu/javatech/java/samples/A.java";
        TypeSolver typeSolver = new ReflectionTypeSolver();

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
            .getConfiguration()
            .setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        cu.findAll(MethodCallExpr.class).forEach(mce ->
            System.out.println(mce.resolve().getQualifiedSignature()));
    }

    @Test
    public void usingTypeSolver() {
        TypeSolver typeSolver = new ReflectionTypeSolver();

        showReferenceTypeDeclaration(typeSolver.solveType("java.lang.Object"));
        showReferenceTypeDeclaration(typeSolver.solveType("java.lang.String"));
        showReferenceTypeDeclaration(typeSolver.solveType("java.util.List"));
    }

    private static void showReferenceTypeDeclaration(
        ResolvedReferenceTypeDeclaration resolvedReferenceTypeDeclaration) {

        System.out.println(String.format("== %s ==", resolvedReferenceTypeDeclaration.getQualifiedName()));
        System.out.println(" fields:");
        resolvedReferenceTypeDeclaration.getAllFields()
            .forEach(f -> System.out.println(String.format("    %s %s", f.getType(), f.getName())));
        System.out.println(" methods:");
        resolvedReferenceTypeDeclaration.getAllMethods()
            .forEach(m -> System.out.println(String.format("    %s", m.getQualifiedSignature())));
        System.out.println();
    }

}
