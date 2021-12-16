package localtests;

import dependency_injection.BeanFactory;
import dependency_injection.BeanFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import testclass.*;

import static org.junit.jupiter.api.Assertions.*;

public class BeanFactoryExtTest {
    private BeanFactory beanFactory;

    @BeforeEach
    public void setup() {
        this.beanFactory = new BeanFactoryImpl();
        beanFactory.loadInjectProperties(new File("inject-ext.properties"));
        beanFactory.loadValueProperties(new File("value-ext.properties"));
        //System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        //System.setErr(new PrintStream(OutputStream.nullOutputStream()));
    }

    // ----------------------------------- Extended tests -----------------------------------

    /*@Test
    public void testInjectTestFieldConstants() {
        InjectTest instance = beanFactory.createInstance(InjectTest.class);
        assertNotNull(instance);
        assertEquals("sustech-ooad", instance.getStringVal());
        assertEquals(42, instance.getIntVal());
        assertArrayEquals(new String[]{"sustech", "cs", "ooad"}, instance.getStringArray());
        assertEquals(0.0, instance.getDoubleVal2());
    }

    @Test
    public void testInjectTestFieldInject() {
        InjectTest instance = beanFactory.createInstance(InjectTest.class);
        assertNotNull(instance);
        assertNotNull(instance.getBDep());
        assertNotNull(instance.getCDep());
        assertNull(instance.getDDep());
        assertEquals(":)", instance.getStringVal2());
    }

    @Test
    public void testInjectTestConstructorInject() {
        InjectTest instance = beanFactory.createInstance(InjectTest.class);
        assertNotNull(instance);
        assertNotNull(instance.getADep());
        assertEquals(4.2, instance.getDoubleVal());
    }

    @Test
    public void testInjectTestNestedInject1() {
        InjectTest instance = beanFactory.createInstance(InjectTest.class);
        assertNotNull(instance);
        assertNotNull(instance.getLDep());
        assertNotNull(instance.getLDep().getBDep());
        assertTrue(instance.getLDep().isBool());
    }

    @Test
    public void testInjectTestNestedInject2() {
        InjectTest instance = beanFactory.createInstance(InjectTest.class);
        assertNotNull(instance);
        assertNotNull(instance.getJDep());
        assertTrue(instance.getJDep() instanceof JImpl2);
        assertTrue(instance.getJDep().getEDep() instanceof EImpl);
        assertArrayEquals(new boolean[]{true, false, false, true}, instance.getJDep().getBools());
        assertArrayEquals(new Integer[]{2, 3, 5, 7, 11}, instance.getJDep().getIntegers());
        assertArrayEquals(new String[]{"all values are non-empty"}, instance.getJDep().getStrings());
    }*/

    @Test
    public void testTypeTest() {
        TypeTest instance = beanFactory.createInstance(TypeTest.class);
        assertNotNull(instance);
        assertTrue(instance.bool);
        assertTrue(instance.boolObject);
        assertEquals('F', instance.c);
        assertEquals('F', instance.charObject);
        assertEquals((byte) 7, instance.b);
        assertEquals((byte) 7, instance.byteObject);
        assertEquals((short) 2200, instance.s);
        assertEquals((short) 2200, instance.shortObject);
        assertEquals(4396934, instance.i);
        assertEquals(4396934, instance.intObject);
        //assertEquals(21474836481L, instance.l);
        //assertEquals(21474836481L, instance.longObject);
        assertEquals(3.14f, instance.f);
        assertEquals(3.14f, instance.floatObject);
        assertEquals(4.2, instance.d);
        assertEquals(4.2, instance.doubleObject);
        assertEquals("test-string", instance.str);
        /*assertArrayEquals(new boolean[]{true, true, false, true}, instance.booleans);
        assertArrayEquals(new Integer[]{1, 0, 0, 8, 6, 1, 0, 0, 1, 0}, instance.integers);
        assertArrayEquals(new String[]{"a", "bcd", "efghijk", "1"}, instance.strings);
        assertArrayEquals(new double[]{1, 3, 5.2, 42.777}, instance.doubles);*/
        assertNull(instance.notSet);
    }
}
