package org.example.java_reflect.demo;

/**
 * 获取类类型的三种方式
 *
 * @author lifei
 */
class ClassDemo {
    public static void main(String[] args) {
        Foo foo1 = new Foo();
        // 第一种表达方式
        // 任何一个类都有一个隐含的静态成员变量 class
        Class c1 = Foo.class;

        // 第二种表达方式
        Class c2 = foo1.getClass();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1 == c2);

        // 第三种表达方式
        Class c3 = null;
        try {
            c3 = Class.forName("org.example.java_reflect.demo.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c3 == c2);

        try {
            // 调用无参数的构造方法
            Foo foo2 = (Foo) c1.newInstance();
            foo2.print();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Foo {
    void print() {
        System.out.println("foo");
    }
}
