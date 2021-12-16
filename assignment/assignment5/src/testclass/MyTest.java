package testclass;

import dependency_injection.BeanFactory;
import dependency_injection.BeanFactoryImpl;
import dependency_injection.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

public class MyTest {
    @Value(value = "myV")
    long l;

    public static void main(String[] args) {
        System.out.println("ABC".substring(2,-1));
    }
}

class T {
    BeanFactory beanFactory;

    @BeforeEach
    public void setup() {
        this.beanFactory = new BeanFactoryImpl();
        beanFactory.loadInjectProperties(new File("local-inject.properties"));
        beanFactory.loadValueProperties(new File("local-value.properties"));
        //System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        //System.setErr(new PrintStream(OutputStream.nullOutputStream()));
    }

    @Test
    void t1() {
        MyTest mt = beanFactory.createInstance(MyTest.class);
        System.out.println(mt.l);
    }
}
