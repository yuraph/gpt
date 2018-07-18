package com.gpengtao.java.collection;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gpengtao.java.model.Person;
import com.gpengtao.java.model.PersonSum;
import com.gpengtao.utils.ModelGenerateUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2017/9/4.
 */
public class StreamTest {
	@Test
	public void test() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4);

		List<Integer> list2 = list.stream().filter(integer -> integer > 2).collect(Collectors.toList());

		System.out.println(list);
		System.out.println(list2);
	}

	@Test
	public void test_reduce() {
		List<Integer> list = Lists.newArrayList();
		for (int i = 1; i <= 1000; i++) {
			list.add(i);
		}

		AtomicInteger mapCount = new AtomicInteger(0);
		AtomicInteger reduceCount = new AtomicInteger(0);

		Optional<Integer> ret = null;
		try {
			ret = list.parallelStream()
					.map(integer -> {
						int i = integer * 2;
						System.out.println("map " + integer + " -> " + i);
						mapCount.incrementAndGet();
						return i;
					})
					.reduce((s1, s2) -> {
						int sum = s1 + s2;
						System.out.println("reduce " + s1 + " " + s2 + " -> " + sum);
						reduceCount.incrementAndGet();
						if (sum > 0) {
							System.out.println("===========================" + Thread.currentThread());
							throw new RuntimeException("too big");
						}
						return sum;
					});
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		System.out.println("结果" + ret);

		System.out.println("map count " + mapCount);
		System.out.println("reduce count " + reduceCount);
	}

	@Test
	public void test_grouping_min_by() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 1, 1);

		Map<String, Optional<Integer>> groups = list.stream().collect(Collectors.groupingBy(one -> {
			if (one > 3) {
				return "big";
			} else {
				return "small";
			}
		}, Collectors.minBy(Comparator.naturalOrder())));

		System.out.println(groups);
	}

	@Test
	public void test_xing_neng() {
		List<Integer> list = Lists.newArrayList(1, 2);

		int times = 100000000;

		for (int count = 0; count < 100; count++) {
			long start = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				list.forEach(x -> {
					int a = x * x;
				});
			}
			System.out.println(System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				for (Integer x : list) {
					int a = x * x;
				}
			}
			System.out.println(System.currentTimeMillis() - start);

			System.out.println("-----------------------------");
		}
	}

	@Test
	public void test_map_compute() {
		Map<String, List<Integer>> map = Maps.newHashMap();

		for (int i = 0; i < 10; i++) {
			map.compute("a", (key, value) -> {
				if (value == null) {
					value = Lists.newArrayList(100);
				} else {
					value.add(100);
				}
				return value;
			});

			System.out.println(map);
		}
	}

	@Test
	public void test_group() throws Exception {
		List<Person> persons = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			persons.add(ModelGenerateUtil.generateModel(Person.class));
		}

		Map<String, Map<Integer, List<Person>>> group = persons.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.groupingBy(Person::getAge)));

		System.out.println(group);
	}

	@Test
	public void test_group_2() throws Exception {
		List<Person> persons = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			persons.add(ModelGenerateUtil.generateModel(Person.class));
		}

		// 先分组，再把list转化成一个
		Map<String, PersonSum> collect = persons.stream()
				.collect(Collectors.groupingBy(Person::getCity, Collectors.collectingAndThen(Collectors.toList(), personList -> {
					Person person = personList.get(0);
					int sumAge = personList.stream().mapToInt(Person::getAge).sum();

					PersonSum personSum = new PersonSum();
					personSum.setCity(person.getCity());
					personSum.setAgeSum(sumAge);
					return personSum;
				})));

		System.out.println(collect);
	}

	@Test
	public void test_partition() {
		List<Integer> list = Lists.newArrayList(2, 3, 4, 5);

		Map<Boolean, List<Integer>> booleanMap = list.stream().collect(Collectors.partitioningBy(x -> x > 1));

		System.out.println(booleanMap.get(true));

		System.out.println(booleanMap.get(false));
	}

}
