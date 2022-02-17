package io.github.dunwu.javatech.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.dunwu.javatech.reflections.MoreTestsModel.*;
import static io.github.dunwu.javatech.reflections.ReflectionsTest.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.reflections.ReflectionUtils.Annotations;
import static org.reflections.scanners.Scanners.Resources;
import static org.reflections.scanners.Scanners.SubTypes;

public class MoreTests {

    @Test
    public void test_cyclic_annotation() {
        Reflections reflections = new Reflections(MoreTestsModel.class);
        assertThat(reflections.getTypesAnnotatedWith(CyclicAnnotation.class),
            equalTo(CyclicAnnotation.class));
    }

    @Test
    public void no_exception_when_configured_scanner_store_is_empty() {
        Reflections reflections = new Reflections(
            new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClass(TestModel.class))
                .setScanners());

        assertNull(reflections.getStore().get(SubTypes.index()));
        assertTrue(reflections.getSubTypesOf(TestModel.C1.class).isEmpty());
        assertTrue(reflections.get(SubTypes.of(TestModel.C1.class)).isEmpty());
        assertTrue(reflections.get(Resources.with(".*")).isEmpty());
    }

    @Test
    public void getAllAnnotated_returns_meta_annotations() {
        Reflections reflections = new Reflections(MoreTestsModel.class);
        for (Class<?> type : reflections.getTypesAnnotatedWith(Meta.class)) {
            Set<Annotation> allAnnotations = ReflectionUtils.get(Annotations.of(type));
            List<? extends Class<? extends Annotation>> collect =
                allAnnotations.stream().map(Annotation::annotationType).collect(Collectors.toList());
            assertTrue(collect.contains(Meta.class));
        }

        Meta meta = new Meta() {
            @Override
            public String value() { return "a"; }

            @Override
            public Class<? extends Annotation> annotationType() { return Meta.class; }
        };
        for (Class<?> type : reflections.getTypesAnnotatedWith(meta)) {
            Set<Annotation> allAnnotations = ReflectionUtils.get(Annotations.of(type));
            List<? extends Class<? extends Annotation>> collect =
                allAnnotations.stream().map(Annotation::annotationType).collect(Collectors.toList());
            assertTrue(collect.contains(Meta.class));
        }
    }

    @Test
    public void resources_scanner_filters_classes() {
        Reflections reflections = new Reflections(Scanners.Resources);
        Collection<String> resources = reflections.getResources(".*");
        assertTrue(resources.stream().noneMatch(res -> res.endsWith(".class")));
    }

    @Test
    public void test_repeatable() {
        Reflections ref = new Reflections(MoreTestsModel.class);
        Collection<Class<?>> clazzes = ref.getTypesAnnotatedWith(Name.class);
        assertTrue(clazzes.contains(SingleName.class));
        assertFalse(clazzes.contains(MultiName.class));

        clazzes = ref.getTypesAnnotatedWith(Names.class);
        assertFalse(clazzes.contains(SingleName.class));
        assertTrue(clazzes.contains(MultiName.class));
    }

    @Test
    public void test_method_param_names_not_local_vars() throws NoSuchMethodException {
        Reflections reflections = new Reflections(MoreTestsModel.class, new MethodParameterNamesScanner());

        Class<ParamNames> clazz = ParamNames.class;
        assertEquals(reflections.getMemberParameterNames(clazz.getConstructor(String.class)).toString(),
            "[param1]");
        assertEquals(
            reflections.getMemberParameterNames(clazz.getMethod("test", String.class, String.class)).toString(),
            "[testParam1, testParam2]");
        assertEquals(reflections.getMemberParameterNames(clazz.getMethod("test", String.class)).toString(),
            "[testParam]");
        assertEquals(reflections.getMemberParameterNames(clazz.getMethod("test2", String.class)).toString(),
            "[testParam]");
    }

}
