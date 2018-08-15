package me.pingcai;

import com.google.common.collect.Lists;
import me.pingcai.service.TestService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static me.pingcai.mockito.CustomArgumentMatchers.isNumber;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author huangpingcai
 * @since 2018/8/15 18:14
 */
public class MockitoTests {

    @Test
    public void testMock() {

        List list = mock(List.class);

        when(list.get(0)).thenReturn("a");

        when(list.remove(anyInt())).thenThrow(new RuntimeException());

        when(list.contains("a")).thenReturn(false);

        System.out.println(list.get(0));

        // 验证 list.get(0),是否被调用
        //verify(list).get(2);
        verify(list, times(1)).get(0);

        list.remove(2);

    }

    @Test
    public void testArgThat() {

        List<String> mockedList = mock(ArrayList.class);

        // argThat 可以使用自己实现的 ArgumentMatcher
        when(mockedList.contains(argThat(isNumber()))).thenReturn(true);


        System.out.println(mockedList.contains(1231241241));

    }

    @Test
    public void testVerify() {

        TestService mock = mock(TestService.class);

        mock.insertIfNotExist(null);
        mock.insertIfNotExist(any());

        // 验证方法调用次数
        verify(mock, times(2)).insertIfNotExist(any());


        verify(mock, never()).deleteById(eq(2L));

        mock.fillTest(anyMap(), any());
        mock.fillTest(anyMap(), any());

        verify(mock, atLeast(1)).fillTest(anyMap(), any());
        verify(mock, atMost(2)).fillTest(anyMap(), any());

    }

    @Test
    public void testReturnVoid() {

        TestService mock = mock(TestService.class);

        // 为无返回值的方法抛出异常
        doThrow(new RuntimeException()).when(mock).returnVoid();
        when(mock.getById(anyLong())).thenReturn(null);
        //mock.returnVoid();

        // 为抛出异常的方法添加返回值
        when(mock.update(any())).thenThrow(new RuntimeException());

        //mock.update(null);

        doReturn(true).when(mock).update(null);

        System.out.println(mock.update(null));
    }

    @Test
    public void testSpy() {
        List<String> realList = Lists.newArrayList();
        List<String> spy = spy(realList);

        // 不可能,因为真实对象没有内容
        //when(spy.get(0)).thenReturn("abc");
        //System.out.println(spy.get(0));

        // 打桩
        doReturn("abc").when(spy).get(0);
        System.out.println(spy.get(0));


    }

    @Test
    public void testArgument() {

        Comparable comparable = mock(Comparable.class);

        when(comparable.compareTo("a")).thenReturn(0);
        when(comparable.compareTo("b")).thenReturn(1);
        when(comparable.compareTo("c")).thenReturn(2);

        assert comparable.compareTo("a") == 0;

    }


}
