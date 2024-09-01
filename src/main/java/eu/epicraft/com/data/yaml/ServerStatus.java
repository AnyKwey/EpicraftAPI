package eu.epicraft.com.data.yaml;

public enum ServerStatus {

    ONLINE(1, "§aEn ligne"),
    OFFLINE(0, "§cHors-ligne"),
    MAINTENANCE(2, "§cMaintenance");

    int status;
    String name;

    ServerStatus(int status, String name){
        this.status = status;
        this.name = name;
    }
}
