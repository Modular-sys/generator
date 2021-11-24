package xyz.zenheart.generator.utils;

import java.net.URL;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 路径工具 </p>
 * <p>创建时间: 2021/9/1 </p>
 *
 * @author CKM
 * @version v1.0
 */
public class PathUtils {
    public static final String FXML = "/fxml";
    public static final String FTL = "/ftl";

    public static URL fxml(String url) {
        return PathUtils.class.getResource(FXML + url);
    }

    public static URL ftl(String url) {
        return PathUtils.class.getResource(FTL + url);
    }

}
