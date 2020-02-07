package io.github.dunwu.javaee.oss.test;

import org.junit.*;

/**
 * JUnit4开始支持注解，本例展示一个单元测试执行过程中，各个注解的调用顺序
 *
 * @author Victor Zhang
 * @since 2016/11/18.
 */
public class JUnitExecTest {

    @BeforeClass
    public static void beforeClass1() {
        System.out.println("@beforeClass1");
    }

    @BeforeClass
    public static void beforeClass2() {
        System.out.println("@beforeClass2");
    }

    @AfterClass
    public static void afterClass1() {
        System.out.println("@afterClass1");
    }

    @AfterClass
    public static void afterClass2() {
        System.out.println("@afterClass2");
    }

    @Before
    public void before1() {
        System.out.println("@before1");
    }

    @Before
    public void before2() {
        System.out.println("@before2");
    }

    @Test
    public void testAdd() {
        System.out.println(1);
    }

    @Test
    public void testSubstract() {
        System.out.println(2);
    }

    @Ignore("Multiply() Not yet implemented")
    @Test
    public void testMultiply() {
        System.out.println(3);
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testDivide() {
        System.out.println(4);
    }

    @Test(timeout = 1000)
    public void testSquareRoot() {
        System.out.println(5);
    }

    @Test
    public void divideByZero() {
        System.out.println(6);
    }

    @After
    public void after1() {
        System.out.println("@after1");
    }

    @After
    public void after2() {
        System.out.println("@after2");
    }

}
