package com.gpengtao.sql;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.gpengtao.sql.model.ColumnDesc;
import com.gpengtao.sql.util.OutFileUtil;
import com.gpengtao.sql.util.TableInfoUtil;
import com.gpengtao.sql.util.TypeMappings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.annotation.Nullable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gpengtao on 15/10/18.
 */
public class GenerateSqlMain {

    public static void main(String[] args) throws SQLException, IOException {

        String url = "jdbc:mysql://10.255.206.132:33008/data_production_plan?useUnicode=true&amp;characterEncoding=UTF-8";
        String username = "dev";
        String password = "096667f9c7d0396d";
        String tableName = "check_result";

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource(url, username, password, false);

        List<String> tables = findTables(dataSource, tableName);
        tables.forEach(table -> {
            OutFileUtil.initFile(table);

            List<ColumnDesc> columnDescList = TableInfoUtil.findTableColumnInfo(dataSource, table);

            printInsertSql(table, columnDescList, table);

            printSelectSql(table, columnDescList);

            printUpdateSql(table, columnDescList);

            printModelFields(table, columnDescList);
        });

    }

    private static List<String> findTables(SingleConnectionDataSource dataSource, String tableName) {
        if (Strings.isNullOrEmpty(tableName) || "*".equals(tableName)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Map<String, Object>> listMap = jdbcTemplate.queryForList("show tables");

            List<String> result = Lists.newArrayList();
            listMap.forEach(one -> {
                List<String> list = one.values().stream().map(new Function<Object, String>() {
                    @Nullable
                    @Override
                    public String apply(@Nullable Object input) {
                        return input == null ? "" : input.toString();
                    }
                }).collect(Collectors.toList());
                result.addAll(list);
            });
            return result;
        } else {
            return Lists.newArrayList(tableName);
        }
    }

    private static void printUpdateSql(String table, List<ColumnDesc> columnDescList) {
        String template = "<if test=\"%s != null\">\n"
                + "\t%s = #{%s},\n"
                + "</if>";

        String templateVarchar = "<if test=\"%s != null and %s != ''\">\n"
                + "\t%s = #{%s},\n"
                + "</if>";

        for (ColumnDesc columnDesc : columnDescList) {
            String propertyName = getJavaPropertyName(columnDesc.getField());

            String sql;
            if (StringUtils.containsIgnoreCase(columnDesc.getType(), "char") || StringUtils.containsIgnoreCase(columnDesc.getType(), "text")) {
                sql = String.format(templateVarchar, propertyName, propertyName, columnDesc.getField(), propertyName);
            } else {
                sql = String.format(template, propertyName, columnDesc.getField(), propertyName);
            }

            OutFileUtil.writeSql(table, sql + "\n");
        }

        OutFileUtil.writeSql(table, "\n\n");
    }

    private static void printModelFields(String table, List<ColumnDesc> columnDescList) {
        for (ColumnDesc columnDesc : columnDescList) {
            String javaType = TypeMappings.findJaveType(columnDesc.getType());
            String propertyName = getJavaPropertyName(columnDesc.getField());

            String comment = "/**\n" +
                    " * " + columnDesc.getComment() + "\n" +
                    " */\n";

            String field = "private " + javaType + " " + propertyName + ";\n\n";

            OutFileUtil.writeField(table, comment);
            OutFileUtil.writeField(table, field);
        }
    }

    private static void printSelectSql(String table, List<ColumnDesc> columnDescList) {
        List<String> show = Lists.newArrayList();
        for (ColumnDesc column : columnDescList) {
            if (column.getField().contains("_")) {
                show.add("\t" + column.getField() + " as " + getJavaPropertyName(column.getField()));
            } else {
                show.add("\t" + column.getField());
            }
        }

        String sql = Joiner.on(",\n").join(show) + "\n";

        String finalSql = "<sql id=\"selectSql\">\n"
                + sql
                + "</sql>";

        OutFileUtil.writeSql(table, finalSql + "\n\n");
    }

    private static void printInsertSql(String table, List<ColumnDesc> columnDescList, String tbaleName) {
        List<String> nameList = Lists.newArrayList();
        List<String> propertyNameList = Lists.newArrayList();
        for (ColumnDesc desc : columnDescList) {
            if ("auto_increment".equals(desc.getExtra()) || "on update CURRENT_TIMESTAMP".equals(desc.getExtra())) {
                continue;
            }
            String field = desc.getField();
            String propertyName = getJavaPropertyName(field);
            nameList.add(field);
            propertyNameList.add(propertyName);
        }

        nameList = addTab(nameList);
        List<String> propertyNameListItem = addBracketItem(propertyNameList);
        propertyNameList = addBracket(propertyNameList);
        propertyNameList = addTab(propertyNameList);
        propertyNameListItem = addTab(propertyNameListItem);

        String insertOneTem = "\tINSERT INTO %s (\n" +
                "%s\n" +
                "\t) values (\n" +
                "%s\n" +
                "\t)\n";
        String insertListTem = "\tINSERT INTO %s(\n" +
                "%s\n" +
                "\t) values \n" +
                "\t<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n" +
                "\t(\n" +
                "%s\n" +
                "\t)\n" +
                "\t</foreach>\n";

        String sql1 = String.format(insertOneTem,
                tbaleName,
                Joiner.on(",\n").join(nameList),
                Joiner.on(",\n").join(propertyNameList));

        String sql2 = String.format(insertListTem,
                tbaleName,
                Joiner.on(",\n").join(nameList),
                Joiner.on(",\n").join(propertyNameListItem));

        String finalSql1 = "<insert id=\"insertOne\" parameterType=\"xxx\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n"
                + sql1
                + "</insert>";

        String finalSql2 = "<insert id=\"insertList\" parameterType=\"java.util.List\">\n"
                + sql2
                + "</insert>";

        OutFileUtil.writeSql(table, finalSql1 + "\n\n");
        OutFileUtil.writeSql(table, finalSql2 + "\n\n");
    }

    private static List<String> addBracketItem(List<String> nameList) {
        List<String> result = Lists.newArrayList();
        for (String name : nameList) {
            result.add("#{item." + name + "}");
        }
        return result;
    }

    private static List<String> addBracket(List<String> nameList) {
        List<String> result = Lists.newArrayList();
        for (String name : nameList) {
            result.add("#{" + name + "}");
        }
        return result;
    }

    private static List<String> addTab(List<String> nameList) {
        List<String> result = Lists.newArrayList();
        for (String name : nameList) {
            result.add("\t\t" + name);
        }
        return result;
    }

    private static String getJavaPropertyName(String field) {
        if (!field.contains("_")) {
            return field;
        }
        int position = field.indexOf("_");
        char at = field.charAt(position + 1);
        char upAt = at;
        if (at >= 'a' && at <= 'z') {
            upAt = (char) (at + ('A' - 'a'));
        }
        String atString = new String(new char[]{at});
        String upAtString = new String(new char[]{upAt});

        String replace = field.replace("_" + atString, upAtString);

        return getJavaPropertyName(replace);
    }
}
