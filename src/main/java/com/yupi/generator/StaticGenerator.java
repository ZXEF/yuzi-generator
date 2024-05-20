package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath);
        System.out.println(projectPath);
        String inputPath = new File(parentFile,"yuzi-generator-demo-project/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        copyFilesByHutool(inputPath,outputPath);

    }
    public static void copyFilesByHutool(String inputPath,String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void copyFilesByByRecursive(String inputPath,String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFilesByByRecursive(inputFile, outputFile);
        }catch (Exception e) {
            System.out.println("复制文件失败");
            e.printStackTrace();
        }
    }


    public static void copyFilesByByRecursive(File inputFile,File outputFile) throws Exception {

        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile =new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }

            File[] files = inputFile.listFiles();
            if( ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                copyFilesByByRecursive(file,destOutputFile);
            }
        }else{
                Path destPath = outputFile.toPath().resolve(inputFile.getName());
                Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
