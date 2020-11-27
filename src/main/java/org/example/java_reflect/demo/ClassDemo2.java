package org.example.java_reflect.demo;

/**
 * 基本数据类型的类类型
 *
 * @author lifei
 */
public class ClassDemo2 {
    public static void main(String[] args) {
        // int 的类类型/字节码表示
        Class c1 = int.class;
        System.out.println(c1.getName());

        // String 的类类型/字节码表示
        Class c2 = String.class;
        System.out.println(c2.getName());
        System.out.println(c2.getSimpleName());

        // double 的类类型/字节码表示
        Class c3 = double.class;
        System.out.println(c3.getName());
        // Double 的类类型/字节码表示
        Class c4 = Double.class;
        System.out.println(c4.getName());

        // void 的类类型/字节码表示
        // 凡是在类里面声明的关键字都有类类型
        Class c5 = void.class;
        System.out.println(c5.getName());
    }
}
