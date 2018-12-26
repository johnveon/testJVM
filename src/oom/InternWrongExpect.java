package oom;

/**
 * intern 造成的不是期望的结果
 * @author zhoufe
 * @date 2018/12/26 11:16
 */
public class InternWrongExpect {

    public static void main(String[] args) {

        String s1 = new String("1234234$");
        //s1.intern();//1.7+没有就在常量池记录对象的引用并返回，有就返回对象的引用
        //String s2 = "1234234$";
        System.out.println((s1.intern() == s1));

        //为什么拼接的字符串是ture
        String s3 = new String("vmmv") + new String("mvvm");
        //s3.intern();//没有就在常量池记录对象的引用并返回，有就返回对象的引用
       //String s4 = "vmmvmvvm";
        System.out.println((s3 == s3.intern()));

        //为什么拼接的字符串是ture
        String s5 = new StringBuilder().append("111").append("444").toString();//还是一个111444 String
        //s5.intern();
        //String s6 = "111444";
        System.out.println((s5 == s5.intern()));//是首次，但是有拼接操作，这个在常量池创建的对象的引用就是刚刚创建对象的引用

        //实例对象的引用
        String s7 = new StringBuilder().append("111").toString();
        //s7.intern();
        //String s8 = "111";
        System.out.println(s7 == s7.intern());//s7.intern() 常量池的对象的引用，不可能相等
        System.out.println(s7 == "111");


    }
}
