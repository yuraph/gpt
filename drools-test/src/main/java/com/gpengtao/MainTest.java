package com.gpengtao;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

/**
 * Created by pengtao.geng on 2016/9/6.
 */
public class MainTest {

    public static void main(String[] args) {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();

        String compile = compiler.compile("/gpt.xls", InputType.XLS);

        System.out.println(compile);
    }
}
