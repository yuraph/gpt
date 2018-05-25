package com.gpengtao.oter;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/24 下午8:03.
 */
public class TxtProcess {

    @Test
    public void test() throws IOException {
        List<String> lines = Files.readLines(new File("/Users/gpengtao/Desktop/人类简史.txt"), Charset.defaultCharset());

        for (String line : lines) {
            if (line.length() > "以，当中信出版社请我为本书的中文版作序时，我也就出于好奇而暂时应承了下来：“先看看吧。".length() && line.charAt(line.length() - 1) != '。') {
                System.out.print(line + ",");

                Files.asCharSink(new File("/Users/gpengtao/Desktop/人类简史2.txt"), Charset.defaultCharset(), FileWriteMode.APPEND).write(line);
            } else {
                System.out.println(line);
                Files.asCharSink(new File("/Users/gpengtao/Desktop/人类简史2.txt"), Charset.defaultCharset(), FileWriteMode.APPEND).write(line + "\n");

            }
        }
    }
}
