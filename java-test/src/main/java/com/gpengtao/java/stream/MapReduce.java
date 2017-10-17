package com.gpengtao.java.stream;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by pengtao.geng on 2017/10/17.
 */
public class MapReduce {

    public static void main(String[] args) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        List<String> list = Lists.newArrayList();
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x01 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x02 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x03 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x04 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x05 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x06 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x07 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x08 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x09 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x10 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x11 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x12 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x13 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x14 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x15 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x16 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x17 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x18 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x19 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x20 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x21 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x22 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x23 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x24 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x25 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x26 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x27 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x28 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x29 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");
        list.add("select store_code, sku_code, SUM(discount_price) as price from tb_test_2017x30 where order_date>='2017-06-16' and order_date<='2017-09-13' group by store_code, sku_code");

        long start = System.currentTimeMillis();

        Optional<List<Map<String, Object>>> result = list.parallelStream()
                .map(sql -> {
                    List<Map<String, Object>> listMap = queryForList(sql);
                    System.out.println("查询sql " + sql + " 返回size: " + listMap.size());
                    return listMap;
                })
                .reduce((list1, list2) -> {
                    List<Map<String, Object>> all = Lists.newArrayList();
                    all.addAll(list1);
                    all.addAll(list2);

                    System.out.println("准备reduce两个list，list1 size= " + list1.size() + " ,list2 size= " + list2.size());

                    Multimap<String, Map<String, Object>> multimap = ArrayListMultimap.create();
                    all.forEach(map -> {
                        String key = map.get("store_code").toString() + map.get("sku_code").toString();
                        multimap.put(key, map);
                    });

                    System.out.println("把list分组完成，list size= " + all.size() + ",multimap size=" + multimap.keySet().size());

                    List<Map<String, Object>> ret = Lists.newArrayList();

                    for (Map.Entry<String, Collection<Map<String, Object>>> entry : multimap.asMap().entrySet()) {
                        Collection<Map<String, Object>> rows = entry.getValue();

                        double sum = rows.stream().mapToDouble(value -> Double.valueOf(value.get("price").toString())).summaryStatistics().getSum();

                        Map<String, Object> one = rows.stream().findFirst().get();
                        one.put("price", sum);

                        ret.add(one);
                    }

                    System.out.println("-----------------reduce两个list，all size=" + all.size() + " 聚合之后size: " + ret.size());

                    return ret;
                });

        List<Map<String, Object>> maps = result.get();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>结束，耗时: " + (System.currentTimeMillis() - start));
    }

    private static List<Map<String, Object>> queryForList(String sql) {
        return null;
    }
}
