package xyz.zenheart.generator.modules.mysql.service.impl;

import org.springframework.stereotype.Service;
import xyz.zenheart.generator.modules.mysql.service.IMysqlTableInfoService;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;

import java.util.List;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: mysql表信息实现 </p>
 * <p>创建时间: 2021/9/22 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Service
public class MysqlTableInfoServiceImpl implements IMysqlTableInfoService {

    @Override
    public List<TableInfoEntity> queryTableInfo(String schema) {
        return null;
    }
}
