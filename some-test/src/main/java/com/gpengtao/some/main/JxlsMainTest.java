package com.gpengtao.some.main;

import com.google.common.collect.Lists;
import com.gpengtao.some.model.Employee;
import com.gpengtao.utils.ModelGenerateUtil;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/7 下午8:46.
 */
public class JxlsMainTest {

    public static void main(String[] args) throws Exception {

        List<Employee> employees = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            employees.add(ModelGenerateUtil.generateModel(Employee.class));
        }

        InputStream is = JxlsMainTest.class.getResourceAsStream("/object_collection_template.xls");
        OutputStream os = new FileOutputStream("some-test/target/out.xls");

        Context context = new Context();
        context.putVar("employees", employees);

        JxlsHelper helper = JxlsHelper.getInstance();
        helper.processTemplate(is, os, context);
    }

}
