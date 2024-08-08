package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public class LoginService {

    private AuthenticationService authenticationService;

    public LoginService() {
        this.authenticationService = new AuthenticationService();
    }

    public String login(String username, String password) {
        return authenticationService.login(username, password);
    }

    public String logout(int sessionId) {
        return authenticationService.logout(sessionId);
    }

    public String register(String lastName, String firstName, String middleInitial, String phoneNum, String emailAddress, String empRole, String username, String password) {
        return authenticationService.register(lastName, firstName, middleInitial, phoneNum, emailAddress, empRole, username, password);
    }
}
