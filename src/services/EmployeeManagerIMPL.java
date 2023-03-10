package services;

import interfaces.IEmployeeManager;
import model.EmployeeContacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagerIMPL implements IEmployeeManager {

    private static ArrayList<EmployeeContacts> contactsOfPersons = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in); // for user input

    @Override
    public int createContacts(EmployeeContacts employeeContacts) {
        try {
            Connection connection = getConnection();
            System.out.println("Connection is successful!!! " + connection);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            return -1;
        }
        contactsOfPersons.add(employeeContacts);
        System.out.println(employeeContacts.toString());
        return contactsOfPersons.indexOf(employeeContacts);
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Employee_Manager";
        String userName = "root";
        String password = "root123";
        return DriverManager.getConnection(jdbcUrl, userName, password);
    }

    private int getIndexForNextEmptyLocation() {

        for (int i = 0; i < contactsOfPersons.size(); i++) {

            if (null == contactsOfPersons.get(i)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void showContacts() {
        System.out.println("Showing All EmployeeContacts Details.....");

        for (int i = 0; i < contactsOfPersons.size(); i++) {
            showContactsDetails(contactsOfPersons.get(i));
        }
    }

    @Override
    public void editContacts(String firstName) {
        int i = findByFirstName(firstName);
        if (i != -1) {
            editContactDetails(contactsOfPersons.get(i));
        }
        showContacts();
    }

    public void deleteContacts(String firstName) {
        int i = findByFirstName(firstName);

        if (i != -1) {
            contactsOfPersons.set(i, null);
        } else {
            System.out.println("Name not found");
        }
        showContacts();
    }

    @Override
    public void searchByID() {
        System.out.println("Enter the ID: ");
        int id = sc.nextInt();

        contactsOfPersons.stream()
                .filter(n -> n.getId()== id)
                .forEach(n -> System.out.println("Search ID: " + n.getId()));
    }

    @Override
    public void sortBySalary() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Ascending 2. Descending");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Collections.sort(contactsOfPersons, Comparator.comparing(EmployeeContacts::getSalary));
                break;
            case 2:
                Collections.sort(contactsOfPersons, Comparator.comparing(EmployeeContacts::getSalary).reversed());
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    @Override
    public void calculateAverageSalary() {
        double totalSalary = 0;
        for (EmployeeContacts contacts : contactsOfPersons) {
            totalSalary += contacts.getSalary();
        }
        double avgSalary = totalSalary / contactsOfPersons.size();
        System.out.println("Average Salary: " + avgSalary);
    }


    private void editContactDetails(EmployeeContacts employeeContacts) {
        EmployeeContacts employeeContacts1 = new EmployeeContacts();

        int exit = 1;
        do {
            System.out.println("Select option to edit: "+"\n" + "1.First Name " +"\n" + "2.Last Name " + "\n" + "3.Id " +
                    "\n" + "4.Phone Number " +"\n" + "5.Mail " + "\n" + "6.salary ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println(" FirstName :: ");
                    employeeContacts.setFirstName(sc.next());
                    break;
                case 2:
                    System.out.println(" LastName :: ");
                    employeeContacts.setLastName(sc.next());
                    break;
                case 3:
                    System.out.println(" ID :: ");
                    employeeContacts.setId(sc.nextInt());
                    break;
                case 4:
                    System.out.println(" Salary :: ");
                    employeeContacts.setSalary(sc.nextInt());
                    break;
                case 5:
                    System.out.println(" PhoneNumber :: ");
                    employeeContacts.setPhoneNumber(sc.nextLong());
                    break;
                default:
                    System.out.println(" ****---- Invalid Input ----**** ");
            }
            System.out.println(" Press 1: If you want to update anything else " + '\n' + " Press 0: Exit to Menu ");
            exit = sc.nextByte();
        } while (exit != 0);
    }

    public void showContactsDetails(EmployeeContacts employeeContacts) {

        if (employeeContacts != null) {
            System.out.println("firstName = " + employeeContacts.getFirstName());
            System.out.println("lastName = " + employeeContacts.getLastName());
            System.out.println("ID = " + employeeContacts.getId());
            System.out.println("Salary = " + employeeContacts.getSalary());
            System.out.println("phoneNumber = " + employeeContacts.getPhoneNumber());
        }
    }

    private int findByFirstName(String firstName) {

        for (int i = 0; i < contactsOfPersons.size(); i++) {
            EmployeeContacts employeeContacts = contactsOfPersons.get(i);

            if (null != employeeContacts && firstName.equalsIgnoreCase(employeeContacts.getFirstName())) {
                return i;
            }
        }
        return -1;
    }
}