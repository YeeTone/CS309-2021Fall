package dependency_injection;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class BeanFactoryImpl implements BeanFactory {
    private final Map<Class<?>, Class<?>> injectClassMap = new HashMap<>();
    private final Map<String, String> valueMap = new HashMap<>();
    private static final Set<Class<?>> wrapperClasses = Set.of(
            Boolean.class,
            Byte.class, Short.class, Integer.class, Long.class,
            Character.class,
            Float.class, Double.class
    );
    private static final Set<Class<?>> primitiveClasses = Set.of(
            boolean.class,
            byte.class, short.class, int.class, long.class,
            char.class,
            float.class, double.class
    );

    @Override
    public void loadInjectProperties(File file) {
        try {
            Properties injectProperties = new Properties();
            FileInputStream fis = new FileInputStream(file);
            injectProperties.load(fis);
            injectClassMap.clear();
            for (Object o : injectProperties.keySet()) {
                Class<?> abstractClazz = Class.forName(o.toString());
                Class<?> implementClazz = Class.forName(injectProperties.get(o).toString());
                injectClassMap.put(abstractClazz, implementClazz);
            }

            fis.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadValueProperties(File file) {
        try {
            Properties valueProperties = new Properties();
            FileInputStream fis = new FileInputStream(file);
            valueProperties.load(fis);
            valueMap.clear();
            for (Object o : valueProperties.keySet()) {
                String fieldName = o.toString();
                String fieldValue = valueProperties.get(o).toString();
                valueMap.put(fieldName, fieldValue);
            }
            fis.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T createInstance(Class<T> clazz) {
        try {
            //1. ????????????implementClazz
            Class<?> implementClazz = injectClassMap.getOrDefault(clazz, clazz);

            //2.??????????????????constructor
            //  2.1 ?????????inject?????????
            //  2.2 getConstructor();
            Constructor<?> injectedConstructor = findConstructor(implementClazz);

            //3. ??????constructor???????????????parameters
            Parameter[] parameters = injectedConstructor.getParameters();
            //4. ????????????Object[], ??????????????????parameter????????????
            Object[] objects = new Object[parameters.length];
            //5. ???????????????parameter???????????????????????????Object[]
            buildObjects(parameters, objects);
            //6. ??????Object[]?????????constructor?????????impleClazz??????
            T tInstance = (T) injectedConstructor.newInstance(objects);
            // --------- ?????????????????? -----------
            //7. ?????????ImplementClazz?????????field??????????????????field
            buildInjectFields(tInstance);
            //8. ???????????????inject?????????????????????????????????????????????????????????????????????????????????
            buildInjectSetter(tInstance);
            return tInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Constructor<?> findConstructor(Class<?> implementClazz) throws NoSuchMethodException {
        try {
            Constructor<?>[] declaredConstructors = implementClazz.getDeclaredConstructors();
            for (Constructor<?> dc : declaredConstructors) {
                if (dc != null && dc.isAnnotationPresent(Inject.class)) {
                    return dc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return implementClazz.getDeclaredConstructor();
    }

    private void buildObjects(Parameter[] parameters, Object[] objects) {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] != null) {
                if (parameters[i].isAnnotationPresent(Value.class)) {
                    Value valueAnno = parameters[i].getAnnotation(Value.class);
                    Class<?> type = parameters[i].getType();
                    if (isPrimitiveStringTypes(type)) {
                        objects[i] = buildPrimitiveStringClassObject(valueAnno, type);
                    }
                } else {
                    Class<?> type = parameters[i].getType();
                    objects[i] = createInstance(type);
                }
            }

        }
    }

    private Object buildPrimitiveStringClassObject(Value valueAnno, Class<?> type) {
        if (type == null || valueAnno == null) {
            return null;
        }
        String v = valueMap.getOrDefault(valueAnno.value(), valueAnno.value());

        String[] numStrings = v.split(valueAnno.delimiter());
        return Parser.getInstance(type).parse(valueAnno, numStrings);
    }

    enum Parser {
        //??????????????????????????????????????????????????????????????????if-else??????
        //??????????????????????????????
        IntegerParser {
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    int i = Integer.parseInt(s);
                    if (valueAnno.min() <= i && i <= valueAnno.max()) {
                        return i;
                    }
                }
                return 0;
            }
        },
        ByteParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    byte b = Byte.parseByte(s);
                    if (valueAnno.min() <= b && b <= valueAnno.max()) {
                        return b;
                    }
                }
                return (byte) 0;
            }
        },
        ShortParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    short sh = Short.parseShort(s);
                    if (valueAnno.min() <= sh && sh <= valueAnno.max()) {
                        return sh;
                    }
                }
                return (short) 0;
            }
        },
        LongParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    long lo = Long.parseLong(s);
                    if (valueAnno.min() <= lo && lo <= valueAnno.max()) {
                        return lo;
                    }
                }
                return 0L;
            }
        },
        BooleanParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    return Boolean.parseBoolean(s);
                }
                return false;
            }
        },
        CharacterParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                return numStrings[0].charAt(0);
            }
        },
        FloatParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    float fl = Float.parseFloat(s);
                    if (valueAnno.min() <= fl && fl <= valueAnno.max()) {
                        return fl;
                    }
                }
                return 0f;
            }
        },
        DoubleParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    double dou = Double.parseDouble(s);
                    if (valueAnno.min() <= dou && dou <= valueAnno.max()) {
                        return dou;
                    }
                }
                return 0d;
            }
        },
        StringParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                for (String s : numStrings) {
                    int length = s.length();
                    if (valueAnno.min() <= length && length <= valueAnno.max()) {
                        return s;
                    }
                }
                return "default value";
            }
        },
        NullParser {
            @Override
            public Object parse(Value valueAnno, String[] numStrings) {
                return null;
            }
        };

        private static final Map<Class<?>, Parser> classParserRelation = new HashMap<>();

        static {
            prepare();
        }

        private static void prepare() {
            classParserRelation.clear();
            Class<?>[] primitiveTypes = BeanFactoryImpl.primitiveClasses.toArray(new Class<?>[0]);
            Class<?>[] wrapperTypes = BeanFactoryImpl.wrapperClasses.toArray(new Class<?>[0]);
            Arrays.sort(primitiveTypes, Comparator.comparing(Class::getSimpleName));
            Arrays.sort(wrapperTypes, Comparator.comparing(Class::getSimpleName));

            for (int i = 0; i < 8; i++) {
                Parser p = Parser.valueOf(wrapperTypes[i].getSimpleName() + "Parser");
                classParserRelation.put(primitiveTypes[i], p);
                classParserRelation.put(wrapperTypes[i], p);
            }
            classParserRelation.put(String.class, StringParser);
        }

        public abstract Object parse(Value valueAnno, String[] numStrings);

        public static Parser getInstance(Class<?> clazz) {
            return classParserRelation.getOrDefault(clazz, NullParser);
        }
    }

    private <T> void buildInjectFields(T tInstance) {
        Field[] fields = tInstance.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                if (f != null) {
                    if (f.isAnnotationPresent(Inject.class)) {
                        //????????????????????????????????????Inject????????????????????????createInstance
                        Object o = createInstance(f.getType());
                        f.setAccessible(true);
                        f.set(tInstance, o);
                        f.setAccessible(false);
                    } else if (isPrimitiveStringTypes(f.getType())) {
                        if (f.isAnnotationPresent(Value.class)) {
                            //???????????????????????????+String?????????@Value???????????????value????????????
                            Value valueAnno = f.getAnnotation(Value.class);
                            Object o = buildPrimitiveStringClassObject(valueAnno, f.getType());
                            f.setAccessible(true);
                            f.set(tInstance, o);
                            f.setAccessible(false);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void buildInjectSetter(T tInstance) {
        Method[] methods = tInstance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            //????????????Inject??????????????????????????????parameter????????????????????????????????????????????????????????????
            //????????????????????????????????????????????????????????????field??????????????????
            if (method != null) {
                if (method.isAnnotationPresent(Inject.class)) {
                    try {
                        Object value = createInstance(method.getParameters()[0].getType());
                        method.setAccessible(true);
                        method.invoke(tInstance, value);
                        method.setAccessible(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }

        }
    }

    private boolean isPrimitiveStringTypes(Class<?> type) {
        return primitiveClasses.contains(type)
                || type == String.class
                || wrapperClasses.contains(type);
    }
}

