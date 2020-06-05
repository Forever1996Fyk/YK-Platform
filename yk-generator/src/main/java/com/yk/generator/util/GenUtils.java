package com.yk.generator.util;

import com.yk.common.constant.ComConstants;
import com.yk.common.constant.GenConstants;
import com.yk.common.util.StringUtils;
import com.yk.generator.config.GenConfig;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.pojo.GenTableColumn;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName GenUtils
 * @Description 代码生成工具类
 * @Author YuKai Fan
 * @Date 2020/6/4 20:45
 **/
public class GenUtils {
    private static final Logger logger = LoggerFactory.getLogger(GenUtils.class);

    /**
     * 初始化表信息
     *
     * @param genTable
     * @return void
     * @author YuKai Fan
     * @date 2020/6/4 20:53
     */
    public static void initTable(GenTable genTable) {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(GenConfig.getPackageName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(GenConfig.getAuthor());
    }


    /**
     * 初始化列属性字段
     *
     * @param column
     * @param table
     * @return void
     * @author YuKai Fan
     * @date 2020/6/4 21:14
     */
    public static void initColumnField(GenTableColumn column, GenTable table) {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getId());
        //设置java字段名
        column.setJavaField(StringUtils.toCamelCase(columnName));

        if (arraysContains(GenConstants.COLUMN_TYPE_STR, dataType)) {
            //字符串类型
            column.setJavaType(GenConstants.TYPE_STRING);
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        } else if (arraysContains(GenConstants.COLUMN_TYPE_TIME, dataType)) {
            //时间类型
            column.setJavaType(GenConstants.TYPE_STRING);
            column.setHtmlType(GenConstants.HTML_DATETIME);

        } else if (arraysContains(GenConstants.COLUMN_TYPE_NUMBER, dataType)) {
            //数字类型
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(GenConstants.TYPE_DOUBLE);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        //插入字段(默认所有字段都需要插入)
        column.setInsert(GenConstants.REQUIRE);
        //编辑字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_EDIT, columnName) && !column.checkPk()) {
            column.setEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_LIST, columnName) && !column.checkPk()) {
            column.setList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_QUERY, columnName) && !column.checkPk()) {
            column.setQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type")
                || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
    }

    /**
     * 生成代码
     * @param table
     * @param columns
     * @param zip
     */
    public static void generatorCode(GenTable table, List<GenTableColumn> columns, ZipOutputStream zip) {
        setPkColumn(table, columns);
        VelocityInitializer.init();
        VelocityContext context = VelocityUtils.prepareContext(table);

        //获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        templates.forEach(template -> {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, ComConstants.UTF8);
            tpl.merge(context, sw);
            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, ComConstants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                logger.error("渲染模板失败，表名：[{}], 错误信息: [{}]", table.getTableName(), e);
            }
        });

    }

    /**
     * 设置主键列信息
     * @param table
     * @param columns
     */
    private static void setPkColumn(GenTable table, List<GenTableColumn> columns) {
        GenTableColumn genTableColumn = columns.stream().filter(column -> column.checkPk()).findAny().orElse(null);
        table.setPkColumn(genTableColumn);

        if(StringUtils.isBlank(table.getPkColumn())) {
            table.setPkColumn(columns.get(0));
        }
    }

    /**
     * 获取字段长度
     *
     * @param columnType
     * @return java.lang.Integer
     * @author YuKai Fan
     * @date 2020/6/4 21:23
     */
    private static Integer getColumnLength(String columnType) {
        String str = "(";
        if (StringUtils.indexOf(columnType, str) > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }

    /**
     * 校验数组是否包含指定值
     *
     * @param arr
     * @param targetValue
     * @return boolean
     * @author YuKai Fan
     * @date 2020/6/4 21:20
     */
    private static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取数据库类型字段
     *
     * @param columnType
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/4 21:15
     */
    private static String getDbType(String columnType) {
        String str = "(";
        if (StringUtils.indexOf(columnType, str) > 0) {
            return StringUtils.substringBefore(columnType, str);
        } else {
            return columnType;
        }
    }


    /**
     * 表名转换成Java类名
     *
     * @param tableName
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/4 20:53
     */
    public static String convertClassName(String tableName) {
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @param replace
     * @param searchList
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/4 20:54
     */
    public static String replaceFirst(String replace, String[] searchList) {
        String text = replace;
        for (String searchString : searchList) {
            if (replace.startsWith(searchString)) {
                text = replace.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 获取模块名
     *
     * @param packageName
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/4 20:55
     */
    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        String moduleName = StringUtils.substring(packageName, lastIndex + 1, nameLength);
        return moduleName;
    }

    /**
     * 获取业务名
     *
     * @param tableName
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/4 20:56
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        String businessName = StringUtils.substring(tableName, lastIndex + 1, nameLength);
        return businessName;
    }

    /**
     * 关键字替换
     *
     * @param name 需要被替换的名字
     * @return java.lang.String 替换后的名字
     * @author YuKai Fan
     * @date 2020/6/4 20:57
     */
    public static String replaceText(String name) {
        return RegExUtils.replaceAll(name, "(?:表|YK)", "");
    }
}
