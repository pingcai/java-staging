package me.pingcai;

import junit.framework.TestResult;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.reposiroty.TestRepository;
import me.pingcai.service.TestService;
import me.pingcai.service.impl.TestServiceImpl;
import me.pingcai.util.JsonUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

/**
 * @author huangpingcai
 * @since 2018/8/15 22:42
 */
// 等同于 MockitoAnnotations.initMocks(this)
//@RunWith(MockitoJUnitRunner.class)
public class MockitoTests2 {

    // 空对象,可以将mock出来的对象注入进去
    @InjectMocks
    private TestService testService = new TestServiceImpl();

    // 模拟的对象
    @Mock
    private TestRepository testRepository;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        TestService testService = mock(TestService.class);
        doReturn(true).when(testService).deleteById(anyLong());
        System.out.println(testService.deleteById(1L));
    }

    @Test
    public void testSuccessive() {

        TestService mock = mock(TestService.class);

        // 每次调用返回不同结果,超过次数以最后一个then为准
        when(mock.deleteById(anyLong()))
                .thenReturn(false)
                .thenReturn(true)
                .thenThrow(new RuntimeException());

        System.out.println(mock.deleteById(anyLong()));
        System.out.println(mock.deleteById(anyLong()));

        // 简写
        when(mock.getById(anyLong())).thenReturn(null,new me.pingcai.dao.entity.Test(),null,null);
        System.out.println(mock.getById(anyLong()));
        System.out.println(mock.getById(anyLong()));
        System.out.println(mock.getById(anyLong()));
        System.out.println(mock.getById(anyLong()));

    }

    @Test
    public void testInject() {

        me.pingcai.dao.entity.Test test = new me.pingcai.dao.entity.Test();
        test.setId(222L);
        test.setAge((byte)1);
        test.setName("mock");

        when(testRepository.selectByName(any())).thenReturn(test);

        me.pingcai.dao.entity.Test result = testService.selectByName(null);

        TestService testService = new TestServiceImpl();
        ReflectionTestUtils.setField(testService,"testRepository",testRepository);

        System.out.println(JsonUtils.object2Json(result));
    }




}
