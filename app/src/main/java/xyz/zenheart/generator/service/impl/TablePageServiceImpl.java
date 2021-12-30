package xyz.zenheart.generator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zenheart.generator.enums.OrmEnum;
import xyz.zenheart.generator.pojo.dto.*;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.service.ITablePageService;
import xyz.zenheart.generator.service.factory.ServiceFactory;
import xyz.zenheart.generator.utils.Constant;
import xyz.zenheart.generator.utils.ConvertUtils;
import xyz.zenheart.generator.utils.FtlUtils;
import xyz.zenheart.generator.utils.PathUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    private SettingEntity setting;
    private TableDto tableDto;

    @Override
    public void processButtonDownload(TableDto tableDto) {
        this.tableDto = tableDto;
        List<TableDetailDto> details = ServiceFactory.tableInfoService().queryTableDetails(tableDto.getTableName());
        details = details.stream().peek(dto -> dto.setColumnName(ConvertUtils.lineToHump(dto.getColumnName()))).collect(Collectors.toList());
        log.info(details.toString());
        setting = Constant.setting();
        EntityDto entityDto = constructEntity(details);
        MapperDto mapperDto = constructMapper(entityDto);
        ServiceDto serviceDto = constructService(entityDto);
        ControllerDto controllerDto = constructController(entityDto);
    }

    private EntityDto constructEntity(List<TableDetailDto> details) {
        EntityDto entityDto = new EntityDto();
        OrmEnum ormEnum = OrmEnum.ENTITY;
        entityDto.setEntityPackage(ormEnum.packagePath());
        Set<String> importPackages = entityDto.getImportPackages();
        importPackages.add(ormEnum.importPath(tableDto.getTableName()));
        String superClass = setting.getSuperClass();
        if (Objects.isNull(superClass)) {
            entityDto.setSuperClass("");
            entityDto.setSuperClassName("");
        } else {
            entityDto.setSuperClass(superClass);
            String[] name = superClass.split("\\.");
            entityDto.setSuperClassName(name[name.length - 1]);
        }
        importPackages.add(setting.getSuperClass());
        entityDto.setClassName(ormEnum.className(tableDto.getTableName()));
        entityDto.setSuperClass(setting.getSuperClass());
        entityDto.setTableName(tableDto.getTableName());
        entityDto.setComment(tableDto.getDescribe());
        entityDto.setFields(details);
        String fileName = setting.getDirectoryLocation() + File.separator + entityDto.getClassName() + "Entity.java";
        FtlUtils.writeFile(entityDto, "entity.java.ftl", PathUtils.format(fileName));
        return entityDto;
    }

    private MapperDto constructMapper(EntityDto entityDto) {
        MapperDto mapperDto = new MapperDto();
        OrmEnum mapperEnum = OrmEnum.MAPPER;
        mapperDto.setMapperPackage(mapperEnum.getPath());
        mapperDto.setEntityPackage(entityDto.getEntityPackage());
        mapperDto.setEntityClass(entityDto.getClassName());
        mapperDto.setSuperMapperClassPackage("com.baomidou.mybatisplus.core.mapper");
        mapperDto.setSuperMapperClass("BaseMapper");
        mapperDto.setTableComment(entityDto.getComment());
        mapperDto.setAuthor("");
        mapperDto.setDate("");
        mapperDto.setTableMapperName(mapperEnum.className(entityDto.getTableName()));

        return mapperDto;
    }

    private ServiceDto constructService(EntityDto entityDto) {
        ServiceDto serviceDto = new ServiceDto();
        OrmEnum serviceEnum = OrmEnum.SERVICE;
        serviceDto.setServicePackage(serviceEnum.getPath());
        serviceDto.setEntityPackage(entityDto.getEntityPackage());
        serviceDto.setSuperServiceClassPackage("com.baomidou.mybatisplus.extension.service.IService");
        serviceDto.setTableComment(entityDto.getComment());
        serviceDto.setAuthor("");
        serviceDto.setDate("");
        serviceDto.setTableServiceName(serviceEnum.className(entityDto.getTableName()));
        serviceDto.setSuperServiceClass("IService");
        serviceDto.setEntityClass(entityDto.getClassName());
        return serviceDto;
    }

    private ControllerDto constructController(EntityDto entityDto) {
        ControllerDto controllerDto = new ControllerDto();
        OrmEnum controllerEnum = OrmEnum.CONTROLLER;
        controllerDto.setControllerPackage(controllerEnum.packagePath());
        controllerDto.setTableComment(entityDto.getComment());
        controllerDto.setAuthor("");
        controllerDto.setDate("");
        controllerDto.setRequestPath("");
        controllerDto.setControllerClassName(controllerEnum.className(entityDto.getTableName()));
        return controllerDto;
    }
}
