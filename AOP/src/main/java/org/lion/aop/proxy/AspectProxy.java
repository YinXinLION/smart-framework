package org.lion.aop.proxy;

import java.lang.reflect.Method;
import java.util.logging.MemoryHandler;

/**
 * Created by 殷鑫 on 2017/5/26.
 */
public abstract class AspectProxy implements Proxy{
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls,method,params)){
                before(cls,method,params);
                result = proxyChain.doProxyChain();
                after(cls,method,params,result);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            error(cls,method,params,e);
            throw e;
        }finally {
            end();
        }
        return result;
    }



    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return  true;
    }
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{

    }
    public void after(Class<?> cls,Method method,Object[] params,Object result){

    }
    public  void begin(){

    }
    public void error(Class<?> cls,Method method,Object[] params,Throwable e) throws Throwable{

    }
    public void end(){

    }
}
