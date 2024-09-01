package eu.epicraft.com.manager.players;

import eu.epicraft.com.data.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class ShopManager {


    public static void add(UUID uuid, String item){
        long time = System.currentTimeMillis();

        try {
            PreparedStatement insert = MySQL.getConnection().prepareStatement("INSERT INTO shop (uuid, object, brought) VALUES (?, ?, ?)");
            insert.setString(1, uuid.toString());
            insert.setString(2, item);
            insert.setTimestamp(3, new Timestamp(time));
            insert.executeUpdate();
            insert.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void remove(UUID uuid, String item){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM shop WHERE object=? and uuid=?");
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                rs.deleteRow();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String getWhenBought(UUID uuid, String item){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT bought FROM shop WHERE object=? and uuid=?");
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return rs.getString("bought");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean have(UUID uuid, String item) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT object FROM shop WHERE uuid=?");
            sts.setString(1, item);
            sts.setString(2, uuid.toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
