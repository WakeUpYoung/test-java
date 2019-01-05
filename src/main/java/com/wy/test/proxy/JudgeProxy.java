package com.wy.test.proxy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class JudgeProxy {

    private List<Class> getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".","/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll((Collection<? extends Class>) findClass(directory, packageName));
        }
        return classes;

    }

    private List<Class> findClass(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        String path1 = directory.getAbsolutePath();
        if (path1.contains("%20"))
            directory = new File(path1.replace("%20"," "));
        if (!directory.exists()) {
            System.out.println("文件夹不存在:"+directory.getAbsolutePath());
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add((Class) Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    private List<Class> getInterfaceByPackage(String packagePath) throws IOException, ClassNotFoundException {
        List<Class> classList = new ArrayList<>();
        List<Class> allClass = getClasses(packagePath);
        for (Class allClas : allClass) {
            if (allClas.isInterface()) {
                classList.add(allClas);
            }
        }
        return classList;
    }

    @SuppressWarnings("unchecked")
    public Object getProxy(String path, Object o) throws IOException, ClassNotFoundException {
        List<Class> allInterface= getInterfaceByPackage(path);
//        System.out.println(allInterface.size());
        for (Class clazz : allInterface){
            // System.out.println("interface:"+clazz.getName());
            if (clazz.isAssignableFrom(o.getClass())){
                return new JDKProxy().newProxy(o);
            }else {
                return new CGLibProxy().getProxy(o.getClass());
            }
        }
        return null;
    }



    }
