package me.pingcai.mock;

import me.pingcai.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * @author huangpingcai
 * @since 2019-05-18 18:18
 */
@PrepareForTest(JsonUtils.class)
@RunWith(PowerMockRunner.class)
public class PowerMockTest {

    @Test
    public void test() throws Exception {
        mockStatic(JsonUtils.class);

        doReturn("json").when(JsonUtils.class,"object2Json", any());

        Assert.assertEquals("json", JsonUtils.object2Json(this));
    }

}
