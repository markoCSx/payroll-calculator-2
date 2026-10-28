package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PayrollCalculator {

    public static void main(String[] args) {

        FileReader fileReader;
        BufferedReader bufferedReader;
        String fileName = "src/main/resources/employees.csv";
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            for (int i = 0;line != null; i++) {
                if (i != 0) {
                    String[] employeeDataText = line.split(Pattern.quote("|"));
                    employees.add(constructEmployee(employeeDataText));
                }
                line = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("IDK" + e.getMessage() );
        }
        catch (IOException e) {
            System.err.println("IDK" + e.getMessage() );
        }

        for (Employee currentEmployee : employees) {
            currentEmployee.display();
        }

    }

    public static Employee constructEmployee (String[] employeeDataText) {
        long employeeId = Long.parseLong(employeeDataText[0]);
        String name = employeeDataText[1];
        double hoursWorked = Double.parseDouble(employeeDataText[2]);
        double payRate = Double.parseDouble(employeeDataText[3]);
        return new Employee(employeeId, name, hoursWorked, payRate);
    }
}
