package xyz.zenheart.generator.service.factory;

import xyz.zenheart.generator.service.ITableInfoService;
import xyz.zenheart.generator.service.impl.MysqlTableInfoServiceImpl;
import xyz.zenheart.generator.service.impl.PgsqlTableInfoServiceImpl;
import xyz.zenheart.generator.utils.Constant;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class ServiceFactory {

    public static ITableInfoService tableInfoService() {
        return switch ((String) Constant.GLOBAL.get(Constant.SELECTED)) {
            case "mysql" -> new MysqlTableInfoServiceImpl();
            case "pgsql" -> new PgsqlTableInfoServiceImpl();
            default -> null;
        };
    }
}
