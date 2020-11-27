package org.example.java_reflect.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 集合泛型的本质
 *
 * @author lifei
 */
public class GenericDemo {
    public static void main(String[] args) {
        List lst = new ArrayList();
        List<String> lst2 = new ArrayList<>();
        lst2.add("hello");

        Class c1 = lst.getClass();
        Class c2 = lst2.getClass();
        System.out.println(c1 == c2);

        try {
            Method m = c2.getMethod("add", Object.class);
            m.invoke(lst2, 100);
            System.out.println(lst2.size());
            System.out.println(lst2);
            // 此时不能使用 forEach 遍历，因为集合中的元素类型不同，会产生异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
