package io.github.dunwu.javalib.junit4;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * JUnit 使用示例。 请注意各个方法的执行顺序。
 *
 * @author Zhang Peng
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTest {

    /**
     * <code>@BeforeClass</code> 注解指出这是附着在静态方法必须执行一次并在类的所有测试之前。 一般用于共享配置方法(如连接到数据库)。
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("call @BeforeClass");
    }

    /**
     * 当需要执行所有的测试在JUnit测试用例类后执行，<code>@AfterClass</code> 注解可以使用以清理建立方法，(从数据库如断开连接)。 注意：附有此批注(类似于BeforeClass)的方法必须定义为静态。
     */
    @AfterClass
    public static void afterClass() {
        System.out.println("call @AfterClass");
    }

    @Test
    public void testA() {
        System.out.println("call @Test testA");
        int sum = 1 + 2 + 3;
        Assert.assertEquals(6, sum);
    }

    @Test
    public void testC() {
        System.out.println("call @Test testC");
    }

    @Test
    public void testB() {
        System.out.println("call @Test testB");
    }

    /**
     * <code>@Before</code> 注解修饰的方法必须在类中的每个测试之前执行，以便执行测试某些必要的先决条件。
     */
    @Before
    public void before() {
        System.out.println("call @Before");
    }

    /**
     * <code>@After</code> 注解修饰的方法在执行每项测试后执行(如执行每一个测试后重置某些变量 ， 删除临时变量等)
     */
    @After
    public void after() {
        System.out.println("call @After");
    }

    /**
     * 当想暂时禁用特定的测试执行可以使用忽略注释。每个被注解为 <code>@Ignore</code> 的方法将不被执行。
     */
    @Ignore
    public void ignore() {
        System.out.println("call @Ignore");
    }

}
