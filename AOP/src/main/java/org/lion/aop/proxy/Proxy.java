package org.lion.aop.proxy;

/**
 * Created by 殷鑫 on 2017/5/26.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
