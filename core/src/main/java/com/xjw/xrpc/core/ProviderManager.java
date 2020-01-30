package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderManager {
    //在服务端会有多个请求接入，每个请求所在线程都会访问此map获得相应provider，因此用concurrenthashmap
    private static Map<String,XProvider> providers=new ConcurrentHashMap<String, XProvider>();

    public static XProvider getProvider(String name){return providers.get(name);}

    public static void putProvider(String name,XProvider provider){providers.put(name,provider);}
}
