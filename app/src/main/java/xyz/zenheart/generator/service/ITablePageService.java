package xyz.zenheart.generator.service;

import xyz.zenheart.generator.pojo.dto.TableDto;

/**
 * <p>项目名称: generator </p>
 * <p>描述: table页面处理 </p>
 * <p>创建时间: 2021/12/3 </p>
 *
 * @author CKM
 * @version v1.0
 */
public interface ITablePageService {
    void processButtonDownload(TableDto tableDto);
}
