package io.github.dunwu.javalib.json.gson;

import com.google.gson.annotations.Since;

public class VersionedClass {

    @Since(1.1)
    private final String newerField;

    @Since(1.0)
    private final String newField;

    private final String field;

    public VersionedClass() {
        this.newerField = "newer";
        this.newField = "new";
        this.field = "old";
    }

}
