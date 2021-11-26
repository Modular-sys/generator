package xyz.zenheart.generator.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>项目名称: generator </p>
 * <p>描述: 应用监听服务接口 </p>
 * <p>创建时间: 2021/11/25 </p>
 * <p>公司信息: 维之星研发部</p>
 *
 * @author CKM
 * @version v1.0
 */
public interface IApplicationListenService<T extends ApplicationEvent> extends ApplicationListener<T> {
}
