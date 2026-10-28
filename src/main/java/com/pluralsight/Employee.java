package com.pluralsight;

public class Employee {
    private long employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public Employee (long employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    public long getEmployeeId () {
        return this.employeeId;
    }

    public double getGrossPay () {
        double grossPay = this.hoursWorked * this.payRate;
        return grossPay;
    }

}
