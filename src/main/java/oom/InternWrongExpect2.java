package oom;

/**
 * intern 造成的不是期望的结果
 *
 * @author zhoufe
 * @date 2018/12/26 11:16
 */
public class InternWrongExpect2 {

    public static void main(String[] args) {
        //对象的引用
        String str1 = new StringBuilder("计算机软件").toString();
        //计算机软件 首次不在常量池中，intern()在记录对象的引用，并返回引用
        System.out.println(str1.intern() == str1);


        //对象的引用
        String str10 = new StringBuilder("1x").append("d").toString();
        //带拼接操作的，没有就创建常量池的实例对象，并返回引用，是堆里的引用和常量池的其实是同一个
        //计算机软件 首次不在常量池中，intern()在记录对象的引用，并返回引用
        System.out.println(str10.intern() == str10);


        //对象的引用
        String str2 = new StringBuilder("ja").append("va").toString();
        //java 是关键字 已经在常量池中，返回的是常量池中记录的对象的引用 和 新建的java不是同一个引用
        System.out.println(str2.intern() == str2);
        //false
    }
}
