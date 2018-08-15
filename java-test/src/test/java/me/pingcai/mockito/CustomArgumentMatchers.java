package me.pingcai.mockito;

import org.mockito.ArgumentMatcher;

/**
 * @author huangpingcai
 * @since 2018/8/15 19:38
 */
public class CustomArgumentMatchers {

    public static class NumberMatcher implements ArgumentMatcher<Integer> {

        @Override
        public boolean matches(Integer argument) {
            return argument != null;
        }
    }
    public static NumberMatcher isNumber(){
        return new NumberMatcher();
    }


}
