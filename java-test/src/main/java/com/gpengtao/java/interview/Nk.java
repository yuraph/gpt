package com.gpengtao.java.interview;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by pengtao.geng on 2017/7/27.
 */
public class Nk {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);

        display(list);
    }

    private static void display(List<Integer> list) {
        //        if (size == 1) {
//            System.out.println(list);
//            return;
//        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> subList = subList(list, i);
            if (subList.size() > 1) {
                System.out.println(subList);
                display(subList);
            }
        }
    }

    private static List<Integer> subList(List<Integer> list, int i) {
        List<Integer> result = Lists.newArrayList();
        for (int j = 0; j < list.size(); j++) {
            if (j != i) {
                result.add(list.get(j));
            }
        }
        return result;
    }
}
