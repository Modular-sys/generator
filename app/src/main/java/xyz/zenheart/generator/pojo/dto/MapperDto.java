package xyz.zenheart.generator.pojo.dto;

import lombok.Data;

/**
 * <p>项目名称: generator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/12/3 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Data
public class MapperDto {

    /**
     *
     */
    private String mapperPackage;
    /**
     *
     */
    private String entityPackage;
    /**
     *
     */
    private String entityClass;
    /**
     *
     */
    private String superMapperClassPackage;
    /**
     *
     */
    private String tableComment;
    /**
     *
     */
    private String author;
    /**
     *
     */
    private String date;
    /**
     *
     */
    private String tableMapperName;
    /**
     *
     */
    private String superMapperClass;
}
