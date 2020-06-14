package com.yk.fileupload.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FastDFSProperties
 * @Description fastDFS配置类
 * @Author YuKai Fan
 * @Date 2020/6/13 21:31
 * @Version 1.0
 **/
@Configuration
@Getter
public class FastDfsProperties {

    /**
     * 最大连接数 并发量较大的话可加大连接数
     */
    @Value("${fastdfs.maxStorageConnection}")
    private Integer maxStorageConnection;

    /**
     * 连接超时时间(默认10秒)
     */
    @Value("${fastdfs.connectTimeout}")
    private Integer connectTimeout;

    /**
     * 连接超时时间(默认30秒)
     */
    @Value("${fastdfs.networkTimeout}")
    private Integer networkTimeout;

    /**
     * 编码类型
     */
    @Value("${fastdfs.charset}")
    private String charset;

    /**
     * 是否开始防盗链
     */
    @Value("${fastdfs.httpAntiStealToken}")
    private Boolean httpAntiStealToken;

    /**
     * 防盗秘钥(服务端与客户端必须一致)
     */
    @Value("${fastdfs.httpSecretKey}")
    private String httpSecretKey;

    /**
     * http tracker访问端口号
     */
    @Value("${fastdfs.httpTrackerHttpPort}")
    private Integer httpTrackerHttpPort;

    /**
     * tracker访问地址
     */
    @Value("${fastdfs.trackerServers}")
    private String trackerServers;

}
