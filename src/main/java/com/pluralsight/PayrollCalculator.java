package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PayrollCalculator {

    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        String userText;
        FileReader fileReader;
        BufferedReader bufferedReader;

        //Prompting fileName
        String fileName;
        System.out.println("Enter the name of the file to process: ");
        userText = userInput.nextLine();
        fileName = userText.strip().toLowerCase();
        //Prompting file to create
        String createFile;
        System.out.println("Enter the name of the payroll file to create: ");
        userText = userInput.nextLine();
        createFile = "src/main/resources/" + userText.strip().toLowerCase();

        String fileDirectory = "src/main/resources/" + fileName;

        //list of employees in the text file
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            fileReader = new FileReader(fileDirectory);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            for (int i = 0;line != null; i++) {
                if (i != 0) {
                    String[] employeeDataText = line.split(Pattern.quote("|"));
                    employees.add(constructEmployee(employeeDataText));
                }
                line = bufferedReader.readLine();
            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("IDK" + e.getMessage() );
        }
        catch (IOException e) {
            System.err.println("IDK" + e.getMessage() );
        }

        try {
            FileWriter fileWriter = new FileWriter(createFile);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.write("id|name|gross pay\n");
            for (Employee currentEmployee : employees) {
                bufWriter.write(String.format("%d|%s|%.2f\n", currentEmployee.getEmployeeId(), currentEmployee.getName(), currentEmployee.getGrossPay()));
            }
            bufWriter.close();
        } catch (IOException e) {
            System.err.println("IDK" + e.getMessage() );
        }
    }

    public static Employee constructEmployee (String[] employeeDataText) {
        long employeeId = Long.parseLong(employeeDataText[0]);
        String name = employeeDataText[1];
        double hoursWorked = Double.parseDouble(employeeDataText[2]);
        double payRate = Double.parseDouble(employeeDataText[3]);

        Employee employee = new Employee(employeeId, name, hoursWorked, payRate);
        return employee;
    }
}
