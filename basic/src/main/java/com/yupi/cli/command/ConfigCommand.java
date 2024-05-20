package com.yupi.cli.command;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import com.yupi.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "config", description = "查看参数信息",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    public void run() {
        System.out.println("查看参数信息");
        //获取要打印的属性信息的类 获取类的所有字段
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("字段名称："+field.getName());
            System.out.println("字段类型："+field.getType());
            System.out.println("----");
        }


    }

}
