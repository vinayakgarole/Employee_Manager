package controller;

import interfaces.IEmployeeManager;
import model.EmployeeContacts;
import services.EmployeeManagerIMPL;

import java.util.Scanner;

public class EmployeeManagerMain {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int i = 0;
        IEmployeeManager employeeManager = new EmployeeManagerIMPL();

        do {
            System.out.println("1 Press create contact");
            System.out.println("2 Press show all contacts");
            System.out.println("3 Press Edit Contact");
            System.out.println("4 Press Delete Contact");
            System.out.println("5 Press Check By ID Contact");
            System.out.println("6 Press Sort by Salary Contact");
            System.out.println("7 Press Calculate Average salary Contact");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Create contact");
                    EmployeeContacts  employeeContacts = getContacts();
                    int index = employeeManager.createContacts(employeeContacts) + 1;
                    System.out.println("Contact id = " + index);
                    break;
                case 2:
                    employeeManager.showContacts();
                    break;
                case 3:
                    System.out.println("Enter First Name: ");
                    String firstName = sc.next();
                    employeeManager.editContacts(firstName);
                    break;
                case 4:
                    System.out.println("Enter the first name of details you want to delete");
                    String firstName1 = sc.next();
                    employeeManager.deleteContacts(firstName1);
                    break;
                case 5:
                    employeeManager.searchByID();
                    break;
                case 6:
                    employeeManager.sortBySalary();
                    break;
                case 7:
                    employeeManager.calculateAverageSalary();
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            System.out.println("Do you want to continue press 1.....");
            i = sc.nextInt();
        } while (i == 1);
    }

    private static EmployeeContacts getContacts() {
        EmployeeContacts employeeContacts = new EmployeeContacts();
        System.out.println("Enter First Name: ");
        String firstName = sc.next();
        employeeContacts.setFirstName(firstName);

        System.out.println("Enter Last Name: ");
        String lastName = sc.next();
        employeeContacts.setLastName(lastName);

        System.out.println("Enter ID: ");
        int id = sc.nextInt();
        employeeContacts.setId(id);

        System.out.println("Enter Phone Number: ");
        long phoneNumber = sc.nextLong();
        employeeContacts.setPhoneNumber(phoneNumber);

        System.out.println("Enter Salary: ");
        int salary = sc.nextInt();
        employeeContacts.setSalary(salary);
        return employeeContacts;
    }
}


/*
Program: Write a Java program to create a class called "EmployeeManager" with instance variables "employees" (a list of Employee objects) and "numEmployees" (the number of employees in the list). Include the following methods:
A method to add an employee to the list
    A method to remove an employee from the list
    A method to search for an employee by their ID
    A method to sort the employees in the list by their salary (ascending or descending order)
A method to calculate the average salary of all employees
    A method to print the details of all employees in the list
 */