package com.yupi.maker.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticFileGenerator {

    public static void copyFilesByHutool(String inputPath,String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }





}
