package org.lion.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 殷鑫 on 2017/5/26.
 */
public class ProxyManager {
    @SuppressWarnings("uncheck")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T)Enhancer.create(targetClass,new MethodInterceptor(){

            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,targetObject,targetMethod,methodProxy,methodParams,proxyList).doProxyChain();
            }
        });

    }
}
