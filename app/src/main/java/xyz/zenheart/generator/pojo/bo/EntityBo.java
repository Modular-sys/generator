package xyz.zenheart.generator.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
public class EntityBo {

    private List<FieldBo> fields = new ArrayList<>();

    private List<String> importPackages = new ArrayList<>();

    private String entityPackage;

    private String entityClass;

    private String superEntityClass;
}
