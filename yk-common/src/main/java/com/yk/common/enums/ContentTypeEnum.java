package com.yk.common.enums;

/**
 * @ClassName ContentTypeEnum
 * @Description ContentType 枚举
 * @Author YuKai Fan
 * @Date 2020/6/15 19:47
 * @Version 1.0
 **/
public enum ContentTypeEnum {
    // image
    image_png("png", "image/png"),
    image_gif("gif", "image/gif"),
    image_bmp("bmp", "image/bmp"),
    image_ico("ico", "image/ico"),
    image_jpeg("jpeg", "image/jpeg"),
    image_jpg("jpg", "image/jpg"),

    // 压缩文件
    file_zip("zip", "application/zip"),
    file_rar("rar", "application/x-rar"),

    // 文档
    document_pdf("pdf", "application/pdf"),
    document_ppt("ppt", "application/vnd.ms-powerpoint"),
    document_xls("xls", "application/vnd.ms-excel"),
    document_xlsx("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    document_pptx("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    document_doc("doc", "application/msword"),
    document_docx("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    document_txt("txt", "text/plain"),

    // 音频
    video_mp4("mp4", "video/mp4"),
    video_flv("flv", "video/x-flv"),
    ;

    private String suffix;
    private String contentTye;

    ContentTypeEnum(String suffix, String contentTye) {
        this.suffix = suffix;
        this.contentTye = contentTye;
    }

    public static String getContentTypeBySuffix(String suffix) {
        for(ContentTypeEnum values : ContentTypeEnum.values()) {
            if (values.getSuffix().equals(suffix)) {
                return values.getContentTye();
            }
        }

        return null;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getContentTye() {
        return contentTye;
    }
}
