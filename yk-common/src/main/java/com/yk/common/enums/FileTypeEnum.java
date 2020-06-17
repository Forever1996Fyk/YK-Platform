package com.yk.common.enums;

/**
 * @program: YK-Platform
 * @description: 文件类型枚举
 * @author: YuKai Fan
 * @create: 2020-06-10 21:20
 **/
public enum FileTypeEnum {
    /**
     * JEPG.
     */
    JPEG("jpeg"),

    /**
     * PNG.
     */
    PNG("png"),

    /**
     * GIF.
     */
    GIF("gif"),

    /**
     * JPG.
     */
    JPG("jpg"),

    /**
     * TIFF.
     */
    TIFF("tiff"),

    /**
     * Windows Bitmap.
     */
    BMP("bmp"),

    /**
     * CAD.
     */
    DWG("dwg"),

    /**
     * Adobe Photoshop.
     */
    PSD("psd"),

    /**
     * Rich Text Format.
     */
    RTF("rtf"),

    /**
     * XML.
     */
    XML("xml"),

    /**
     * HTML.
     */
    HTML("html"),
    /**
     * CSS.
     */
    CSS("css"),
    /**
     * JS.
     */
    JS("js"),
    /**
     * Email [thorough only].
     */
    EML("eml"),

    /**
     * Outlook Express.
     */
    DBX("dbx"),

    /**
     * Outlook (pst).
     */
    PST("pst"),

    /**
     * MS Word/Excel.
     */
    XLS_DOC("xls"), XLSX_DOCX("xlsx"),
    DOC("doc"),DOCX("docx"), TXT("txt"),PPT("ppt"),
    /**
     * Visio
     */
    VSD("vsd"),
    /**
     * MS Access.
     */
    MDB("mdb"),
    /**
     * WPS文字wps、表格et、演示dps都是一样的
     */
    WPS("d0cf11e0a1b11ae10000"),
    /**
     * torrent
     */
    TORRENT("torrent"),
    /**
     * WordPerfect.
     */
    WPD("wpd"),

    /**
     * Postscript.
     */
    EPS("eps"),

    /**
     * Adobe Acrobat.
     */
    PDF("pdf"),

    /**
     * Quicken.
     */
    QDF("qdf"),

    /**
     * Windows Password.
     */
    PWL("pwl"),

    /**
     * ZIP Archive.
     */
    ZIP("zip"),

    /**
     * RAR Archive.
     */
    RAR("rar"),
    /**
     * JSP Archive.
     */
    JSP("jsp"),
    /**
     * JAVA Archive.
     */
    JAVA("java"),
    /**
     * CLASS Archive.
     */
    CLASS("class"),
    /**
     * JAR Archive.
     */
    JAR("jar"),
    /**
     * MF Archive.
     */
    MF("mf"),
    /**
     *EXE Archive.
     */
    EXE("exe"),
    /**
     *CHM Archive.
     */
    CHM("chm"),
    /*
     * INI("235468697320636F6E66"), SQL("494E5345525420494E54"), BAT(
     * "406563686F206f66660D"), GZ("1F8B0800000000000000"), PROPERTIES(
     * "6C6F67346A2E726F6F74"), MXP(
     * "04000000010000001300"),
     */
    /**
     * Wave.
     */
    WAV("wav"),

    /**
     * AVI.
     */
    AVI("avi"),

    /**
     * Real Audio.
     */
    RAM("ram"),

    /**
     * Real Media.
     */
    RM("rm"),

    /**
     * MPEG (mpg).
     */
    MPG("mpg"),

    /**
     * Quicktime.
     */
    MOV("mov"),

    /**
     * Windows Media.
     */
    ASF("asf"),

    /**
     * MIDI.
     */
    MID("mid"),
    /**
     * MP4.
     */
    MP4("mp4"),
    /**
     * MP3.
     */
    MP3("mp3"),
    /**
     * FLV.
     */
    FLV("flv");

    private String value = "";

    /**
     * Constructor.
     *
     * @param value
     */
    FileTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}