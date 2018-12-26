package oom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * VM Args:-XX:PermSize=10M-XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -XX:+PrintGCDetails
 *
 * @author zzm
 */
public class StringConstantPoolOOM {
    public static void main(String[] args) throws InterruptedException {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在integer范围内足够产生OOM了

        //JDK1.6开始把String常量池放入到Perm区，所以设置-XX:PermSize和-XX:MasPermSize的大小
        //JDK1.7+以后就没放在Metaspace，而是放在堆，Metaspace放引用，所以要换配置-Xms10M -Xmx10M -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -XX:+PrintGCDetails



        int i = 0;
        while (true) {
            //GC overhead limt exceed检查是Hotspot VM 1.6+定义的一个策略，通过统计GC时间来预测是否要OOM了，提前抛出异常，防止OOM发生。
            //会抛出Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            list.add(String.valueOf(i++).intern());//GC 时间间隔，太频繁，太快

            //会抛出Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            //list.add(UUID.randomUUID().toString().intern());//没办法分配空间来扩容List
        }
    }
}