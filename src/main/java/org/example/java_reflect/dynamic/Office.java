package org.example.java_reflect.dynamic;

/**
 * Office 软件启动程序
 * new创建对象是静态加载类，在编译时刻就需要加载所有可能用到的类。
 * 这种方法的弊端是显而易见的：
 * 如果有 100 个功能，只要有一个功能不能用，其他 99 个也不能使用
 *
 * @author lifei
 */
public class Office {
    public static void main(String[] args) {
        String word = "Word";
        String excel = "Excel";
        if (word.equals(args[0])) {
            Word w = new Word();
            w.start();
        }
        if (excel.equals(args[0])) {
            Excel e = new Excel();
            e.start();
        }
    }
}
