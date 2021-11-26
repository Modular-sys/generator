package xyz.zenheart.generator.service.impl;

import org.springframework.stereotype.Service;
import xyz.zenheart.generator.modules.ITableInfoMapper;
import xyz.zenheart.generator.modules.mysql.mapper.IMysqlTableInfoMapper;
import xyz.zenheart.generator.modules.pgsql.mapper.IPgsqlTableInfoMapper;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.service.ITableInfoService;
import xyz.zenheart.generator.utils.Constant;

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
@Service
public class TableInfoServiceImpl implements ITableInfoService {

    @Resource
    private IPgsqlTableInfoMapper pgsqlTableInfoMapper;

    @Resource
    private IMysqlTableInfoMapper mysqlTableInfoMapper;

    @Override
    public List<TableInfoEntity> queryTableInfo(String schema) {
        String type = (String) Constant.GLOBAL.get(Constant.SELECTED);
        ITableInfoMapper tableInfoMapper;
        if ("mysql".equals(type)) {
            tableInfoMapper = mysqlTableInfoMapper;
        } else {
            tableInfoMapper = pgsqlTableInfoMapper;
        }
        return tableInfoMapper.queryTableInfo(schema);
    }
}
