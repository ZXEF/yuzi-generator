package com.yupi.maker.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yupi.maker.maker.generator.file.DynamicFileGenerator;
import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        String projectPath = System.getProperty("user.dir");
        String outputPath=projectPath+ File.separator+"generated"+File.separator+meta.getName();
        if (!FileUtil.exist(outputPath)){
            FileUtil.mkdir(outputPath);
        }

        //读取resources
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath=classPathResource.getAbsolutePath();

        String outputBasePackage= meta.getBasePackage();
        String outputBasePackagePath= StrUtil.join("/",StrUtil.split(outputBasePackage,"."));
        String outputBaseJavaPackagePath= outputPath+File.separator+"src/main/java/"+outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        inputFilePath=inputResourcePath+File.separator+"templates/java/model/DataModel.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+"/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);





    }
}
