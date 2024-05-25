package com.yupi.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import jdk.internal.loader.Resource;

public class MetaManager {
    private static volatile Meta meta;
    private MetaManager() {}
    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }
    private static Meta initMeta() {
        String metaJson= ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();

        return newMeta;
    }
}
