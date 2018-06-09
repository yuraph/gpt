package com.gpengtao.some.jxls.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengtao.geng on 2018/5/7 下午8:47.
 */
public class Employee {
    private String name;
    private Date birthDate;
    private BigDecimal payment;
    private int bonus;

    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
