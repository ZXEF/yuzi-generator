package com.yupi.maker.meta.enums;

public enum FileTypeEnum {
    DIR("目录","dir"),
    FILE("文件","file");
    private String name;
    private String value;
    FileTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }


}
