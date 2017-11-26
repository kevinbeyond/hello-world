package basic.jvm;

/**
 * Created by Leo on 2017/10/24.
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1==s2);//true

        String s3 = new String("hello");//在Heap中创建了一个新对象
        String s4 = new String("hello");//在Heap中创建了一个新对象
        System.out.println(s2==s3);//false
        System.out.println(s3==s4);//false

        /**
         * s1、s3不是同一个对象，但是却有相同的hashCode
         */
        System.out.println(s1==s3);//false
        System.out.println(s1.hashCode()==s3.hashCode());//true

        /**
         * 2个不同的字符串hashCode值确是相同的
         * 是由String.hashCode()的算法造成的
         * 两个String的hashCode相同并不代表着equals比较时会相等，他们两者之间是没有必然关系
         */
        String a = "Aa";
        String b = "BB";
        System.out.println(a.hashCode() == b.hashCode());//true
    }
}
