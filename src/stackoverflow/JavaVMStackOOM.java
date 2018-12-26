package stackoverflow;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * VM Args:-Xss2M(这时候不妨设置大些)
 *
 * @author zzm
 */
public class JavaVMStackOOM {
    private static AtomicInteger count = new AtomicInteger(0);
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() +" - "+ count.incrementAndGet());
                        dontStop();
                    }
            });
            thread.start();
        }
    }

    public static void main( String[] args) throws Throwable {
        //但是，如果是建立过多线程导致的内存溢出，在不能减少线程数或者更换64位虚拟机的情况下，就只能通过  减少最大堆  和  减少栈容量  来换取更多的线程。
        // 如果没有这方面的处理经验，这种通过“减少内存”的手段来解决内存溢出的方式会比较难以想到。
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}