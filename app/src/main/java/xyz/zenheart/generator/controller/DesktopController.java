package xyz.zenheart.generator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class DesktopController implements Initializable {

    @FXML
    private SettingController settingController;
    @FXML
    private TableListController tableListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
