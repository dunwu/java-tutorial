package io.github.dunwu.javatech.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.ReflectionsException;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigurationBuilderTest {

    @Test
    public void buildForConfig() {
        assertConfig(ConfigurationBuilder.build("io.github.dunwu.javatech.reflections"),
            ClasspathHelper.forPackage("io.github.dunwu.javatech.reflections"),
            new FilterBuilder().includePackage("io.github.dunwu.javatech.reflections"));

        assertConfig(ConfigurationBuilder.build("io"),
            ClasspathHelper.forPackage("io"),
            new FilterBuilder().includePackage("io"));
    }

    @Test
    public void buildFor() {
        assertThrows(ReflectionsException.class, () -> ConfigurationBuilder.build(""));

        assertConfig(ConfigurationBuilder.build(),
            ClasspathHelper.forClassLoader(),
            new FilterBuilder());

        assertConfig(ConfigurationBuilder.build("not.exist"),
            ClasspathHelper.forClassLoader(),
            new FilterBuilder().includePackage("not.exist"));
    }

    private void assertConfig(ConfigurationBuilder config, Collection<URL> urls, Predicate<String> inputsFilter) {
        assertEquals(config.getUrls(), new HashSet<>(urls));
        assertEquals(config.getInputsFilter(), inputsFilter);
        assertEquals(config.getScanners(), new HashSet<>(Arrays.asList(Scanners.SubTypes, Scanners.TypesAnnotated)));
    }

}
