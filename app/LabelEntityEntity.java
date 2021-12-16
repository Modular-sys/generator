package com.robot.devops.entity;

    import com.robot.devops.entity.entity.LabelEntity;
    import com.robot.base.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
* <p>标签管理表</p>
*
* @author 
* @since 
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("d_label")
public class LabelEntity extends BaseEntity {

    /**
    * 标签id
    */
    @TableId(type = IdType.AUTO)
    private int8 ;
    /**
    * 标签名称
    */
    private varchar ;
    /**
    * 创建人
    */
    private int8 ;
    /**
    * 创建时间
    */
    private timestamp ;
    /**
    * 更新人
    */
    private int8 ;
    /**
    * 更新时间
    */
    private timestamp ;
    private bool ;

}
