package eu.epicraft.com.data.yaml;

import eu.epicraft.com.data.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerCommon {

    private String displayName;

    public ServerCommon(String displayName){
        this.displayName = displayName;
    }

    public int getStatus() {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM servers WHERE name=?");
            sts.setString(1, displayName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("status");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPort() {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM servers WHERE name=?");
            sts.setString(1, displayName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("status");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getAddress() {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM servers WHERE name=?");
            sts.setString(1, displayName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getString("ip");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }
}
