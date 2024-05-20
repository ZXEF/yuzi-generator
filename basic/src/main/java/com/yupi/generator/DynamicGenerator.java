package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class DynamicGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator +"basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outPath=projectPath + File.separator +"MainTemplate.java";
        MainTemplateConfig mainTemplateConfig=new MainTemplateConfig();
//        设置名字 循环否 输出
        mainTemplateConfig.setAuthor("ZXEZ");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果1:");
        doGenerate(inputPath, outPath,mainTemplateConfig);

    }
    public static void doGenerate(String inputPath, String outputPath,Object model ) throws IOException, TemplateException {
        //new出Configuration对象 参数为2_3_23版本  freemarker版本号
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        //设置模板文件所在目录
        File templateDir=new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        configuration.setDefaultEncoding("UTF-8");
        //获取模板文件/创建数据模型
        String templateName=new File(inputPath).getName();
        Template template=configuration.getTemplate(templateName);

        //创建数据模型
        MainTemplateConfig mainTemplateConfig=new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ZXEZ");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果3:");
//
        Writer out =new FileWriter(outputPath);
        template.process(model, out);
//        关闭
        out.close();
    }

}
