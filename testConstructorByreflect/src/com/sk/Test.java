package com.sk;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args) {
        try {
            Person person = (Person) newInstance("com.sk.Person", new Object[]{});
            person.getPerson();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 得到带构造的类的实例
     * */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Constructor cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }
}
