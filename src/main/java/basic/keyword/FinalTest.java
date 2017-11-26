package basic.keyword;

/**
 * Created by Leo on 2017/10/24.
 */
public class FinalTest {

    public static void main(String[] args) {
        String s1 = "hello2";

        String s2 = "hello";//常量池
        final String s3 = "hello";//常量池

        System.out.println(s2==s3);//true，都指向常量池
        System.out.println(s2.hashCode() + "&&" + s3.hashCode());

        String s4 = s2 + 2;//s2是引用，编译期不确定其值，s4指向堆内存
        System.out.println(s4==s1);//false

        String s5 = s3 + 2;//编译期即确定值，s5指向常量池
        System.out.println(s5==s1);//true

        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();

        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);//j的值不变，i的值不同
    }
}

class MyClass {
    public final double i = Math.random();
    public static double j = Math.random();
}
