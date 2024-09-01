package eu.epicraft.com.manager.players;

import eu.epicraft.com.data.mysql.MySQL;
import net.md_5.bungee.api.ProxyServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * created by AnyKwey
 */
public class FriendManager {

    public static void add(UUID playerUUID, UUID targetUUID){
        try {
            long time = System.currentTimeMillis();

            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO friends (player_uuid, player_name, friend_uuid, friend_name, when) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, playerUUID.toString());
            preparedStatement.setString(2, ProxyServer.getInstance().getPlayer(playerUUID).getName());
            preparedStatement.setString(3, targetUUID.toString());
            preparedStatement.setString(4, ProxyServer.getInstance().getPlayer(targetUUID).getName());
            preparedStatement.setInt(5, 0);
            preparedStatement.setTimestamp(6, new Timestamp(time));
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void remove(String mainFriend, String secondFriend){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM friends WHERE player_name=? and friend_name=?");
            preparedStatement.setString(1, mainFriend);
            preparedStatement.setString(2, secondFriend);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<String> getFriendList(String pseudo){
        List<String> friendList = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT friend_name FROM friends WHERE player_name=?");
            preparedStatement.setString(1, pseudo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                friendList.add(rs.getString("friend_name"));
            }
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
            return friendList;
        }
        return friendList;
    }

    public static boolean isFrendsWith(String mainFriend, String secondFriend){
        if(getFriendList(mainFriend).contains(secondFriend)) return true;
        return false;
    }

    public static Integer getFriendCounter(String pseudo){
        Integer counter = 0;

        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT friend_name FROM friends WHERE player_uuid=?");
            preparedStatement.setString(1, pseudo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                counter++;
            }
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
            return counter;
        }
        return counter;
    }
}
