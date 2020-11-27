package org.example.java_reflect.method;

import java.lang.reflect.Method;

/**
 * 方法的反射
 *
 * @author lifei
 */
class MethodDemo {
    public static void main(String[] args) {
        A a1 = new A();
        Class c = a1.getClass();
        try {
            Method m = c.getMethod("print", new Class[]{int.class, int.class});
            m.invoke(a1, new Object[] { 10, 20 });
            Method m2 = c.getMethod("print", int.class, int.class);
            m2.invoke(a1, 10, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method m3 = c.getMethod("print", String.class, String.class);
            m3.invoke(a1, "Hello", "World");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class A {
    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + ", " + b.toLowerCase());
    }
}
