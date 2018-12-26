package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * jvm内存泄漏
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zhoufe
 * @date 2018/12/25 14:29
 */
public class LeakHeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
