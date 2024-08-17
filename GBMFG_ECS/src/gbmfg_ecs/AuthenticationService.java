package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;

public class AuthenticationService {

    // mapping the classes to variables for calls
    private AuthenticationDAO authenticationDAO;
    private EmployeeService employeeService;

    public AuthenticationService() {
        this.authenticationDAO = new AuthenticationDAO();
        this.employeeService = new EmployeeService();
    }

    /* When clicking login, the program will search for the username in the DB.
     * If not null and the entered password's hash matches the hash saved in the
     * DB, the program will search for an active session. If a session does not 
     * already exist, a new session will be created using the employee's ID.
     */
    public int login(String username, String password) {
    Employee employee = employeeService.getEmployeeByUsername(username);
    if (employee != null && employee.checkPassword(password)) {
        Session activeSession = authenticationDAO.getActiveSessionByEmployeeId(employee.getEmpId());
        if (activeSession == null) {
            Session newSession = new Session(employee.getEmpId(), true, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
            authenticationDAO.createSession(newSession);
            return newSession.getSessionId(); // Return the new session ID
        } else {
            return activeSession.getSessionId(); // Return existing session ID
        }
    }
    return -1; // Indicate login failure
}





    // When a user finishes their work and clicks logout, the program will search
    // for the sessionId and terminate the session.
    public String logout(int sessionId) {
    System.out.println("Attempting to logout session ID: " + sessionId);
    Session session = authenticationDAO.getSessionById(sessionId);
    if (session != null) {
        authenticationDAO.deactivateSession(session.getSessionId());
        return "Logout successful.";
    } else {
        return "No active session found for the given session ID.";
    }
}


    /* Pending additional security controls for effectiveness, this method will
     * allow a new employee to register for an account.
     */
    public String register(String lastName, String firstName, 
            String middleInitial, String phoneNum, String emailAddress, 
            String empRole, String username, String password) {
        return employeeService.addEmployee(lastName, firstName, middleInitial, 
                phoneNum, emailAddress, empRole, username, password);
    }
}
