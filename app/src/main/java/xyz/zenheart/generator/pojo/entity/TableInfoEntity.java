package xyz.zenheart.generator.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/9/22 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TableInfoEntity extends BaseEntity {

    private String tableName;
    private String description;
}

