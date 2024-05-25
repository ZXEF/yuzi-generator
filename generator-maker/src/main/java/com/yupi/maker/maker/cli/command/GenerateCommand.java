package com.yupi.maker.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.maker.maker.generator.file.FileGenerator;
import com.yupi.maker.maker.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "generate", description = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-l","--loop"},arity = "0..1",description = "是否循环生成代码",interactive = true,echo = true)
    private boolean loop;
    @CommandLine.Option(names = {"-a","--author"},arity = "0..1",description = "作者",interactive = true,echo = true)
    private String author="zxez";
    @CommandLine.Option(names = {"-o","--outputTtext"},arity = "0..1",description = "输出文本",interactive = true,echo = true)
    private String outputText="sum =";
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        System.out.println("配置信息"+ dataModel);
        FileGenerator.doGenerate(dataModel);
        return 0;
    }


}
