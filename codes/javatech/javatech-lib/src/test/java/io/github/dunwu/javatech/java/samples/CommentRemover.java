package io.github.dunwu.javatech.java.samples;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CommentRemover {

    private static final String FILE_PATH =
        "src/test/java/io/github/dunwu/javatech/java/samples/ReversePolishNotation.java";

    public static void main(String[] args) throws Exception {
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        List<Comment> comments = cu.getAllContainedComments();
        List<Comment> unwantedComments = comments
            .stream()
            .filter(p -> !p.getCommentedNode().isPresent() || p.isLineComment())
            .collect(Collectors.toList());
        unwantedComments.forEach(Node::remove);

        System.out.println(cu.toString());
    }

}
