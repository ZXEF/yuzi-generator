package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath =new File(parentFile, "yupi-generator/yuzi-generator-demo-project/acm-template").getAbsolutePath();
        String outputPath =projectPath;
        System.out.println("inputPath:"+inputPath);
        System.out.println("outputPath:"+outputPath);
        StaticGenerator.copyFilesByByRecursive(inputPath, outputPath);
        String inputDynamicFilePath =projectPath+File.separator+"yuzi-generator-demo-project/acm-template/src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath =outputPath+File.separator+"yuzi-generator-demo-project/acm-template/src/main/java/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);


    }
    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ZXEZ");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果:22");
        doGenerate(mainTemplateConfig);

    }
}
