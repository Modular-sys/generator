package xyz.zenheart.generator.datasource.execute;

import lombok.extern.slf4j.Slf4j;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.utils.Constant;

import java.sql.*;
import java.util.function.Function;

/**
 * <p>项目名称: generator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/11/26 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
public class SqlExecute {

    private static final String PGSQL_DRIVE = "org.postgresql.Driver";
    private static final String MYSQL_DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String PGSQL_URL = "jdbc:postgresql://@{url}/@{database}?currentSchema=@{schema}&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String MYSQL_URL = "jdbc:mysql:///@{url}/@{database}?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

    public static void main(String[] args) {
//        PreparedStatement
//        DriverManager.getConnection()
    }

    public static Connection createConnection() {
        try {
            String type = (String) Constant.GLOBAL.get(Constant.SELECTED);
            SettingEntity setting = (SettingEntity) Constant.GLOBAL.get(type);
            return switch (type) {
                case "mysql" -> {
                    Class.forName(MYSQL_DRIVE);
                    String url = MYSQL_URL.replace("@{url}", setting.getDatabaseUrl())
                            .replace("@{database}", setting.getDatabaseName());
                    yield DriverManager.getConnection(url, setting.getUsername(), setting.getPassword());
                }
                case "pgsql" -> {
                    Class.forName(PGSQL_DRIVE);
                    String url = PGSQL_URL.replace("@{url}", setting.getDatabaseUrl())
                            .replace("@{database}", setting.getDatabaseName())
                            .replace("@{schema}", setting.getSchema());
                    yield DriverManager.getConnection(url, setting.getUsername(), setting.getPassword());
                }
                default -> null;
            };
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  <R> R executeQuery(String sql, Function<ResultSet, R> call) {
        Connection connection = createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            log.info(resultSet.toString());
            return call.apply(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}