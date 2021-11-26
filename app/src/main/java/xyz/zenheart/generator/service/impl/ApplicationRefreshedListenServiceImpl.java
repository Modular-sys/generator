package xyz.zenheart.generator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import xyz.zenheart.generator.modules.locale.service.ISettingService;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.service.IApplicationListenService;
import xyz.zenheart.generator.utils.Constant;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>项目名称: generator </p>
 * <p>描述: spring刷新监听服务 </p>
 * <p>创建时间: 2021/11/25 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Service
public class ApplicationRefreshedListenServiceImpl implements IApplicationListenService<ContextRefreshedEvent> {

    @Resource
    private ISettingService settingService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("监听上下文刷新");
        if (Objects.nonNull(event.getApplicationContext().getParent())) return;
        log.info("执行spring启动后任务");
        List<SettingEntity> settingEntities = settingService.searchAll();
        /*默认数据库中不会有默认被选中*/
        boolean selected = false;
        for (SettingEntity setting : settingEntities) {
            Constant.GLOBAL.put(setting.getDatabaseType(), setting);
            if (setting.isSelected()) {
                selected = true;
                Constant.GLOBAL.put(Constant.SELECTED, setting.getDatabaseType());
            }
        }
        if (selected) return;
        SettingEntity setting = settingEntities.get(0);
        settingService.selectedModify(setting.getDatabaseType());
        Constant.GLOBAL.put(Constant.SELECTED, setting.getDatabaseType());
    }
}
