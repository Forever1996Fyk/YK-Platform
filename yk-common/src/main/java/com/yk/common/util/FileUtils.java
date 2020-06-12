package com.yk.common.util;

import ch.qos.logback.core.util.TimeUtil;
import com.github.pagehelper.util.StringUtil;
import com.yk.common.enums.FileTypeEnum;
import com.yk.common.exception.ParameterException;
import com.yk.common.exception.file.FileSizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: YK-Platform
 * @description: 文件工具类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:21
 **/
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取文件SHA1值
     *
     * @param multipartFile
     * @return
     */
    public static String getFileSHA1(MultipartFile multipartFile) {
        if (multipartFile.getSize() == 0) {
            logger.error("文件转化[{SH1}]值错误! 文件大小为空");
            throw new ParameterException("文件转化SH1值错误! 文件大小为空");
        }
        byte[] buffer = new byte[4096];
        try (InputStream fis = multipartFile.getInputStream()) {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            int len;
            while ((len = fis.read(buffer)) != -1) {
                sha1.update(buffer, 0, len);
            }
            BigInteger SHA1Bi = new BigInteger(1, sha1.digest());
            return SHA1Bi.toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据输入流 获取文件SHA1值
     *
     * @param fis
     * @return
     */
    public static String getFileSHA1(InputStream fis) {
        byte[] buffer = new byte[4096];
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            int len;
            while ((len = fis.read(buffer)) != -1) {
                sha1.update(buffer, 0, len);
            }
            BigInteger SHA1Bi = new BigInteger(1, sha1.digest());
            return SHA1Bi.toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件MD5值
     *
     * @param multipartFile
     * @return
     */
    public static String getFileMD5(MultipartFile multipartFile) {
        if (multipartFile.getSize() == 0) {
            logger.error("文件转化[{MD5}]值错误! 文件大小为空");
            throw new FileSizeException(0L);
        }
        byte[] buffer = new byte[4096];
        try (InputStream fis = multipartFile.getInputStream()) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int len;
            while ((len = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            BigInteger MD5Bi = new BigInteger(1, md5.digest());
            return MD5Bi.toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据输入流 获取文件MD5值
     *
     * @param fis
     * @return
     */
    public static String getFileMD5(InputStream fis) {
        byte[] buffer = new byte[4096];
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int len;
            while ((len = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            BigInteger MD5Bi = new BigInteger(1, md5.digest());
            return MD5Bi.toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            int lastIndexOf = fileName.lastIndexOf(".");
            return fileName.substring(lastIndexOf + 1);
        }
        return null;
    }

    /**
     * 根据文件原名生成id名称
     *
     * @param originalFilename
     * @return
     */
    public static String genFileName(String originalFilename) {
        String fileSuffix = getFileSuffix(originalFilename);
        return genFileName(AppUtils.randomId(), fileSuffix);
    }

    /**
     * 根据文件原名生成id名称
     *
     * @param attachmentId
     * @param suffix
     * @return
     */
    public static String genFileName(String attachmentId, String suffix) {
        return attachmentId + "." + suffix;
    }

    /**
     * 文件转化
     * @param filePath
     */
    public static File transferTo(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            logger.error("文件创建异常![{}], 异常信息为 [{}]", e, e.getMessage());
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 文件转化
     * @author YuKai Fan
     * @param multipartFile 文件对象
     * @param filePath 文件路径
     * @return void
     * @date 2020/6/11 22:15
     */
    public static void transferTo(MultipartFile multipartFile, String filePath) {
        byte[] buffer = new byte[4096];
        try (OutputStream fos = Files.newOutputStream(transferTo(filePath).toPath()); InputStream fis = multipartFile.getInputStream()) {
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件转化
     * @author YuKai Fan
     * @param fis 文件输入流
     * @param filePath 文件路径
     * @return void
     * @date 2020/6/11 22:14
     */
    public static void transferTo(InputStream fis, String filePath) {
        byte[] buffer = new byte[4096];
        try (OutputStream fos = Files.newOutputStream(transferTo(filePath).toPath())) {
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件类型获取相应的标识
     * @param value 文件类型
     * @return 1: 图片, 2: 视频, 3: 文档, 4: 种子, 5: 音乐, 6: 其他
     */
    public static int getTypeFlag(FileTypeEnum value) {
        int type = 6;//其他
        if (value == null) {
            throw new ParameterException("文件类型错误！");
        }
        // 图片
        FileTypeEnum[] pics = { FileTypeEnum.JPEG, FileTypeEnum.PNG, FileTypeEnum.GIF, FileTypeEnum.TIFF, FileTypeEnum.BMP, FileTypeEnum.DWG, FileTypeEnum.PSD, FileTypeEnum.JPG };

        //文档
        FileTypeEnum[] docs = { FileTypeEnum.RTF, FileTypeEnum.XML, FileTypeEnum.HTML, FileTypeEnum.CSS, FileTypeEnum.JS, FileTypeEnum.EML, FileTypeEnum.DBX, FileTypeEnum.PST, FileTypeEnum.XLS_DOC, FileTypeEnum.XLSX_DOCX, FileTypeEnum.VSD,
                FileTypeEnum.MDB, FileTypeEnum.WPS, FileTypeEnum.WPD, FileTypeEnum.EPS, FileTypeEnum.PDF, FileTypeEnum.QDF, FileTypeEnum.PWL, FileTypeEnum.ZIP, FileTypeEnum.RAR, FileTypeEnum.JSP, FileTypeEnum.JAVA, FileTypeEnum.CLASS,
                FileTypeEnum.JAR, FileTypeEnum.MF, FileTypeEnum.EXE, FileTypeEnum.CHM };

        //视频
        FileTypeEnum[] videos = { FileTypeEnum.AVI, FileTypeEnum.RAM, FileTypeEnum.RM, FileTypeEnum.MPG, FileTypeEnum.MOV, FileTypeEnum.ASF, FileTypeEnum.MP4, FileTypeEnum.FLV, FileTypeEnum.MID };

        //种子
        FileTypeEnum[] tottents = { FileTypeEnum.TORRENT };

        //音乐
        FileTypeEnum[] audios = { FileTypeEnum.WAV, FileTypeEnum.MP3 };

        FileTypeEnum[] others = {};

        //图片
        for (FileTypeEnum fileTypeEnum : pics) {
            if (fileTypeEnum.equals(value)) {
                type = 1;
            }
        }
        //视频
        for (FileTypeEnum fileTypeEnum : videos) {
            if (fileTypeEnum.equals(value)) {
                type = 2;
            }
        }
        //文档
        for (FileTypeEnum fileTypeEnum : docs) {
            if (fileTypeEnum.equals(value)) {
                type = 3;
            }
        }
        //种子
        for (FileTypeEnum fileTypeEnum : tottents) {
            if (fileTypeEnum.equals(value)) {
                type = 4;
            }
        }
        //音乐
        for (FileTypeEnum fileTypeEnum : audios) {
            if (fileTypeEnum.equals(value)) {
                type = 5;
            }
        }

        return type;
    }

    /**
     * 根据文件类型返回相应的文件路径
     * @param type
     * @return
     */
    public static String getModulePath(int type) {
        String module = "";
        switch (type) {
            case 1:
                module = "images";
                break;
            case 2:
                module = "videos";
                break;
            case 3:
                module = "docs";
                break;
            case 4:
                module = "torrents";
                break;
            case 5:
                module = "audios";
                break;
            case 6:
                module = "others";
                break;
        }

        return module;
    }

    /**
     * 获取文件类型
     * @param postfix
     * @return
     */
    public static FileTypeEnum getType(String postfix) {
        FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
        for (FileTypeEnum fileTypeEnum : fileTypeEnums) {
            if (postfix.equals(fileTypeEnum.getValue())) {
                return fileTypeEnum;
            }
        }
        return null;
    }

    /**
     * request转为file
     * @param request
     * @return
     */
    public static MultipartFile getRequestFile(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
            if (CollectionUtils.isEmpty(fileMap) || CollectionUtils.isEmpty(fileMap.keySet())) {
                return null;
            }
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                if (StringUtils.isBlank(entry.getKey())) {
                    continue;
                }

                if (!entry.getValue().isEmpty()) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * request转为fileMap
     * @param request
     * @return
     */
    public static Map<String, MultipartFile> getRequestFileMap(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
            if (CollectionUtils.isEmpty(fileMap) || CollectionUtils.isEmpty(fileMap.keySet())) {
                return null;
            }
            return fileMap;
        }
        return null;
    }

    /**
     * request转为file List
     * @param request
     * @return
     */
    public static List<MultipartFile> getRequestListFile(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            return multipartHttpServletRequest.getFiles("file_data");
        }
        return null;
    }
}