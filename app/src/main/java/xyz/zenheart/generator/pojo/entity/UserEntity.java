package xyz.zenheart.generator.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 用户类 </p>
 * <p>创建时间: 2021/8/3 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    private String name;
    private String pwd;
}

