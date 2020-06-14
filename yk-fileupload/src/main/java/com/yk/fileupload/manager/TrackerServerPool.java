package com.yk.fileupload.manager;

import com.yk.common.exception.file.FastDfsException;
import com.yk.common.util.SpringUtils;
import com.yk.fileupload.config.properties.FastDfsProperties;
import com.yk.fileupload.manager.factory.TrackerServerFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName TrackerServerPool
 * @Description TrackerServer 对象池
 * @Author YuKai Fan
 * @Date 2020/6/13 21:39
 * @Version 1.0
 **/
public class TrackerServerPool {
    private static final Logger logger = LoggerFactory.getLogger(TrackerServerPool.class);

    /**
     * fastDfs配置类
     */
    private static final FastDfsProperties FAST_DFS_PROPERTIES = SpringUtils.getBean(FastDfsProperties.class);

    /**
     * TrackerServer 对象池.
     * GenericObjectPool 没有无参构造
     */
    private static GenericObjectPool<TrackerServer> trackerServerPool;

    /**
     * 单例模式
     */
    private TrackerServerPool() {
    }

    private static GenericObjectPool<TrackerServer> getObjectPool() {
        if (trackerServerPool == null) {
            try {
                // 加载配置文件
                ClientGlobal.setG_connect_timeout(FAST_DFS_PROPERTIES.getConnectTimeout());
                ClientGlobal.setG_charset(FAST_DFS_PROPERTIES.getCharset());
                ClientGlobal.setG_network_timeout(FAST_DFS_PROPERTIES.getNetworkTimeout());
                ClientGlobal.setG_anti_steal_token(FAST_DFS_PROPERTIES.getHttpAntiStealToken());
                ClientGlobal.setG_tracker_http_port(FAST_DFS_PROPERTIES.getHttpTrackerHttpPort());
                ClientGlobal.setG_secret_key(FAST_DFS_PROPERTIES.getHttpSecretKey());
                ClientGlobal.initByTrackers(FAST_DFS_PROPERTIES.getTrackerServers());
            } catch (IOException | MyException e) {
                e.printStackTrace();
            }

            logger.info("ClientGlobal configInfo ClientGlobal初始化信息: {}", ClientGlobal.configInfo());

            //Pool配置
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMinIdle(2);

            if (FAST_DFS_PROPERTIES.getMaxStorageConnection() > 0) {
                poolConfig.setMaxTotal(FAST_DFS_PROPERTIES.getMaxStorageConnection());
            }

            trackerServerPool = new GenericObjectPool<>(new TrackerServerFactory(), poolConfig);
        }

        return trackerServerPool;
    }

    /**
     *
     * 获取TrackerServer
     * @author YuKai Fan
     * @return org.csource.fastdfs.TrackerServer
     * @date 2020/6/13 22:02
     */
    public static TrackerServer getTrackerServer() {
        TrackerServer trackerServer = null;
        try {
            trackerServer = getObjectPool().borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof FastDfsException){
                throw (FastDfsException) e;
            }
        }
        return trackerServer;
    }

    /**
     * 回收 TrackerServer
     * @author YuKai Fan
     * @param trackerServer
     * @return void
     * @date 2020/6/13 22:03
     */
    public static void returnObject(TrackerServer trackerServer){
        getObjectPool().returnObject(trackerServer);
    }
}
