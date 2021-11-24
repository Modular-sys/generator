package xyz.zenheart.generator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import xyz.zenheart.generator.modules.locale.service.ISettingService;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.utils.Constant;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 监听spring启动完成后 </p>
 * <p>创建时间: 2021/11/24 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Component
public class ApplicationListenHandler implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private ISettingService settingService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("启动类");
        /*防止重复执行*/
        if (event.getApplicationContext().getParent() == null) {
            log.info("执行spring启动后任务");
            List<SettingEntity> settingEntities = settingService.searchAll();
            log.info(settingEntities.toString());
            for (SettingEntity setting : settingEntities) {
                Constant.GLOBAL.put(setting.getDatabaseType(), setting);
                if (setting.isSelected()) {
                    Constant.GLOBAL.put(Constant.SELECTED, setting.getDatabaseType());
                }
            }
        }
    }
}
