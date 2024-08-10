package gbmfg_ecs;

/**
 *
 * @author phillip.tette
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

    // Constructors, getters, and setters
    public Employee() {
    }

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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordHashUtil.hashPassword(password);
    }

    public boolean checkPassword(String password) {
        return PasswordHashUtil.checkPassword(password, this.password);
    }
}
