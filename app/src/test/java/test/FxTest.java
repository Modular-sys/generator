//package test;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import xyz.zenheart.generator.config.SpringConfiguration;
//import xyz.zenheart.generator.modules.pgsql.mapper.IPgsqlTableInfoMapper;
//import xyz.zenheart.generator.modules.pgsql.service.IPgsqlTableInfoService;
//import xyz.zenheart.generator.pojo.entity.TableInfoEntity;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * <p>项目名称: generator </p>
// * <p>描述: 测试类 </p>
// * <p>创建时间: 2021/11/22 </p>
//// *
// * @author CKM
// * @version v1.0
// */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SpringConfiguration.class)
//public class FxTest {
//
//    @Resource
//    private IPgsqlTableInfoMapper mapper;
//
//    @Resource
//    private IPgsqlTableInfoService pgsqlTableInfoService;
//
//    @Test
//    public void ta() {
//        List<TableInfoEntity> devops = mapper.queryTableInfo("devops");
//        System.out.println(devops);
//    }
//}
