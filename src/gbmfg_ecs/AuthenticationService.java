package gbmfg_ecs;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    private final AuthenticationDAO authenticationDAO;

    public AuthenticationService() {
        this.authenticationDAO = new AuthenticationDAO();
    }

    public int login(String username, String password) {
        Optional<Employee> employeeOpt = authenticationDAO.getEmployeeByUsername(username);

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (employee.checkPassword(password)) {
                Optional<Session> activeSessionOpt = authenticationDAO.getActiveSessionByEmployeeId(employee.getEmpId());

                if (activeSessionOpt.isPresent()) {
                    return activeSessionOpt.get().getSessionId();
                } else {
                    try {
                        Session newSession = new Session(employee.getEmpId(), true,
                                LocalDateTime.now(), LocalDateTime.now().plusHours(1));
                        authenticationDAO.createSession(newSession);
                        return newSession.getSessionId();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, "Error creating session: {0}", e.getMessage());
                        return -1;  // Indicate session creation failure
                    }
                }
            }
        }

        return -1;  // Indicate login failure
    }

    public String logout(int sessionId) {
        try {
            Optional<Session> sessionOpt = authenticationDAO.getActiveSessionByEmployeeId(sessionId);
            if (sessionOpt.isPresent()) {
                authenticationDAO.deactivateSession(sessionId);
                return "Logout successful.";
            } else {
                return "No active session found for the given session ID.";
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during logout: {0}", e.getMessage());
            return "Logout failed due to a server error.";
        }
    }
}
