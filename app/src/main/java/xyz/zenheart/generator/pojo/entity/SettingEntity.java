package xyz.zenheart.generator.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/10/12 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SettingEntity extends BaseEntity {

    private String directoryLocation;

    /**
     * 数据库地址例如 127.0.0.1:3306
     */
    private String databaseUrl;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 概要/计划/模式/纲要
     */
    private String schema;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 包基础路径
     */
    private String packagePath;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 父类
     */
    private String superClass;

    /**
     * 是否选中
     */
    private boolean selected;
}
