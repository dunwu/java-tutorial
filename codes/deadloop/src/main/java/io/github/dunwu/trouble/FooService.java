package io.github.dunwu.trouble;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FooService {

    List<String> data = new ArrayList<>();

    public void oom() {
        data.add(IntStream.rangeClosed(1, 100000)
            .mapToObj(__ -> "a")
            .collect(Collectors.joining("")));
    }

}
