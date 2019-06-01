package me.pingcai.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author huangpingcai
 * @since 2019-05-24 15:38
 */
public class MockitoTest {
    @Spy
    @InjectMocks
    Tom tom = new Tom();

    @Mock
    Cat cat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception {

        doNothing().when(cat).print();

        tom.print();

        verify(tom,times(1)).print();
        verify(tom,never()).walk();
    }

    class Tom {

        @Autowired
        private Cat cat;

        public void print(){
            cat.print();
        }

        public void walk(){
            cat.print();
        }
    }

    class Cat{
        public void print(){
            System.out.println("...");
        }
    }
}
