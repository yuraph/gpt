package com.gpengtao.sql.util;

/**
 * Created by pengtao.geng on 2017/3/15.
 */
public class TypeMappings {

    public static String findJavaType(String sqlType) {
        if (sqlType.contains("bigint")) {
            return "long";
        }
        if (sqlType.contains("date")) {
            return "Date";
        }
        if (sqlType.contains("varchar")) {
            return "String";
        }
        if (sqlType.contains("timestamp")) {
            return "Date";
        }
        if (sqlType.startsWith("int")) {
            return "int";
        }
        if (sqlType.startsWith("decimal")) {
            return "BigDecimal";
        }
        if (sqlType.startsWith("smallint")) {
            return "int";
        }
        if (sqlType.startsWith("text")) {
            return "String";
        }
        if (sqlType.startsWith("tinyint(1)")) {
            return "boolean";
        }
        if (sqlType.startsWith("tinyint")) {
            return "int";
        }
        if (sqlType.startsWith("longtext")) {
            return "String";
        }

        throw new RuntimeException("不支持映射sqlType: " + sqlType);
    }

}
