package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath="D:\\Dy\\yupi\\generator\\yupi-generator\\acm-template-pro";
        String outputRootPath="D:\\Dy\\yupi\\generator\\yupi-generator";

        String inputPath;
        String outputPath;
        inputPath=new File(inputRootPath,"src/main/java/com/yupi/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"acm-template/src/main/java/com/yupi/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath=new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath=new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath=new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath=new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);


//        String projectPath = System.getProperty("user.dir");
//        File parentFile = new File(projectPath).getParentFile();
//        String inputPath =new File(parentFile, "acm-template").getAbsolutePath();
//        String outputPath =projectPath;
//        System.out.println("inputPath:"+inputPath);
//        System.out.println("outputPath:"+outputPath);
//        StaticGenerator.copyFilesByByRecursive(inputPath, outputPath);
//        String inputDynamicFilePath =projectPath+File.separator+"src/main/resources/templates/MainTemplate.java.ftl";
//        String outputDynamicFilePath =outputPath+File.separator+"acm-template/src/main/java/com/yupi/acm/MainTemplate.java";
//        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);


    }
    public static void main(String[] args) throws TemplateException, IOException {

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ZXEZ");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果:11");
        doGenerate(mainTemplateConfig);
//        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
//        mainTemplateConfig.setAuthor("ZXEZ");
//        mainTemplateConfig.setLoop(false);
//        mainTemplateConfig.setOutputText("求和结果:22");
//        doGenerate(mainTemplateConfig);

    }
}
