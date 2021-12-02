package ${entityPackage};

<#list importPackages as pkg>
    import ${pkg};
</#list>

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>${comment!}</p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
<#if convert>
    @TableName("${name}")
</#if>
<#if superEntityClass??>
    public class ${entityClass} extends ${superEntityClass} {
<#else>
    public class ${entityClass} implements Serializable {
</#if>

<#if superEntityClass??>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fields as field>
    <#if field.comment!?length gt 0>
        /**
        * ${field.comment}
        */
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
            @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
        <#elseif idType??>
            @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
        <#elseif field.convert>
            @TableId("${field.annotationColumnName}")
        </#if>
    <#-- 普通字段 -->
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>

}
