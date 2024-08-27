package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service 
 * Course: CEIS 400 - Software Engineering II 
 * Author: Phillip Tette 
 * Program Description: Abstract class for handling login/logout/registrations. 
 * Date: August 13, 2024
 */
public class LoginService {

    private AuthenticationService authenticationService;

    public LoginService() {
        this.authenticationService = new AuthenticationService();
    }

    public int login(String username, String password) {
        // Assuming authenticationService.login returns a session ID or -1 for failure
        int sessionId = authenticationService.login(username, password);

        if (sessionId != -1) {
            return sessionId; // Successful login, return session ID
        } else {
            return -1; // Indicate login failure
        }
    }

    public String logout(int sessionId) {
        return authenticationService.logout(sessionId);
    }

//    public String register(String lastName, String firstName, 
//            String middleInitial, String phoneNum, String emailAddress, 
//            String empRole, String username, String password) {
//        return authenticationService.register(lastName, firstName, 
//                middleInitial, phoneNum, emailAddress, empRole, username, 
//                password);
//    }
}
