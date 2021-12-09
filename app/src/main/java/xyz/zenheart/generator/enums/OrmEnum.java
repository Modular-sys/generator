package xyz.zenheart.generator.enums;

import lombok.Getter;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.utils.Constant;
import xyz.zenheart.generator.utils.ConvertUtils;
import xyz.zenheart.generator.utils.FieldUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 数据层orm枚举 </p>
 * <p>创建时间: 2021/12/8 </p>
 *
 * @author CKM
 * @version v1.0
 */
public enum OrmEnum {

    ENTITY("", "Entity", "entity"),
    MAPPER("I", "Mapper", "mapper"),
    SERVICE("I", "Service", "service"),
    SERVICE_IMPL("", "ServiceImpl", "service.impl"),
    CONTROLLER("", "Controller", "controller");

    OrmEnum(String prefix, String suffix, String path) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.path = path;
    }

    @Getter
    private final String prefix;
    @Getter
    private final String suffix;
    @Getter
    private final String path;

    public String className(String tableName) {
        return prefix + classPrefix(tableName) + suffix;
    }

    public String className(String tableName, boolean is) {
        return (is ? prefix : "") + classPrefix(tableName) + suffix;
    }

    public String classPrefix(String tableName) {
        SettingEntity setting = Constant.setting();
        return Optional.ofNullable(tableName).map(s -> {
            String tablePrefix = setting.getTablePrefix();
            s = s.replaceFirst(Objects.isNull(tablePrefix) ? "" : tablePrefix, "");
            return FieldUtils.convertChar(ConvertUtils.lineToHump(s), 0, FieldUtils.LOWER_TO_UPPER);
        }).orElse("");
    }

    public String packagePath() {
        SettingEntity setting = Constant.setting();
        return String.join(".", new String[]{setting.getPackagePath(), setting.getModuleName(), path});
    }

    public String importPath(String tableName) {
        return String.join(".", new String[]{packagePath(), path, className(tableName)});
    }
}
