package xyz.zenheart.generator.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import xyz.zenheart.generator.Application;

import java.io.IOException;
import java.net.URL;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 使用spring代理fxml对象实例 </p>
 * <p>创建时间: 2021/11/24 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class SpringFxmlLoader extends FXMLLoader {

    public static Parent Load(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(Application::getBean);
            loader.setLocation(url);
            return loader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}