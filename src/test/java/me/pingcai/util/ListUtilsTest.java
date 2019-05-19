package me.pingcai.util;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author huangpingcai
 * @since 2019-05-19 02:25
 */
public class ListUtilsTest {

    @Test
    public void join1() {
        List<String> list = Lists.newArrayList("aaa","bbb","ccc");
        Assert.assertEquals("aaa,bbb,ccc", ListUtils.join(",", list));
    }

    @Test
    public void join2() {
        List<String> list = Lists.newArrayList("aaa","bbb","ccc");
        Assert.assertEquals("aaabbbccc", ListUtils.join("", list));
    }

    @Test
    public void join3() {
        List<String> list = Lists.newArrayList("aaa","bbb","ccc");
        Assert.assertEquals("aaa,,bbb,,ccc", ListUtils.join(",,", list));
    }
}