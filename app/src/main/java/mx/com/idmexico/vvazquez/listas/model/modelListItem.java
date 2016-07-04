package mx.com.idmexico.vvazquez.listas.model;

/**
 * Created by sistemas on 29/06/2016.
 */
public class modelListItem  {

    private int id;
    private int rscId;
    private String appName;
    private String devName;
    private String description;
    private int isInstalled;

    public modelListItem(int id, int rscId, String appName, String devName, String description, int isInstalled)
    {
        this.id = id;
        this.rscId = rscId;
        this.appName = appName;
        this.devName = devName;
        this.description = description;
        this.isInstalled = isInstalled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRscId() {
        return rscId;
    }

    public void setRscId(int rscId) {
        this.rscId = rscId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(int isInstalled) {
        this.isInstalled = isInstalled;
    }
}
