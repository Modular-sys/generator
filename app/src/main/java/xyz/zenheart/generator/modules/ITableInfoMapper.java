package xyz.zenheart.generator.modules;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;

import java.util.List;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/9/22 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Mapper
public interface ITableInfoMapper {

    List<TableInfoEntity> queryTableInfo(@Param("schema") String schema);


}
