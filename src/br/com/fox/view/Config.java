package br.com.fox.view;

/**
 *
 * @author Dvr
 */
public class Config {
    private String pathRouterSnapshot;
    private String pathDestSnapshot;
    private String smtpHostEmail;
    private int smtpPortEmail;
    private String userEmail;
    private String passEmail;
    private String hostFTP;
    private int portaFTP;
    private String userFTP;
    private String passFTP;
    private String pathDestFTP;

    public Config() {
    }

    public String getHostFTP() {
        return hostFTP;
    }

    public void setHostFTP(String hostFTP) {
        this.hostFTP = hostFTP;
    }

    public String getPassEmail() {
        return passEmail;
    }

    public void setPassEmail(String passEmail) {
        this.passEmail = passEmail;
    }

    public String getPassFTP() {
        return passFTP;
    }

    public void setPassFTP(String passFTP) {
        this.passFTP = passFTP;
    }

    public String getPathDestFTP() {
        return pathDestFTP;
    }

    public void setPathDestFTP(String pathDestFTP) {
        this.pathDestFTP = pathDestFTP;
    }

    public String getPathDestSnapshot() {
        return pathDestSnapshot;
    }

    public void setPathDestSnapshot(String pathDestSnapshot) {
        this.pathDestSnapshot = pathDestSnapshot;
    }

    public String getPathRouterSnapshot() {
        return pathRouterSnapshot;
    }

    public void setPathRouterSnapshot(String pathRouterSnapshot) {
        this.pathRouterSnapshot = pathRouterSnapshot;
    }

    public int getPortaFTP() {
        return portaFTP;
    }

    public void setPortaFTP(int portaFTP) {
        this.portaFTP = portaFTP;
    }

    public String getSmtpHostEmail() {
        return smtpHostEmail;
    }

    public void setSmtpHostEmail(String smtpHostEmail) {
        this.smtpHostEmail = smtpHostEmail;
    }

    public int getSmtpPortEmail() {
        return smtpPortEmail;
    }

    public void setSmtpPortEmail(int smtpPortEmail) {
        this.smtpPortEmail = smtpPortEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFTP() {
        return userFTP;
    }

    public void setUserFTP(String userFTP) {
        this.userFTP = userFTP;
    }

    @Override
    public String toString() {
        return "Config{" + "pathRouterSnapshot=" + pathRouterSnapshot + ", pathDestSnapshot=" + pathDestSnapshot + ", smtpHostEmail=" + smtpHostEmail + ", smtpPortEmail=" + smtpPortEmail + ", userEmail=" + userEmail + ", passEmail=" + passEmail + ", hostFTP=" + hostFTP + ", portaFTP=" + portaFTP + ", userFTP=" + userFTP + ", passFTP=" + passFTP + ", pathDestFTP=" + pathDestFTP + '}';
    }
    
}
