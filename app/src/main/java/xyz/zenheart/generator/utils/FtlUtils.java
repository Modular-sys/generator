package xyz.zenheart.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xyz.zenheart.generator.pojo.entity.UserEntity;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: TODO </p>
 * <p>创建时间: 2021/9/16 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Component
public class FtlUtils {

    private static Configuration configuration;

    public static void main(String[] args) {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        cfg.setClassForTemplateLoading(FtlUtils.class, PathUtils.FTL);

        cfg.setDefaultEncoding("UTF-8");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        try {
            Template temp = cfg.getTemplate("entity.java.ftl");
            System.out.println(temp);
            FileWriter fileWriter = new FileWriter("entity.java");
            UserEntity userEntity = new UserEntity();
            userEntity.setName("222222");
            userEntity.setPwd("123456");
            temp.process(userEntity, fileWriter);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
//        try {
//            Path path = Paths.get(URI.create("file:/D:/DraftFiles/维之星进度/1/1/1.txt"));
//        Path root = path.getRoot();
//        System.out.println(root.toString());
//            FileAttribute<Set<PosixFilePermission>> r = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxrwxrwx"));
//            Files.createDirectories(path);
//            Path directories = Files.createFile(path);
//            Files.writeString(path,"fffffffffffffff");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void writeFile(Object o, String templateName, String fileName) {
        Template temp = null;
        try {
            temp = configuration.getTemplate(templateName);
            System.out.println(temp);
            FileWriter fileWriter = new FileWriter(fileName);
            temp.process(o, fileWriter);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    @Qualifier("freemarkerConfiguration")
    public void afterPropertiesSet(Configuration configuration) {
        FtlUtils.configuration = configuration;
    }
}
