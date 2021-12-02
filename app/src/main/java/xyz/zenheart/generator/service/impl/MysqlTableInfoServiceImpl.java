package xyz.zenheart.generator.service.impl;

import xyz.zenheart.generator.datasource.execute.SqlExecute;
import xyz.zenheart.generator.pojo.dto.TableDetailDto;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.service.ITableInfoService;

import java.util.List;
import java.util.Objects;

/**
 * <p>项目名称: generator </p>
 * <p>描述: mysql表信息业务实现 </p>
 * <p>创建时间: 2021/11/28 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class MysqlTableInfoServiceImpl implements ITableInfoService {

    @Override
    public List<TableInfoEntity> queryTableInfo() {
        String table = """
                SELECT table_name AS tableName,table_comment AS description FROM information_schema.tables  WHERE table_schema='@{schema}'
                """;
        String sql = table.replace("@{schema}", setting().getSchema());
        return Objects.requireNonNull(SqlExecute.executeQuery(sql, ITableInfoService::queryTableInfo));
    }

    @Override
    public List<TableDetailDto> queryTableDetails(String tableName) {
        String details = """
                select TABLE_NAME AS tableName,COLUMN_NAME AS columnName,COLUMN_TYPE AS columnType,
                IS_NULLABLE AS isNullAble,COLUMN_KEY AS isPrimary,COLUMN_COMMENT AS columnDescription
                from information_schema.COLUMNS where table_name = '@{tableName}' and table_schema = '@{schema}';
                """;
        String sql = details.replace("@{tableName}", tableName).replace("@{schema}", setting().getSchema());

        return Objects.requireNonNull(SqlExecute.executeQuery(sql, ITableInfoService::queryTableDetails));
    }
}