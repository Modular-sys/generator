package xyz.zenheart.generator.service.impl;

import xyz.zenheart.generator.datasource.execute.SqlExecute;
import xyz.zenheart.generator.pojo.dto.TableDetailDto;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.service.ITableInfoService;

import java.util.List;
import java.util.Objects;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class PgsqlTableInfoServiceImpl implements ITableInfoService {

    @Override
    public List<TableInfoEntity> queryTableInfo() {
        String table = """
                SELECT pgt_.tablename AS tablename, pgd_.description as description 
                        FROM pg_tables pgt_ 
                        JOIN pg_class pgc_ ON pgc_.relname = pgt_.tablename 
                        LEFT JOIN pg_description pgd_ ON pgd_.objoid = pgc_.oid AND pgd_.objsubid = '0' 
                        WHERE 1=1 AND pgt_.schemaname = '@{schema}'
                """;
        String sql = table.replace("@{schema}", setting().getSchema());
        return Objects.requireNonNull(SqlExecute.executeQuery(sql, ITableInfoService::queryTableInfo));
    }

    @Override
    public List<TableDetailDto> queryTableDetails(String tableName) {
//        String details = """
//                SELECT pc.relname AS tableName,pa.attname AS columnName,pt.typname AS columnType,
//                	(CASE WHEN pa.attlen > 0 THEN pa.attlen ELSE pa.atttypmod - 4 END ) AS columnLength, pa.attnotnull AS isNullAble,
//                	( CASE WHEN ( SELECT COUNT(*) FROM pg_constraint WHERE conrelid = pa.attrelid AND conkey[1]= attnum AND contype = 'p' ) > 0 THEN
//                        TRUE ELSE FALSE END ) AS isPrimary,pd.description AS columnDescription
//                FROM
//                	pg_class pc,pg_attribute pa,pg_type pt,pg_description pd
//                WHERE
//                	pc.oid = pa.attrelid AND pt.oid = pa.atttypid AND pd.objoid = pa.attrelid AND pd.objsubid = pa.attnum AND pc.relname = '@{tableName}'
//                ORDER BY
//                	pc.relname DESC,pa.attnum ASC
//                """;
        String details = """
                SELECT pc.relname AS tableName,pa.attname AS columnName,pt.typname AS columnType,
                	(CASE WHEN pa.attlen > 0 THEN pa.attlen ELSE pa.atttypmod - 4 END ) AS columnLength,pa.attnotnull AS isNullAble,
                	(CASE WHEN (SELECT COUNT(*) FROM pg_constraint WHERE conrelid = pa.attrelid AND conkey[1]= attnum AND contype = 'p' ) > 0 THEN TRUE
                		ELSE FALSE END ) AS isPrimary,col_description(pa.attrelid, pa.attnum) AS columnDescription
                FROM pg_class pc LEFT JOIN pg_attribute pa ON pc.oid = pa.attrelid INNER JOIN pg_type pt ON pt.oid = pa.atttypid
                WHERE pc.relname = '@{tableName}' AND pa.attnum>0
                ORDER BY pc.relname DESC, pa.attnum ASC;
                """;
        String sql = details.replace("@{tableName}", tableName);
        return Objects.requireNonNull(SqlExecute.executeQuery(sql, ITableInfoService::queryTableDetails));
    }
}
