package org.example.java_reflect.dynamic;

/**
 * Office 软件启动程序
 * 动态加载类，在运行时刻加载多需要使用的类。
 * 通过类类型创建该类的实例对象。
 * 如果需要增加新功能，直接重新写新类，不需要重新编译这个类。
 * 这是一种设计思想，使用动态加载功能模块
 *
 * @author lifei
 */
public class OfficeBetter {
    public static void main(String[] args) {
        try {
            Class c1 = Class.forName(args[0]);
            Officeable oa = (Officeable) c1.newInstance();
            oa.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
