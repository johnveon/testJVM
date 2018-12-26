package stacks;

/**
 * 查看栈帧等信息 javap stacks.DemoTest.class
 * @author zhoufe
 * @date 2018/12/24 15:32
 */
public class DemoTest {

    public static void main(String[] args) {
        int a = 100;
        int b = 200;
        a++;
        int c = a + b;
    }
}
