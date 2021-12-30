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
public class ControllerDto {
    /**
     *
     */
    private String controllerPackage;
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
    private String requestPath;
    /**
     *
     */
    private String controllerClassName;
}
