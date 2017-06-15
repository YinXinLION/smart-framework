package org.lion.aop.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 殷鑫 on 2017/5/26.
 */
public class ProxyChain {
    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] methodParams;

    private List<Proxy> proxyList = new ArrayList<Proxy>();//代理链
    private int proxyIndex = 0;//记录执行的位置
    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
    }
    public  Object[] getMethodParams(){
        return methodParams;
    }

    public Class<?> getTargetClass(){
        return targetClass;
    }
    public Method getTargetMethod(){
        return targetMethod;
    }

    /*  net.sf.cglib.proxy.Enhancer – 主要的增强类
    net.sf.cglib.proxy.MethodInterceptor – 主要的方法拦截类，它是Callback接口的子接口，需要用户实现
    net.sf.cglib.proxy.MethodProxy – JDK的java.lang.reflect.Method类的代理类，可以方便的实现对源对象方法的
    */
    public Object doProxyChain() throws Throwable{
        Object methodResult;
        if (proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else {
            methodResult = methodProxy.invokeSuper(targetObject,methodParams);
        }
        return methodResult;
    }

}
