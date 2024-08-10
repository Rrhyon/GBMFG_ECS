package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;

public class AuthenticationService {

    private AuthenticationDAO authenticationDAO;
    private EmployeeService employeeService;

    public AuthenticationService() {
        this.authenticationDAO = new AuthenticationDAO();
        this.employeeService = new EmployeeService();
    }

    public String login(String username, String password) {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee != null && employee.checkPassword(password)) {
            Session activeSession = authenticationDAO.
                    getActiveSessionByEmployeeId(employee.getEmpId());
            if (activeSession == null) {
                Session newSession = new Session(employee.getEmpId(), true, 
                        LocalDateTime.now(), LocalDateTime.now().plusHours(1));
                authenticationDAO.createSession(newSession);
                return "Login successful.";
            } else {
                return "User already has an active session.";
            }
        }
        return "Invalid username or password.";
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

    public String register(String lastName, String firstName, 
            String middleInitial, String phoneNum, String emailAddress, 
            String empRole, String username, String password) {
        return employeeService.addEmployee(lastName, firstName, middleInitial, 
                phoneNum, emailAddress, empRole, username, password);
    }
}
