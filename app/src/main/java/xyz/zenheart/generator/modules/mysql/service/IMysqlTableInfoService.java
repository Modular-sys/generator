package xyz.zenheart.generator.modules.mysql.service;

import xyz.zenheart.generator.pojo.entity.TableInfoEntity;

import java.util.List;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: mysql表信息接口 </p>
 * <p>创建时间: 2021/9/22 </p>
 *
 * @author CKM
 * @version v1.0
 */
public interface IMysqlTableInfoService {

    List<TableInfoEntity> queryTableInfo(String schema);
}
