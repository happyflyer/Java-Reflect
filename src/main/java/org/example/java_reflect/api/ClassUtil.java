package org.example.java_reflect.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取类信息的工具类
 *
 * @author lifei
 */
class ClassUtil {
    /**
     * 打印类信息，包括类的成员函数（方法）、成员变量、构造函数（方法）
     *
     * @param obj 要获取的类的实例对象
     */
    public static void printClassMessage(Object obj) {
        // 第一步、获取类的类类型
        // 如果传入 Object 对象，得到的就是 Object 的类类型， 如果传入的是 Object 对象的任何子类，得到的就是子类的类类型
        Class c = obj.getClass();
        // 第二步、获取类的名称
        System.out.println("类的名称为：" + c.getName());
        // 第三步、获取方法信息
        printMethodMessage(obj);
        // 第四步，获取类的成员变量
        printFieldMessage(obj);
        // 第五步，获取类的构造方法
        printConstructorMessage(obj);
    }

    /**
     * 打印成员函数（方法）信息
     *
     * @param obj 传入实例对象
     */
    public static void printMethodMessage(Object obj) {
        Class c = obj.getClass();
        // 获取该类所有 public 的函数，包括从父类继承来的
        Method[] ms = c.getMethods();
        // 获取该类所有自己声明的方法，不问访问权限
        Method[] ms2 = c.getDeclaredMethods();
        for (Method m : ms) {
            Class returnType = m.getReturnType();
            System.out.print(returnType.getName() + " ");
            System.out.print(m.getName() + "(");
            Class[] paramTypes = m.getParameterTypes();
            for (Class param : paramTypes) {
                System.err.print(param.getName() + ",");
            }
            System.out.println(");");
        }
    }

    /**
     * 打印成员变量信息
     *
     * @param obj 传入的实例对象
     */
    public static void printFieldMessage(Object obj) {
        Class c = obj.getClass();
        // java.lang.reflect.Field 封装了关于类的成员变量的操作
        Field[] fs = c.getFields();
        Field[] fs2 = c.getDeclaredFields();
        for (Field f : fs) {
            Class fieldType = f.getType();
            String typeName = fieldType.getName();
            String fieldName = f.getName();
            System.out.println(typeName + " " + fieldName + ";");
        }
    }

    /**
     * 打印构造函数（方法）的信息
     *
     * @param obj 传入的实例对象
     */
    public static void printConstructorMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * 构造函数必须是自己声明的
         * 构造函数的名称与类同名
         * 所以这里就是获取构造哦啊函数的参数列表
         */
        Constructor[] cs = c.getConstructors();
        Constructor[] cs2 = c.getDeclaredConstructors();
        for (Constructor con : cs) {
            System.out.print(con.getName() + "(");
            Class[] paramTypes = con.getParameterTypes();
            for (Class param : paramTypes) {
                System.err.print(param.getName() + ",");
            }
            System.out.println(");");
        }
    }
}
