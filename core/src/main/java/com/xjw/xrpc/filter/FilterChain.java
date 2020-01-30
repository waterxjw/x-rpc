package com.xjw.xrpc.filter;

import com.xjw.xrpc.core.Invoker;

import java.util.List;

/**
 * 负责将多个filter与原始invoker逐层组装，得到最终的invoker
 */
public class FilterChain {

    private List<Filter> filters;

    private Invoker originInvoker;

    public FilterChain(Invoker originInvoker,List<Filter> filters){
        this.originInvoker=originInvoker;
        this.filters=filters;
    }

    /**
     *获取组装好的 带过滤器链的invoker
     */
    public Invoker getWrappedInvoker(){
        Invoker current=originInvoker;//原始invoker封装在最底层，也就是最终执行
        for (int i=filters.size()-1;i>=0;i--){
            //倒序组装，使得第一个filter在最外层，最先执行
            current=new FilterInvoker(filters.get(i),current);
        }
        return current;
    }
}
