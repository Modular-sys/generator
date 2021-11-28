package xyz.zenheart.generator.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.zenheart.generator.pojo.dto.TableDto;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.pojo.widget.DownloadButton;
import xyz.zenheart.generator.pojo.widget.TableCheckbox;
import xyz.zenheart.generator.service.factory.ServiceFactory;
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
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Component
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


    private final ObservableList<TableDto> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStyle();
//        checkbox.setCellFactory(param -> {
//            TableCell<TableDto, TableCheckbox> cell = new TableCell<>() {
//                @Override
//                public void updateItem(TableCheckbox item, boolean empty) {
//                    super.updateItem(item,empty);
//                    if (!empty && Objects.nonNull(item)) {
//                        super.setGraphic(item);
//                    }
//                }
//            };
//            cell.setAlignment(Pos.CENTER);
//            return cell;
//        });

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
        checkbox.setMinWidth(35);
        checkbox.setMaxWidth(35);
        checkbox.setStyle("-fx-alignment:center");
        tableName.setMinWidth(140);
        tableName.setMaxWidth(180);
        describe.setMinWidth(200);
        describe.setMaxWidth(240);
        tableName.setStyle("-fx-padding: 0");
        tableName.setStyle("-fx-alignment:center-left");
        describe.setStyle("-fx-padding: 0");
        describe.setStyle("-fx-alignment:center-left");
        operation.setStyle("-fx-alignment:center");
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
        data.clear();
        List<TableInfoEntity> tableInfoEntities = ServiceFactory.tableInfoService().queryTableInfo();
        if (Objects.isNull(tableInfoEntities)) return;
        for (TableInfoEntity tableInfo : tableInfoEntities) {
            DownloadButton button = new DownloadButton("下载");
            String description = Objects.isNull(tableInfo.getDescription()) ? "" : tableInfo.getDescription();
            TableCheckbox checkbox = new TableCheckbox(Constant.FALSE);
            TableDto dto = new TableDto(checkbox, tableInfo.getTableName(), description, button);
            data.add(dto);
            button.setRowData(dto);
            checkbox.setRowData(dto);
            button.setOnAction(this::downloadEvent);
        }
    }
}
