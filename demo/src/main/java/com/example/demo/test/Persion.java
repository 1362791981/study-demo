package com.example.demo.test;

import java.util.Objects;
import java.util.stream.Stream;

public class Persion {

    int age = 1;
    String name = "zhaomx";

    @Override
    public int hashCode() {
        Object[] a = Stream.of("test1", 1).toArray();
        int result = 1;
        for (Object element : a) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }
    //*******hashCode计算源码*************
            /*public int hashCode() {
                int h = hash;
                if (h == 0 && value.length > 0) {
                    char val[] = value;
                    for (int i = 0; i < value.length; i++) {
                        h = 31 * h + val[i];
                    }
                    hash = h;
                }
                return h;
            }*/

    //*******重写equals方法*************
            /*因为如果不重写equals方法，当将自定义对象放到map或者set中时；如果这时两个对象的hashCode相同，就会调用equals方法进行比较，这个时候会调用Object中默认的equals方法，而默认的equals方法只是比较了两个对象的引用是否指向了同一个对象，显然大多数时候都不会指向，这样就会将重复对象存入map或者set中。这就 破坏了map与set不能存储重复对象的特性，会造成内存溢出 。

            重写equals方法的几条约定：

            自反性：即x.equals(x)返回true，x不为null；
            对称性：即x.equals(y)与y.equals(x）的结果相同，x与y不为null；
            传递性：即x.equals(y)结果为true, y.equals(z)结果为true，则x.equals(z)结果也必须为true；
            一致性：即x.equals(y)返回true或false，在未更改equals方法使用的参数条件下，多次调用返回的结果也必须一致。x与y不为null。
            如果x不为null, x.equals(null)返回false。
            我们根据上述规则来重写equals方法。*/

    @Override
    public boolean equals(Object o) {
        //先判断是否为同一对象
        if (this == o) {
            return true;
        }
        //再判断目标对象是否是当前类及子类的实例对象
        //注意：instanceof包括了判断为null的情况，如果o为null，则返回false
        if (!(o instanceof Persion)) {
            return false;
        }
        Persion persion = (Persion) o;
        return age == persion.age &&
                Objects.equals(name, persion.name);
    }
    //建议equals及hashCode两个方法，需要重写时，两个都要重写，一般都是将自定义对象放至Set中，或者Map中的key时，需要重写这两个方法。


    //*******toString*************
    @Override
    public String toString(){
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "override by zhaomx";
    }

    public Persion() {
    }

    public Persion(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
