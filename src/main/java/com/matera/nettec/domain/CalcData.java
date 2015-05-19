/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.domain;

/**
 *
 *
 * @author user
 */
public class CalcData {

    public static final double TAX = 0.01;

    private double salary;
    private double percentage;

    public double getSalary() {

        return salary;
    }

    public void setSalary(double salary) {

        this.salary = salary;
    }

    public double getPercentage() {

        return percentage;
    }

    public void setPercentage(double percentage) {

        this.percentage = percentage;
    }
}
