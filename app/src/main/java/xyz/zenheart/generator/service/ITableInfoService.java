package xyz.zenheart.generator.service;

import javafx.scene.control.Alert;
import xyz.zenheart.generator.pojo.dto.TableDetailDto;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.utils.Constant;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 配置类service </p>
 * <p>创建时间: 2021/9/9 </p>
 *
 * @author CKM
 * @version v1.0
 */
public interface ITableInfoService {
    default List<TableInfoEntity> queryTableInfo(ResultSet resultSet) {
        List<TableInfoEntity> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TableInfoEntity tableInfo = new TableInfoEntity();
                tableInfo.setTableName((String) resultSet.getObject("tablename"));
                tableInfo.setDescription((String) resultSet.getObject("description"));
                list.add(tableInfo);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "查询异常");
            alert.showAndWait();
        }
        return list;
    }

    default List<TableInfoEntity> queryTableInfo() {
        return null;
    }

    default List<TableDetailDto> queryTableDetails(String tableName) {
        return null;
    }

    default List<TableDetailDto> queryTableDetails(ResultSet resultSet) {
        List<TableDetailDto> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TableDetailDto tableDetail = new TableDetailDto();
                tableDetail.setTableName((String) resultSet.getObject("tablename"));
                tableDetail.setColumnName((String) resultSet.getObject("columnName"));
                tableDetail.setColumnType((String) resultSet.getObject("columnType"));
                tableDetail.setColumnDescription((String) resultSet.getObject("columnDescription"));
                tableDetail.setNullAble((boolean) resultSet.getObject("isNullAble"));
                tableDetail.setPrimary((boolean) resultSet.getObject("isPrimary"));
                list.add(tableDetail);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "查询异常");
            alert.showAndWait();
        }
        return list;
    }

    default SettingEntity setting() {
        return (SettingEntity) Constant.GLOBAL.get((String) Constant.GLOBAL.get(Constant.SELECTED));
    }
}
