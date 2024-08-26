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

    private AuthenticationDAO authenticationDAO;
    private EmployeeService employeeService;

    public AuthenticationService() {
        this.authenticationDAO = new AuthenticationDAO();
        this.employeeService = new EmployeeService();
    }

    public int login(String username, String password) {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee != null && employee.checkPassword(password)) {
            Session activeSession = authenticationDAO.getActiveSessionByEmployeeId(employee.getEmpId());
            if (activeSession == null) {
                Session newSession = new Session(employee.getEmpId(), true, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1)); // Set timeout to 1 minute for testing
                authenticationDAO.createSession(newSession);
                return newSession.getSessionId(); 
            } else {
                return activeSession.getSessionId();
            }
        }
        return -1;
    }

    public String logout(int sessionId) {
        Session session = authenticationDAO.getActiveSessionByEmployeeId(sessionId);
        if (session != null) {
            authenticationDAO.deactivateSession(session.getSessionId());
            return "Logout successful.";
        } else {
            return "No active session found for the given session ID.";
        }
    }

    public String register(String lastName, String firstName, String middleInitial, String phoneNum, String emailAddress, String empRole, String username, String password) {
        return employeeService.addEmployee(lastName, firstName, middleInitial, phoneNum, emailAddress, empRole, username, password);
    }
}
