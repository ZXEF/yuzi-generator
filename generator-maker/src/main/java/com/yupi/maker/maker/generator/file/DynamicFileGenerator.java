package com.yupi.maker.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class DynamicFileGenerator {

    public static void doGenerate(String inputPath, String outputPath,Object model ) throws IOException, TemplateException {
        //new出Configuration对象 参数为2_3_23版本  freemarker版本号
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        //设置模板文件所在目录
        File templateDir=new File(inputPath).getParentFile();
        System.out.println("sout:"+templateDir.getAbsolutePath());
        configuration.setDirectoryForTemplateLoading(templateDir);

        //设置默认编码
        configuration.setDefaultEncoding("UTF-8");
        //获取模板文件/创建数据模型
        String templateName=new File(inputPath).getName();
        Template template=configuration.getTemplate(templateName);

        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);

        }
//
        Writer out =new FileWriter(outputPath);
        template.process(model, out); //输出文件
//        关闭
        out.close();
    }

}
