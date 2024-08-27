package gbmfg_ecs;

import java.util.prefs.Preferences;

public class LoginService {

    private AuthenticationService authenticationService;
    private Preferences preferences;

    public LoginService() {
        this.authenticationService = new AuthenticationService();
        this.preferences = Preferences.userRoot().node(getClass().getName());
    }

    public int login(String username, String password, boolean rememberMe) {
        int sessionId = authenticationService.login(username, password);

        if (sessionId != -1 && rememberMe) {
            saveCredentials(username, password);
        }

        return sessionId;
    }

    public void logout(int sessionId) {
        authenticationService.logout(sessionId);
        clearCredentials();
    }

    private void saveCredentials(String username, String password) {
        preferences.put("username", username);
        preferences.put("password", password);
    }

    private void clearCredentials() {
        preferences.remove("username");
        preferences.remove("password");
    }
}