package com.yk.fileupload.config;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @ClassName NonStaticResourceHttpRequestHandler
 * @Description 返回视频流请求处理
 * @Author YuKai Fan
 * @Date 2020/6/12 22:37
 * @Version 1.0
 **/
@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {
    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        final Path path = (Path) request.getAttribute(ATTR_FILE);
        return new FileSystemResource(path);
    }
}
