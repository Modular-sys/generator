package xyz.zenheart.generator.utils;

import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final Pattern pattern = Pattern.compile("[/\\\\]+");

    public static URL fxml(String url) {
        return PathUtils.class.getResource(FXML + url);
    }

    public static URL ftl(String url) {
        return PathUtils.class.getResource(FTL + url);
    }

    public static String format(String path) {
        /*获取 matcher 对象*/
        Matcher matcher = pattern.matcher(path);
        return matcher.replaceAll(Matcher.quoteReplacement(File.separator));
    }
}
