package xyz.zenheart.generator.enums;

import lombok.Getter;

/**
 * <p>项目名称: generator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/12/16 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
public enum MySqlTypeEnum {
    VARCHAR("String", "varchar", "");
    @Getter
    private final String javaType;
    @Getter
    private final String jdbcType;
    @Getter
    private final String importType;

    MySqlTypeEnum(String javaType, String jdbcType, String importType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.importType = importType;
    }
}
