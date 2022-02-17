package io.github.dunwu.javatech.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaParser 解析java文件中的注释
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-10
 */
public class JavaParserCommentReporterTest {

    private static final String FILE_PATH =
        "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";

    @Test
    @DisplayName("解析java文件中的注释 - 测试一")
    public void commentReporterStarter() throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));
        List<Comment> comments = cu.getAllContainedComments();
        comments.forEach(System.out::println);
    }

    @Test
    @DisplayName("解析java文件中的注释 - 测试二")
    public void commentReporterComplete() throws FileNotFoundException {

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        List<CommentReportEntry> comments = cu.getAllContainedComments()
            .stream()
            .map(p -> new CommentReportEntry(p.getClass().getSimpleName(),
                p.getContent(),
                p.getRange().map(r -> r.begin.line).orElse(-1),
                !p.getCommentedNode().isPresent()))
            .collect(Collectors.toList());

        comments.forEach(System.out::println);
    }

    private static class CommentReportEntry {

        private String type;
        private String text;
        private int lineNumber;
        private boolean isOrphan;

        CommentReportEntry(String type, String text, int lineNumber, boolean isOrphan) {
            this.type = type;
            this.text = text;
            this.lineNumber = lineNumber;
            this.isOrphan = isOrphan;
        }

        @Override
        public String toString() {
            return lineNumber + "|" + type + "|" + isOrphan + "|" + text.replaceAll("\\n", "").trim();
        }

    }

}
