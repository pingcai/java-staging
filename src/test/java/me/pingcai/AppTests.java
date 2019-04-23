package me.pingcai;

import lombok.Data;

/**
 * create by 黄平财 at 2017/11/30 23:23
 */
@Data
public class AppTests {

    abstract static class BaseValidator {
        void valid(Object object) {
            if (null == object) {
                throw new IllegalArgumentException();
            }
            subValid(object);
        }

        abstract void subValid(Object object);
    }

    class PayToCardValidator extends BaseValidator{

        @Override
        void subValid(Object object) {

        }
    }

    public static void main(String[] args){
        new BaseValidator(){
            @Override
            void subValid(Object object) {

            }
        };
    }
}
