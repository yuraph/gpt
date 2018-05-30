package com.gpengtao.java.atom;

import com.gpengtao.java.model.Person;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pengtao.geng on 2018/5/29 下午9:14.
 */
public class AtomTest {

    @Test
    public void test() {
        AtomicInteger integer = new AtomicInteger();
    }

    @Test
    public void test_unsafe_get_value_from_field_offset() throws Exception {
        Field personNameField = Person.class.getDeclaredField("name");
        Field personCityField = Person.class.getDeclaredField("city");
        Field personAgeField = Person.class.getDeclaredField("age");

        Unsafe unsafe = getUnsafeInstance();

        System.out.println(personNameField.getName() + " offset: " + unsafe.objectFieldOffset(personNameField));
        System.out.println(personCityField.getName() + " offset: " + unsafe.objectFieldOffset(personCityField));
        System.out.println(personAgeField.getName() + " offset: " + unsafe.objectFieldOffset(personAgeField));

        Person person = new Person();
        person.setName("gpt");
        person.setAge(18);
        person.setCity("beijing");

        int age = unsafe.getInt(person, unsafe.objectFieldOffset(personAgeField));
        System.out.println("通过unsafe获得的age: " + age);
    }

    private static Unsafe getUnsafeInstance() throws Exception {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        // return (Unsafe) theUnsafeInstance.get(null);是等价的
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
