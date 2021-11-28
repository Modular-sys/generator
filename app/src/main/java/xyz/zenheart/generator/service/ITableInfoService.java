package xyz.zenheart.generator.service;

import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.utils.Constant;

import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
public interface ITableInfoService {
    List<TableInfoEntity> queryTableInfo();

    default SettingEntity setting() {
        return (SettingEntity) Constant.GLOBAL.get((String) Constant.GLOBAL.get(Constant.SELECTED));
    }
}
