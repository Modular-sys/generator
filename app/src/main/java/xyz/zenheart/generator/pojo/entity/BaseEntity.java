package xyz.zenheart.generator.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>项目名称: CGenerator </p>
 * <p>描述: 基类 </p>
 * <p>创建时间: 2021/7/29 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
public abstract class BaseEntity implements Serializable {
    static final long serialVersionUID = 1L;
}
