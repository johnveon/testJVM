package oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出，不指定MaxDirectMemorySize就和堆内存一样大小
 * VM Args:-Xmx20M-XX:MaxDirectMemorySize=10M
 *
 * @author zzm
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        //Exception in thread "main" java.lang.OutOfMemoryError
        //	at sun.misc.Unsafe.allocateMemory(Native Method)

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}