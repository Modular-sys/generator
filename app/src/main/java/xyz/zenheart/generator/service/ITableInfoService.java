package xyz.zenheart.generator.service;

import xyz.zenheart.generator.pojo.entity.TableInfoEntity;

import java.util.List;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
public interface ITableInfoService {
    List<TableInfoEntity> queryTableInfo(String schema);
}
