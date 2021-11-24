package xyz.zenheart.generator.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import xyz.zenheart.generator.ApplicationMain;
import xyz.zenheart.generator.modules.pgsql.service.IPgsqlTableInfoService;
import xyz.zenheart.generator.pojo.dto.TableDto;
import xyz.zenheart.generator.pojo.entity.SettingEntity;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.pojo.widget.DownloadButton;
import xyz.zenheart.generator.pojo.widget.TableCheckbox;
import xyz.zenheart.generator.utils.Constant;
import xyz.zenheart.generator.utils.FieldUtils;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 小信息列表 </p>
 * <p>创建时间: 2021/9/24 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
public class TableListController implements Initializable {

    @FXML
    private TableView<TableDto> tableView;
    @FXML
    private TableColumn<TableDto, TableCheckbox> checkbox;
    @FXML
    private TableColumn<TableDto, String> tableName;
    @FXML
    private TableColumn<TableDto, String> describe;
    @FXML
    private TableColumn<TableDto, DownloadButton> operation;
    @FXML
    private Button searchTable;

    private final ObservableList<TableDto> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStyle();
        searchTable();
        checkbox.setCellFactory(param -> {
            TableCell<TableDto, TableCheckbox> cell = new TableCell<>() {
                @Override
                public void updateItem(TableCheckbox item, boolean empty) {
                    if (!empty) {
                        super.setGraphic(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        checkbox.setCellValueFactory(cellData -> cellData.getValue().getTableCheckbox());
        CheckBox box = new CheckBox();
        box.selectedProperty().addListener((observable, oldValue, newValue) -> data.forEach(tableDto -> tableDto.getTableCheckbox().setSelected(newValue)));
        checkbox.setGraphic(box);

        tableName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTableName()));

        describe.setCellFactory(TextFieldTableCell.forTableColumn());
        describe.setCellValueFactory(new PropertyValueFactory<>(FieldUtils.getName(TableDto::getDescribe)));

        operation.setCellValueFactory(new PropertyValueFactory<>(FieldUtils.getName(TableDto::getDownloadButton)));

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    private void initStyle() {
        checkbox.setMinWidth(30);
        tableName.setMinWidth(120);
        describe.setMinWidth(210);
        tableName.setStyle("-fx-alignment:CENTER");
        describe.setStyle("-fx-alignment:CENTER");
        operation.setStyle("-fx-alignment:CENTER");
    }

    private void downloadEvent(ActionEvent actionEvent) {
        DownloadButton source = (DownloadButton) actionEvent.getSource();
        System.out.println(source.getRowData());
    }


    @FXML
    private void searchTableEvent(ActionEvent event) {
        searchTable();
    }

    private void searchTable() {
        SettingEntity settingEntity = (SettingEntity) Constant.GLOBAL.get(Constant.SETTING_ENTITY);
        if (Objects.isNull(settingEntity)) return;
        IPgsqlTableInfoService pgsqlTableInfoService = ApplicationMain.getBean(IPgsqlTableInfoService.class);
        List<TableInfoEntity> devops = pgsqlTableInfoService.queryTableInfo(settingEntity.getSchema());
        for (TableInfoEntity tableInfo : devops) {
            DownloadButton button = new DownloadButton("下载");
            TableDto dto = new TableDto(new TableCheckbox(false), tableInfo.getTableName(), tableInfo.getDescription(), button);
            data.add(dto);
            button.setRowData(dto);
            button.setOnAction(this::downloadEvent);
        }
    }
}
