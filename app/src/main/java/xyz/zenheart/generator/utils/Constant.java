package xyz.zenheart.generator.utils;

import xyz.zenheart.generator.pojo.entity.SettingEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 常量类 </p>
 * <p>创建时间: 2021/11/23 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class Constant {
    public static final Map<String, Object> GLOBAL = new ConcurrentHashMap<>();

    public static final String SELECTED = "selected";

    public static final boolean FALSE = false;

    public static SettingEntity setting() {
        return (SettingEntity) Constant.GLOBAL.get((String) Constant.GLOBAL.get(SELECTED));
    }
}
