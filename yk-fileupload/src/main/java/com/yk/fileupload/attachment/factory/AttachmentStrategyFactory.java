package com.yk.fileupload.attachment.factory;

import com.google.common.collect.Maps;
import com.yk.fileupload.attachment.service.AttachmentService;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 附件策略工厂类
 * @author: YuKai Fan
 * @create: 2020-06-10 22:34
 **/
public class AttachmentStrategyFactory {
    private static Map<String, AttachmentService> services = Maps.newConcurrentMap();

    /**
     * 根据类型获取对应的实现类
     * @param type
     * @return
     */
    public static AttachmentService getAttachmentServiceByType(String type) {
        return services.get(type);
    }

    /**
     * 向工厂中注入对应的实现类
     * @param type
     * @param attachmentService
     */
    public static void register(String type, AttachmentService attachmentService) {
        Assert.notNull(type, "附件接口不能为空");
        services.put(type, attachmentService);
    }
}