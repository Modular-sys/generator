package com.robot.devops.entity;

    import com.robot.devops.entity.entity.DeviceEntity;
    import com.robot.base.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
* <p></p>
*
* @author 
* @since 
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("d_device")
public class DeviceEntity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private int8 ;
    /**
    * 设备类型编号 {根设备表匹配}
    */
    private int8 ;
    /**
    * 使用部门
    */
    private int8 ;
    /**
    * 设备ip
    */
    private varchar ;
    /**
    * ping在线状态 {0 离线 1在线，2创建}
    */
    private int2 ;
    /**
    * 设备编码
    */
    private varchar ;
    /**
    * 设备名称
    */
    private varchar ;
    /**
    * 创建人
    */
    private varchar ;
    /**
    * 创建时间
    */
    private timestamp ;
    /**
    * 更新人
    */
    private varchar ;
    /**
    * 更新时间
    */
    private timestamp ;
    /**
    * [
  {
    "id": "111",
    "label": "标签分类",
    "data": [
      {
        "id": 1111111,
        "label": "开始时间",
        "remark": "YYY-MM-DD",
        "inputType": "3",
        "inputValue": "",
        "validateRule": "YYY-MM-DD",
        "sysRequired": true,
        "customRequired": true
      }
    ]
  }
]
    */
    private json ;
    /**
    * 0 未使用 1使用中 2 已停用 3 已报废
    */
    private int2 ;
    /**
    * 管理归属
    */
    private int8 ;
    /**
    * 监测服务器账号
    */
    private varchar ;
    /**
    * 端口
    */
    private int2 ;
    /**
    * 监测状态 0关闭 1开启
    */
    private int2 ;
    /**
    * telnet在线状 {0 离线 1在线,2创建}
    */
    private int2 ;

}
