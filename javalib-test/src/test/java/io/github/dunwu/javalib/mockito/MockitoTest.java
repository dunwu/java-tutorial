package io.github.dunwu.javalib.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MockitoTest {

    // 模拟 LinkedList 的一个对象
    LinkedList mockedList = mock(LinkedList.class);

    @BeforeEach
    void beforeEach() {
        mockedList.clear();
    }

    @Test
    void test() {
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
    }

    /**
     * 模拟对象
     */
    @Test
    void test01() {
        // 此时调用get方法，会返回null，因为还没有对方法调用的返回值做模拟
        System.out.println(mockedList.get(0));
    }

    /**
     * 模拟方法调用的返回值
     */
    @Test
    void test02() {
        // 模拟获取第一个元素时，返回字符串first。给特定的方法调用返回固定值在官方说法中称为stub。
        when(mockedList.get(0)).thenReturn("first");
        // 此时打印输出first
        System.out.println(mockedList.get(0));
    }

    /**
     * 模拟方法调用抛出异常
     */
    @Test
    void test03() {
        // 模拟获取第二个元素时，抛出RuntimeException
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        try {
            // 此时将会抛出RuntimeException
            System.out.println(mockedList.get(1));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            assertThat(e.getMessage()).isEqualTo(null);
        }
    }

    /**
     * 模拟方法调用抛出异常2
     */
    @Test
    void test04() {
        doThrow(new RuntimeException("clear exception")).when(mockedList).clear();
        try {
            mockedList.clear();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            assertThat(e.getMessage()).contains("clear exception");
        }
    }

    /**
     * 模拟调用方法时的参数匹配
     */
    @Test
    void test05() {
        // anyInt()匹配任何int参数，这意味着参数为任意值，其返回值均是element
        when(mockedList.get(anyInt())).thenReturn("element");
        // 此时打印是element
        System.out.println(mockedList.get(999));
    }

    /**
     * 模拟方法调用次数
     */
    @Test
    void test06() {
        // 调用add一次
        mockedList.add("once");
        // 下面两个写法验证效果一样，均验证add方法是否被调用了一次
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
    }

    /**
     * 校验行为
     */
    @Test
    void test07() {
        // using mock object
        mockedList.add("one");
        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * 模拟方法调用(Stubbing)
     */
    @Test
    void test08() {
        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        // following prints "first"
        System.out.println(mockedList.get(0));
        try {
            // following throws runtime exception
            System.out.println(mockedList.get(1));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        // following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        verify(mockedList).get(0);
    }

    /**
     * 校验方法调用次数
     */
    @Test
    void test09() {
        // using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three");
        mockedList.add("three");
        mockedList.add("three");
        // following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
        // exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three");
        // verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");
        // verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(5)).add("three");
    }

    /**
     * 校验方法调用顺序
     */
    @Test
    void test10() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);
        // using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        // create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);
        // following will make sure that add is first called with "was added first, then
        // with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        // using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");
        // create inOrder object passing any mocks that need to be verified in order
        inOrder = inOrder(firstMock, secondMock);
        // following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");
        // Oh, and A + B can be mixed together at will
    }

    /**
     * 校验方法是否从未调用
     */
    @Test
    void test11() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        // using mocks - only mockOne is interacted
        mockOne.add("one");
        // ordinary verification
        verify(mockOne).add("one");
        // verify that method was never called on a mock
        verify(mockOne, never()).add("two");
        // verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    /**
     * 重置Mock
     */
    void test12() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);
        reset(mock);
    }

}
