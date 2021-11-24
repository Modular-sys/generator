package xyz.zenheart.generator.modules.locale.service;

import xyz.zenheart.generator.pojo.entity.SettingEntity;

import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/11/24 </p>

 *
 * @author CKM
 * @version v1.0
 */
public interface ISettingService {
    List<SettingEntity> searchAll();
}
