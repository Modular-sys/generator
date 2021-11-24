package xyz.zenheart.generator.modules.locale.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.zenheart.generator.pojo.entity.SettingEntity;

import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类mapper </p>
 * <p>创建时间: 2021/11/24 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Mapper
public interface ISettingMapper {
    List<SettingEntity> searchAll();
}
