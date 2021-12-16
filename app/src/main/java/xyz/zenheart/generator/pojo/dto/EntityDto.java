package xyz.zenheart.generator.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>项目名称: generator </p>
 * <p>描述: freemarker处理表业务对象 </p>
 * <p>创建时间: 2021/9/6 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityDto {

    private List<TableDetailDto> fields = new ArrayList<>();

    private Set<String> importPackages = new LinkedHashSet<>();

    private String entityPackage;

    private String className;

    private String superClass;

    private String superClassName;

    private String tableName;

    private String comment;
}
