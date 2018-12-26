package stackoverflow;

/**
 * 线程栈的栈溢出
 * VM Args:-Xss128k
 *
 * @author zhoufe
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            //HotSpot JVM现实的时候不区分虚拟机栈和本地方法栈，对于HotSpot来说-Xoss设置本地方法栈无效。
            //但可以设置栈容量：-Xss

            //关于虚拟机栈和本地方法有两种异常：
            //1.如果线程请求的栈深度，大于设置的最大栈容量，抛出StackOverflowERROR
            //2.如果虚拟机在申请扩展栈时无法申请到足够的内存空间，抛出OutOfMemoryError，但是很难重现！

            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}