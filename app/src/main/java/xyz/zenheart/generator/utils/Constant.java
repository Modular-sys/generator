package xyz.zenheart.generator.utils;

import xyz.zenheart.generator.pojo.entity.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 常量类 </p>
 * <p>创建时间: 2021/11/23 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
public class Constant {
    public static final Map<String, BaseEntity> GLOBAL = new ConcurrentHashMap<>();

    public static final String SETTING_ENTITY = "settingEntity";
}
