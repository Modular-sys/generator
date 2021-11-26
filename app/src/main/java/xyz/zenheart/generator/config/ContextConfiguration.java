package xyz.zenheart.generator.config;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>项目名称: generator </p>
 * <p>描述: spring配置类 </p>
 * <p>创建时间: 2021/9/16 </p>
 *
 * @author CKM
 * @version v1.0
 */
@EnableAspectJAutoProxy
@ComponentScan("xyz.zenheart.generator")
public class ContextConfiguration {
    public static final AnnotationConfigApplicationContext CTX;
    private static final ConfigurableListableBeanFactory BEAN_FACTORY;

    static {
        CTX = new AnnotationConfigApplicationContext();
        CTX.register(ContextConfiguration.class);
        CTX.refresh();
        BEAN_FACTORY = CTX.getBeanFactory();
    }

    public static <T> T getBean(Class<T> aClass) {
        return CTX.getBean(aClass);
    }

    public static <T> T getBean(String s, Class<T> aClass) {
        return CTX.getBean(s, aClass);
    }

    public static void setBean(String beanName, Object obj) {
        BEAN_FACTORY.registerSingleton(beanName, obj);
        BEAN_FACTORY.autowireBean(obj);
    }

    public static void initialization() {
    }
}
