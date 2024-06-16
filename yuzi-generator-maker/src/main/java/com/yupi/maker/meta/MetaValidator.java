package com.yupi.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.yupi.maker.meta.enums.FileGenerateTypeEnum;
import com.yupi.maker.meta.enums.FileTypeEnum;
import com.yupi.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);
        // modelConfig校验和默认值
        validAndFillModelConfig(meta);

    }

    public static void validAndFillMetaRoot(Meta meta) {
        String name = meta.getName();
        if(StrUtil.isBlank(name)){
            name = "my-generator";
            meta.setName(name);
        }
        String description = meta.getDescription();
        if(StrUtil.isEmpty(description)){
            description="我的模板代码生成器";
            meta.setDescription(description);
        }
        String author = meta.getAuthor();
        if (StrUtil.isEmpty(author)){
            author = "zxcz";
            meta.setAuthor(author);
        }

        String basePackage = meta.getBasePackage();
        if (StrUtil.isEmpty(basePackage)){
            basePackage = "com.yupi";
            meta.setBasePackage(basePackage);
        }

        String version = meta.getVersion();
        if(StrUtil.isEmpty(version)){
            version = "1.0.0";
            meta.setVersion(version);
        }

        String createTime = meta.getCreateTime();
        if(StrUtil.isEmpty(createTime)){
            createTime = DateUtil.now();
            meta.setCreateTime(createTime);
        }
    }

    public static void validAndFillFileConfig(Meta meta) {
        //fileConfig校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if(fileConfig!=null){
            //sourceRootPath：必填
            String sourceRootPath=fileConfig.getSourceRootPath();
            if(StrUtil.isBlank(sourceRootPath)){
                throw  new MetaException("未填写sourceRootPath");
            }
            //inputRootPath：默认值：.source+sourceRootPath最后一级目录
            String inputRootPath= fileConfig.getInputRootPath();
            String defaultOutputPath= ".source"+ File.separator+ FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
            if (StrUtil.isEmpty(inputRootPath)){
                fileConfig.setInputRootPath(defaultOutputPath);
            }
            //outputRootPath：默认为当前路径下的generated
            String outputRootPath= fileConfig.getOutputRootPath();
            String defaultOutputRootPath="generated";
            if (StrUtil.isEmpty(outputRootPath)){
                fileConfig.setOutputRootPath(defaultOutputRootPath);
            }
            String fileConfigType= fileConfig.getType();
            String defaultType= FileTypeEnum.DIR.getValue();
            if(StrUtil.isEmpty(fileConfigType)){
                fileConfig.setType(defaultType);
            }
            //fileInfo 默认值
            List<Meta.FileConfig.FileInfo> fileInfoList= fileConfig.getFiles();
            if (CollectionUtil.isNotEmpty(fileInfoList)){
                for (Meta.FileConfig.FileInfo fileInfo : fileInfoList){
                    // inputPath：必填
                    String inputPath=fileInfo.getInputPath();
                    if(StrUtil.isBlank(inputPath)){
                        throw  new MetaException("未填写inputPath");
                    }
                    // outputPath：默认值：inputPath
                    String outputPath=fileInfo.getOutputPath();
                    if(StrUtil.isEmpty(outputPath)){
                        fileInfo.setOutputPath(inputPath);
                    }
                    //type: 默认inputPath 有文件后缀（如.java）为file,否则为dir
                    String type =fileInfo.getType();
                    if (StrUtil.isBlank(type)){
                        //无文件后缀
                        if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                            fileInfo.setType(FileTypeEnum.DIR.getValue());
                        }else {
                            fileInfo.setType(FileTypeEnum.FILE.getValue());
                        }
                    }
                    //generate：如果文件结尾不为ftl，则generate默认为 static,否则为dynamic
                    String generateType=fileInfo.getGenerateType();
                    if (StrUtil.isBlank(generateType)){
                        if (inputPath.endsWith(".ftl")){
                            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                        }
                        else {
                            fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                        }
                    }
                }
            }

        }
    }

    public static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if(modelConfig!=null){
            List<Meta.ModelConfig.ModelInfo> modelInfoList= modelConfig.getModels();
            if (CollectionUtil.isNotEmpty(modelInfoList)){
                for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList){
                //输出路径默认值
                    String fieldName =modelInfo.getFieldName();
                    if(StrUtil.isBlank(fieldName)){
                        throw  new MetaException("未填写fieldName");
                    }
                    String modelInfoType =modelInfo.getType();
                    if(StrUtil.isEmpty(modelInfoType)){
                        modelInfo.setType(ModelTypeEnum.STRING.getValue());
                    }

                }
            }




        }
    }

}
