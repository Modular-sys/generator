package ${entityPackage};

<#list importPackages as pkg>
    import ${pkg!};
</#list>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
* <p>${comment!}</p>
*
* @author ${author!}
* @since ${date!}
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${tableName}")
<#if superClass??>
public class ${className!} extends ${superClassName} {
<#else>
public class ${className!} implements Serializable {
</#if>

<#if superEntityClass??>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fields as field>
    <#if field.columnDescription!?length gt 0>
    /**
    * ${field.columnDescription}
    */
    </#if>
    <#if field.primary>
    <#-- 主键 -->
    @TableId(type = IdType.AUTO)
    </#if>
    private ${field.columnType!} ${field.propertyName!};
</#list>

}
