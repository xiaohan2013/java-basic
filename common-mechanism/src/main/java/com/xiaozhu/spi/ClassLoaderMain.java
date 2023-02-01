package com.xiaozhu.spi;

import sun.misc.CompoundEnumeration;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderMain {
    //获取资源的方法，读取jar包中的资源文件
//    public static Enumeration<URL> getResources(String name) throws IOException {
//        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
//        if (parent != null) {
//            tmp[0] = parent.getResources(name);
//        } else {
//            tmp[0] = getBootstrapResources(name);
//        }
//        tmp[1] = findResources(name);
//        return new CompoundEnumeration<>(tmp);
//    }

    public static void main(String[] args) throws IOException {
        System.out.println("======");
        String name = "java/sql/Array.class";
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.toString());
        }
    }
}
