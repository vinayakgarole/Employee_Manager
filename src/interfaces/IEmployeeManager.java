package interfaces;

import model.EmployeeContacts;

public interface IEmployeeManager {

    int createContacts(EmployeeContacts employeeContacts);

    void showContacts();

    void editContacts(String firstName);

    void deleteContacts(String firstName);

    void searchByID();

    void sortBySalary();

    void calculateAverageSalary();
}