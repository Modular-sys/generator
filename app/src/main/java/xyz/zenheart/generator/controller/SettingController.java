package xyz.zenheart.generator.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.zenheart.generator.config.FXMLConfiguration;
import xyz.zenheart.generator.enums.DataBaseEnum;
import xyz.zenheart.generator.modules.locale.service.ISettingService;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.utils.Constant;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 配置页面 </p>
 * <p>创建时间: 2021/9/23 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Component
public class SettingController implements Initializable {

    @FXML
    public TextField superClass;
    @FXML
    private TextField directoryLocation;
    @FXML
    private Button directoryChooser;
    @FXML
    private TextField databaseUrl;
    @FXML
    private TextField databaseName;
    @FXML
    private ChoiceBox<DataBaseEnum> databaseType;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField schema;
    @FXML
    private TextField moduleName;
    @FXML
    private TextField packagePath;
    @FXML
    public TextField tablePrefix;
    @Resource
    private ISettingService settingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Tooltip tooltip = new Tooltip();
        tooltip.setText("请输入");

        databaseUrl.setFocusTraversable(false);
        databaseUrl.setTooltip(tooltip);

        databaseType.setItems(FXCollections.observableArrayList(DataBaseEnum.values()));
        String selected = (String) Constant.GLOBAL.get(Constant.SELECTED);
        databaseType.getSelectionModel().select(DataBaseEnum.getByName(selected));

        resetValue((SettingEntity) Constant.GLOBAL.get(selected));

        databaseType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            log.info("切换到数据库: {}", newValue.getName());
            Constant.GLOBAL.put(Constant.SELECTED, newValue.getName());
            SettingEntity setting = (SettingEntity) Constant.GLOBAL.get(newValue.getName());
            resetValue(setting);
            saveConfigHandle();
        });
        databaseType.setConverter(new DataBaseConverter());

        directoryChooser.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Folder");
            File directory = directoryChooser.showDialog(FXMLConfiguration.stage);
            if (directory != null) {
                System.out.println(directory.getAbsolutePath());
                directoryLocation.setText(directory.getAbsolutePath());
            }
        });
    }

    private void resetValue(SettingEntity setting) {
        directoryLocation.setText(setting.getDirectoryLocation());
        databaseUrl.setText(setting.getDatabaseUrl());
        databaseName.setText(setting.getDatabaseName());
        username.setText(setting.getUsername());
        password.setText(setting.getPassword());
        schema.setText(setting.getSchema());
        moduleName.setText(setting.getModuleName());
        packagePath.setText(setting.getPackagePath());
        tablePrefix.setText(setting.getTablePrefix());
        superClass.setText(setting.getSuperClass());
    }

    static class DataBaseConverter extends StringConverter<DataBaseEnum> {
        public DataBaseConverter() {
        }

        @Override
        public String toString(DataBaseEnum object) {
            return object.getName();
        }

        @Override
        public DataBaseEnum fromString(String name) {
            return DataBaseEnum.getByName(name);
        }
    }

    @FXML
    private void saveConfigEvent(ActionEvent event) {
        saveConfigHandle();
    }

    private void saveConfigHandle() {
        SettingEntity setting = snapshotData();
        settingService.updateSetting(setting);
        settingService.selectedModify(setting.getDatabaseType());
    }

    private SettingEntity snapshotData() {
        SettingEntity setting = new SettingEntity();
        setting.setDirectoryLocation(directoryLocation.getText());
        setting.setDatabaseUrl(databaseUrl.getText());
        setting.setDatabaseName(databaseName.getText());
        setting.setDatabaseType(databaseType.getValue().getName());
        setting.setUsername(username.getText());
        setting.setPassword(password.getText());
        setting.setSchema(schema.getText());
        setting.setModuleName(moduleName.getText());
        setting.setPackagePath(packagePath.getText());
        setting.setTablePrefix(tablePrefix.getText());
        setting.setSuperClass(superClass.getText());
        Constant.GLOBAL.put(Constant.SELECTED, setting.getDatabaseType());
        Constant.GLOBAL.put(setting.getDatabaseType(), setting);
        return setting;
    }
}
