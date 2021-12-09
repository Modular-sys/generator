package xyz.zenheart.generator.controller;

import com.sun.javafx.scene.control.VirtualScrollBar;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.zenheart.generator.pojo.dto.TableDto;
import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
import xyz.zenheart.generator.pojo.widget.DownloadButton;
import xyz.zenheart.generator.pojo.widget.TableCheckbox;
import xyz.zenheart.generator.service.ITablePageService;
import xyz.zenheart.generator.service.factory.ServiceFactory;
import xyz.zenheart.generator.utils.Constant;
import xyz.zenheart.generator.utils.FieldUtils;

import javax.annotation.Resource;
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
    public AnchorPane tableContainer;
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
    @Resource
    private ITablePageService tablePageService;

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
        tableView.setPrefHeight(tableContainer.getHeight());
        tableView.prefHeightProperty().bind(tableContainer.prefHeightProperty());

        checkbox.setStyle("-fx-alignment:center");
        checkbox.setPrefWidth(tableView.widthProperty().multiply(0.1).getValue());
        checkbox.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));

        tableName.setStyle("-fx-padding: 0");
        tableName.setStyle("-fx-alignment:center-left");
        tableName.setPrefWidth(tableView.widthProperty().multiply(0.3).getValue());
        tableName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));

        describe.setStyle("-fx-padding: 0");
        describe.setStyle("-fx-alignment:center-left");
        describe.setPrefWidth(tableView.widthProperty().multiply(0.45).getValue());
        describe.prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));

        operation.setStyle("-fx-alignment:center");
        operation.setPrefWidth(tableView.widthProperty().multiply(0.15).getValue());
        operation.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
    }

    private void downloadEvent(ActionEvent actionEvent) {
        DownloadButton source = (DownloadButton) actionEvent.getSource();
        System.out.println(source.getRowData());
        Platform.runLater(() -> {
            log.info("下载{}", source);
            tablePageService.processButtonDownload((TableDto) source.getRowData());
        });
    }

    @FXML
    private void searchTableEvent(ActionEvent event) {
        searchTable();
    }

    private void searchTable() {
        Platform.runLater(() -> {
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
            ScrollBar verticalBar = (ScrollBar) tableView.lookup(".scroll-bar:vertical");
            log.info("");
        });
    }
}
