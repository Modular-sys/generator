package xyz.zenheart.generator.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * <p>项目名称: generator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/12/16 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
public enum PgSqlTypeEnum {
    VARCHAR("String", "varchar", ""),
    BPCHAR("String", "bpchar", ""),
    TEXT("String", "text", ""),
    JSON("String", "json", ""),
    JSONB("String", "jsonb", ""),
    FLOAT4("Float", "float4", ""),
    FLOAT8("Double", "float8", ""),
    MONEY("Double", "money", ""),
    NUMERIC("BigDecimal", "numeric", "java.math.BigDecimal"),
    INT2("Integer", "int2", ""),
    INT4("Integer", "int4", ""),
    INT8("Long", "int8", ""),
    DATE("LocalDate", "date", "java.time.LocalDate"),
    TIME("LocalTime", "time", "java.time.LocalTime"),
    TIMESTAMP("LocalDateTime", "timestamp", "java.time.LocalDateTime"),
    TIMESTAMPTZ("OffsetDateTime", "timestamptz", "java.time.OffsetDateTime"),
    BOOL("Boolean", "bool", ""),
    DEFAULT("Object", "", "");
    @Getter
    private final String javaType;
    @Getter
    private final String jdbcType;
    @Getter
    private final String importType;

    PgSqlTypeEnum(String javaType, String jdbcType, String importType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.importType = importType;
    }

    public static PgSqlTypeEnum select(String jdbcType) {
        return Arrays.stream(values()).filter(pgSqlTypeEnum -> pgSqlTypeEnum.jdbcType.equals(jdbcType))
                .findFirst().orElse(DEFAULT);
    }
}
