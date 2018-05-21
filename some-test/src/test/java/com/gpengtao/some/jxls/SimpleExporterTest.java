package com.gpengtao.some.jxls;

import com.google.common.collect.Lists;
import com.gpengtao.some.model.Address;
import com.gpengtao.some.model.Employee;
import org.junit.Test;
import org.jxls.template.SimpleExporter;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/21 下午3:08.
 */
public class SimpleExporterTest {

    @Test
    public void test_simple_exporter() throws Exception {

        List<Employee> employees = Lists.newArrayList();
        employees.add(mockEmployee());

        FileOutputStream outputStream = new FileOutputStream(new File("target/11.xls"));

        List<String> header = Lists.newArrayList();
        header.add("名字");
        header.add("工资");
        header.add("工作城市");

        SimpleExporter exporter = new SimpleExporter();
        exporter.gridExport(header, employees, "name, payment, address.city", outputStream);
    }

    private static Employee mockEmployee() {
        Employee e1 = new Employee();
        e1.setName("hello");
        e1.setPayment(new BigDecimal("21256.12"));
        e1.setAddress(new Address("北京", "太阳宫"));
        return e1;
    }
}
