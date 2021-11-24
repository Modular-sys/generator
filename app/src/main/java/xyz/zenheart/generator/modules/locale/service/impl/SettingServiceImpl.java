package xyz.zenheart.generator.modules.locale.service.impl;

import org.springframework.stereotype.Service;
import xyz.zenheart.generator.modules.locale.mapper.ISettingMapper;
import xyz.zenheart.generator.modules.locale.service.ISettingService;
import xyz.zenheart.generator.pojo.entity.SettingEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类ServiceImpl </p>
 * <p>创建时间: 2021/11/24 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Service
public class SettingServiceImpl implements ISettingService {

    @Resource
    private ISettingMapper settingMapper;

    @Override
    public List<SettingEntity> searchAll() {
        return settingMapper.searchAll();
    }
}
