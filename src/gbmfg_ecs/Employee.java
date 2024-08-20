package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service 
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette 
 * Program Description: Provides framework for employee object creation.
 * Date: August 13, 2024
 */
public class Employee {

    private int empId;
    private String lastName;
    private String firstName;
    private String middleInitial;
    private String phoneNum;
    private String emailAddress;
    private String empRole;
    private String username;
    private String password;

    // Constructors
    public Employee() {

    }

    // Constructor is for creating new employees
    public Employee(String lastName, String firstName, String middleInitial,
            String phoneNum, String emailAddress, String empRole,
            String username, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
        this.empRole = empRole;
        this.username = username;
        this.password = password;
    }
    
    // Constructor is for updating employees
    public Employee(int employeeId, String lastName, String firstName, String middleInitial,
            String phoneNum, String emailAddress, String empRole,
            String username, String password) {
        this.empId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
        this.empRole = empRole;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getEmpId() {
        return empId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmpRole() {
        return empRole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    //Behaviors
    
    // Method calls check password from the PasswordHashUtil class
    public boolean checkPassword(String password) {
        return PasswordHashUtil.checkPassword(password, this.password);
    }
}
