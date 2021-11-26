package xyz.zenheart.generator.config;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import xyz.zenheart.generator.utils.PathUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 代码生成器启动类 </p>
 * <p>创建时间: 2021/7/29 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
public class FXMLConfiguration extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ContextConfiguration.initialization();
        Parent root = Load(PathUtils.fxml("/Desktop.fxml"));
        stage.setTitle("代码生成器");
        stage.getIcons().add(new Image("/assets/icon/icon.jpg"));
        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add("/assets/css/global.css");
        stage.setScene(scene);
//        stage.setResizable(false);
        FXMLConfiguration.stage = stage;
        // systemTraySetting();
        stage.show();
    }

    private void systemTraySetting() {
        Platform.setImplicitExit(false);
        java.awt.Image image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(FXMLConfiguration.class.getResourceAsStream("/assets/icon/icon.jpg")));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        SystemTray tray = SystemTray.getSystemTray();
        final ActionListener closeListener = e -> System.exit(0);

        ActionListener showListener = e -> Platform.runLater(stage::show);
        // create a popup menu
        PopupMenu popup = new PopupMenu();

        MenuItem showItem = new MenuItem("Show");
        showItem.addActionListener(showListener);
        popup.add(showItem);

        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener(closeListener);
        popup.add(closeItem);
        TrayIcon trayIcon = new TrayIcon(image, "Title", popup);
        trayIcon.addActionListener(showListener);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            log.error("系统托盘异常", e.getCause());
        }
    }


    public Parent Load(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(ContextConfiguration::getBean);
            loader.setLocation(url);
            return loader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public static void execute(String[] args) {
        launch(args);
    }
}
