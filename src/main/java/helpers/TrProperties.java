package helpers;

public class TrProperties {
    private static final TrProperties TR_PROPERTIES = new TrProperties();
    private String userName;
    private String password;
    private String inProgressStatus;
    private String doneStatus;
    private String baseUrl;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getInProgressStatus() {
        return inProgressStatus;
    }

    public String getDoneStatus() {
        return doneStatus;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    static {
        initProperties();
    }

    private TrProperties() {
    }

    private static void initProperties() {
        TR_PROPERTIES.userName = PropertiesUtil.get("tr.email");
        TR_PROPERTIES.password = PropertiesUtil.get("tr.password");
        TR_PROPERTIES.inProgressStatus = PropertiesUtil.get("tr.status.inProgress");
        TR_PROPERTIES.doneStatus = PropertiesUtil.get("tr.status.Done");
        TR_PROPERTIES.baseUrl = PropertiesUtil.get("tr.baseurl");
    }

    public static TrProperties getProperties() {
        return TR_PROPERTIES;
    }

}
