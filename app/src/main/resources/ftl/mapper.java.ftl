package ${packageMapper};

import ${packageEntity}.${entityClass};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * ${tableComment!} Mapper 接口
 * </p>
 *
 * @author ${author!}
 * @since ${date!}
 */
@Mapper
public interface ${tableMapperName} extends ${superMapperClass}<${entityClass}> {

}
