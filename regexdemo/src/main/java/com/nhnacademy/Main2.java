package com.nhnacademy;

import java.util.Arrays;
import java.util.List;

public class Main2 {
    static List<String> classPathList;
    static List<String> ModuleList;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].trim().equals("--class-path") || args[i].trim().equals("-classpath")) {
                if (i + 1 == args.length) {
                    throw new IllegalArgumentException();
                }
                String[] classPathes = args[i + 1].split(";");
                classPathList = Arrays.asList(classPathes);
                i++;
            }

            if(args[i].trim().equals("--module")|| args[i].trim().equals("-m")){
                if(i+1==args.length){
                    throw new IllegalArgumentException();
                }
                String [] modules =args[i+1].split(",");
                ModuleList=Arrays.asList(modules);
                i++;
            }
        }
        if (classPathList != null) {
            System.out.println("-class-path");
            for (String classPath : classPathList) {
                System.out.println(classPath);
            }
        }

        if(ModuleList != null){
            System.out.println("--module");
            for (String module : ModuleList){
                System.out.println(module);
            }
        }
    }

}