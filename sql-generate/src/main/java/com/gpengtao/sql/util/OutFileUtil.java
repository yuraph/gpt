package com.gpengtao.sql.util;


import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by pengtao.geng on 2017/8/22.
 */
public class OutFileUtil {
    private static String fieldFile = "sql-generate/target/field.txt";

    public static void init() {

        File file = new File(fieldFile);
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

    public static void writeField(String line) {
        doWrite(fieldFile, line);
    }

    public static void doWrite(String file, String line) {
        try {
            Files.append(line, new File(file), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
