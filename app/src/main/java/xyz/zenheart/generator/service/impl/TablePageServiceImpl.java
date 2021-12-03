package xyz.zenheart.generator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zenheart.generator.pojo.dto.*;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.service.ITablePageService;
import xyz.zenheart.generator.service.factory.ServiceFactory;
import xyz.zenheart.generator.utils.Constant;

import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: table页面处理实现 </p>
 * <p>创建时间: 2021/12/3 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Service
public class TablePageServiceImpl implements ITablePageService {

    @Override
    public void processButtonDownload(TableDto tableDto) {
        List<TableDetailDto> details = ServiceFactory.tableInfoService().queryTableDetails(tableDto.getTableName());
        log.info(details.toString());
        SettingEntity setting = Constant.setting();
        EntityDto entityDto = constructEntity();
        MapperDto mapperDto = constructMapper();
        ServiceDto serviceDto = constructService();
        ControllerDto controllerDto = constructController();
    }

    private EntityDto constructEntity() {
        EntityDto entityDto = new EntityDto();
        entityDto.setEntityClass("");
        return entityDto;
    }

    private MapperDto constructMapper() {
        return new MapperDto();
    }

    private ServiceDto constructService() {
        return new ServiceDto();
    }

    private ControllerDto constructController() {
        return new ControllerDto();
    }
}
