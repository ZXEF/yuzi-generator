package com.yupi.maker.maker.generator.file;

import com.yupi.maker.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {

        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath =new File(parentFile, "acm-template").getAbsolutePath();
        String outputPath =projectPath;
        System.out.println("inputPath:"+inputPath);
        System.out.println("outputPath:"+outputPath);
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        String inputDynamicFilePath =projectPath+File.separator+"src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath =outputPath+File.separator+"acm-template/src/main/java/com/yupi/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);


    }
    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("ZXEZ");
        dataModel.setLoop(false);
        dataModel.setOutputText("求和结果:22");
        doGenerate(dataModel);

    }
}
