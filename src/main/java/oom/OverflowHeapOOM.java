package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java内存溢出
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zhoufe
 * @date 2018/12/25 14:29
 */
public class OverflowHeapOOM {
    private static List<OOMObject> list = new ArrayList<OOMObject>();
    static class OOMObject {
    }

    public static void main(String[] args) {
        while (true) {
            list.add(new OOMObject());
        }
    }
}
