package xyz.zenheart.generator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class DesktopController implements Initializable {

    public AnchorPane desktopContainer;
    @FXML
    private SettingController settingController;
    @FXML
    private TableListController tableListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableListController.tableContainer.setPrefWidth(desktopContainer.getWidth());
        tableListController.tableContainer.prefWidthProperty().bind(desktopContainer.widthProperty());
    }
}
