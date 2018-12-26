package oom;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * jvm方法区内存溢出
 * VM Args:-XX:PermSize=10M-XX:MaxPermSize=10M
 * JDK7+ -XX:MaxMetaspaceSiz=10M
 *
 * @author zzm
 */
public class JVMMethodAreaOOM {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            //一直在创建新的类信息，并放在Metaspace
            enhancer.create();
        }
    }

    static class OOMObject {
    }
}
