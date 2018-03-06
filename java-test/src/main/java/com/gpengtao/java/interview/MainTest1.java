package com.gpengtao.java.interview;

import com.google.common.collect.Lists;

import java.util.List;

public class MainTest1 {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        List<List<Integer>> result = Lists.newArrayList();

        combination(list, Lists.newArrayList(), 3, result);

        for (List<Integer> one : result) {
            System.out.println(one);
        }
    }

    private static void combination(List<Integer> list, List<Integer> indexes, int num, List<List<Integer>> result) {
        if (indexes.size() == num) {
            List<Integer> one = Lists.newArrayList();
            for (Integer index : indexes) {
                one.add(list.get(index));
            }
            result.add(one);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            indexes.add(i);

            combination(list, indexes, num, result);

            indexes.remove(indexes.size() - 1);
        }
    }
}
