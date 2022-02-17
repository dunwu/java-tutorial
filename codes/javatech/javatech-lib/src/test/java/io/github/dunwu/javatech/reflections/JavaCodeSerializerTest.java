package io.github.dunwu.javatech.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.serializers.JavaCodeSerializer;
import org.reflections.util.FilterBuilder;
import org.reflections.util.NameHelper;

public class JavaCodeSerializerTest implements NameHelper {

	public JavaCodeSerializerTest() {
		FilterBuilder filterBuilder = new FilterBuilder().includePattern("io\\.github\\.dunwu\\.javatech\\.reflections\\.TestModel\\$.*");
		Reflections reflections = new Reflections(
			TestModel.class,
			new TypeElementsScanner().filterResultsBy(filterBuilder),
			filterBuilder);

		String filename = ReflectionsTest.getUserDir() + "/src/test/java/io.github.dunwu.javatech.reflections.MyTestModelStore";
		reflections.save(filename, new JavaCodeSerializer());
	}

	@Test
	public void check() {
		// MyTestModelStore contains TestModel type elements
		Class<?> c1 = MyTestModelStore.io.github.dunwu.javatech.reflections.TestModel$C1.class;
		Class<?> ac1 = MyTestModelStore.io.github.dunwu.javatech.reflections.TestModel$C1.annotations.io_github_dunwu_javatech_reflections_TestModel$AC1.class;
		Class<?> f1 = MyTestModelStore.io.github.dunwu.javatech.reflections.TestModel$C4.fields.f1.class;
		Class<?> m1 = MyTestModelStore.io.github.dunwu.javatech.reflections.TestModel$C4.methods.m1.class;
	}
}
