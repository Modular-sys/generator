package xyz.zenheart.generator.modules.pgsql.service.impl;

import org.springframework.stereotype.Service;
import xyz.zenheart.generator.modules.pgsql.mapper.IPgsqlTableInfoMapper;
import xyz.zenheart.generator.modules.pgsql.service.IPgsqlTableInfoService;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 配置service实现 </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Service("pgsqlTableInfoService")
public class PgsqlTableInfoServiceImpl implements IPgsqlTableInfoService {

    @Resource
    private IPgsqlTableInfoMapper tableInfoMapper;

    @Override
    public List<TableInfoEntity> queryTableInfo(String schema) {
        return tableInfoMapper.queryTableInfo(schema);
    }
}
