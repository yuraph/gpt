package com.gpengtao.sql.util;


import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by pengtao.geng on 2017/8/22.
 */
public class OutFileUtil {

    private static String sqlFile = "sql-generate/target/%s_sql.txt";

    private static String fieldFile = "sql-generate/target/%s_field.txt";

    private static String file = "sql-generate/target/%s.txt";

    public static void initFile(String table) {
        doInitFile(String.format(file, table));
    }

    private static void doInitFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeSql(String table, String line) {
        String fileName = String.format(file, table);
        doWrite(fileName, line);
    }

    public static void writeField(String table, String line) {
        String fileName = String.format(file, table);
        doWrite(fileName, line);
    }

    public static void doWrite(String file, String line) {
        try {
            Files.append(line, new File(file), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
