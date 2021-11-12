package xyz.zenheart.generator.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/9/22 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
public class TableInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tableName;
    private String description;
}