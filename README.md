# [Java Reflect](https://www.imooc.com/learn/199)

## 1. Class 类

- 在面向对象的世界里，万事万物皆对象（在 Java 中除了基本数据类型、静态成员），类是对象，类是 `java.lang.Class` 类的实例对象
- 有一个类名字就叫 `Class`
- 类也是对象，是 `Class` 类的实例对象，这个对象被我们称为该类的**类类型**
- 可以通过类类型创建类的对象实例

获取类类型的三种方式

```java
Foo foo1 = new Foo();
// 任何一个类都有一个隐含的静态成员变量 class
Class c1 = Foo.class;
```

```java
Class c2 = foo1.getClass();
System.out.println(c1);
System.out.println(c2);
System.out.println(c1 == c2);
```

```java
Class c3 = null;
try {
    c3 = Class.forName("org.example.java_reflect.demo.Foo");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
System.out.println(c3 == c2);
```

使用类类型创建类的对象实例

```java
try {
    // 调用无参数的构造方法
    Foo foo2 = (Foo) c1.newInstance();
    foo2.print();
} catch (InstantiationException | IllegalAccessException e) {
    e.printStackTrace();
}
```

## 2. 动态加载

```java
Class.forName("类的全称");
```

1. 不仅表示了类的类类型，还代表了动态加载类
2. 区分编译、运行
3. 编译时加载的类是 **静态加载类**
4. 运行时加载的类是 **动态加载类**

### 2.1. 静态加载类

```java
public interface Officeable {
    void start();
}
```

```java
public class Word implements Officeable {
    @Override
    public void start() {
        System.out.println("Word starts ...");
    }
}
```

```java
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
```

`new` 创建对象是静态加载类，在编译时刻就需要加载所有可能用到的类。

这种方法的弊端是显而易见的：如果有 100 个功能，只要有一个功能不能用，其他 99 个也不能使用！

### 2.2. 动态加载类

```java
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
```

在运行时刻加载需要使用的类。通过类类型创建该类的实例对象。

如果需要增加新功能，直接重新写新类，不需要重新编译这个类。

## 3. 基本数据类型的类类型

基本数据类型的类类型可以看作是类类型概念的一个扩展。

```java
// int 的类类型/字节码表示
Class c1 = int.class;
// String 的类类型/字节码表示
Class c2 = String.class;
// double 的类类型/字节码表示
Class c3 = double.class;
// Double 的类类型/字节码表示
Class c4 = Double.class;
// void 的类类型/字节码表示
// 凡是在类里面声明的关键字都有类类型
Class c5 = void.class;
```

## 4. Class 类的基本 API

### 4.1. 获取类的方法

```java
// 获取该类所有 public 的函数，包括从父类继承来的
Method[] ms = c.getMethods();
// 获取该类所有自己声明的方法，不问访问权限
Method[] ms2 = c.getDeclaredMethods();
```

### 4.2. 获取类的成员变量

```java
Field[] fs = c.getFields();
Field[] fs2 = c.getDeclaredFields();
```

### 4.3. 获取类的构造方法

```java
Constructor[] cs = c.getConstructors();
Constructor[] cs2 = c.getDeclaredConstructors();
```

## 5. 方法的反射

- 如何获取一个方法：方法的名称和方法的参数列表才能唯一确定某个方法
- 方法反射的操作：`method.invoke(object, params, ...)`

```java
class A {
    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + ", " + b.toLowerCase());
    }
}
```

### 5.1. 获取方法对象

```java
Method m = c.getMethod("print", new Class[]{int.class, int.class});
Method m2 = c.getMethod("print", int.class, int.class);
Method m3 = c.getMethod("print", String.class, String.class);
```

### 5.2. 方法反射

```java
m.invoke(a1, new Object[] { 10, 20 });
m2.invoke(a1, 10, 20);
m.invoke(a1, "Hello", "World");
```

## 6. 集合泛型的本质

- 泛型的本质
- 泛型什么时候有效

反射的操作都是编译之后的操作，编译之后集合的泛型是 **去泛型化** 的。Java 中集合的泛型是防止错误输入的，只在编译阶段有效，绕过编译就无效了。

```java
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
```

```java
true
2
[hello, 100]
```
