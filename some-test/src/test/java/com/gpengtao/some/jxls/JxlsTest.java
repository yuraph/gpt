package com.gpengtao.some.jxls;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.transform.poi.PoiTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by pengtao.geng on 2018/5/16 上午10:50.
 */
public class JxlsTest {

    @Test
    public void test_create_area() throws IOException, InvalidFormatException {
        // getting input stream for our report template file from classpath
        InputStream is = getClass().getResourceAsStream("/object_collection_template.xls");

        // creating POI Workbook
        Workbook workbook = WorkbookFactory.create(is);

        // creating JxlsPlus transformer for the workbook
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);

        // creating XlsCommentAreaBuilder instance
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);

        // using area builder to construct a list of processing areas
        List<Area> xlsAreaList = areaBuilder.build();

        // getting the main area from the list
        Area xlsArea = xlsAreaList.get(0);
    }
}
