package xyz.zenheart.generator.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 表字段信息 </p>
 * <p>创建时间: 2021/11/30 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDetailDto {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * 是否为空
     */
    private String isNullAble;
    /**
     * 是否主键
     */
    private String isPrimary;
    /**
     * 列描述
     */
    private String columnDescription;
}
