package testclass;

import dependency_injection.Value;

public class TypeTest {
    @Value("config.boolean")
    public boolean bool;

    @Value("config.boolean")
    public Boolean boolObject;

    @Value("config.char")
    public char c;

    @Value("config.char")
    public Character charObject;

    @Value("config.byte")
    public byte b;

    @Value("config.byte")
    public Byte byteObject;

    @Value("config.short")
    public short s;

    @Value("config.short")
    public Short shortObject;

    @Value("config.int")
    public int i;

    @Value("config.int")
    public Integer intObject;

    @Value(value = "config.long")
    public long l;

    @Value("config.long")
    public Long longObject;

    @Value("config.float")
    public float f;

    @Value("config.float")
    public Float floatObject;

    @Value("config.double")
    public double d;

    @Value("config.double")
    public Double doubleObject;

    @Value("config.string")
    public String str;

    @Value("config.list.boolean")
    public boolean[] booleans;

    @Value("config.list.integer")
    public Integer[] integers;

    @Value("config.list.string")
    public String[] strings;

    @Value(value = "config.list.double.delimiter", delimiter = "!!")
    public double[] doubles;

    public Object notSet;
}
