package xyz.zenheart.generator.enums;

import lombok.Getter;
import lombok.Setter;
import xyz.zenheart.generator.pojo.entity.SettingEntity;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/9/26 </p>
 *
 * @author CKM
 * @version v1.0
 */
public enum DataBaseEnum {
    MYSQL("mysql", new SettingEntity()),
    PGSQL("pgsql", new SettingEntity());
    @Getter
    private final String name;
    @Setter
    @Getter
    private SettingEntity setting;

    DataBaseEnum(String name, SettingEntity setting) {
        this.name = name;
        this.setting = setting;
    }

    public static DataBaseEnum getByName(String name) {
        for (DataBaseEnum data : DataBaseEnum.values()) {
            if (data.getName().equals(name)) {
                return data;
            }
        }
        return null;
    }
}
