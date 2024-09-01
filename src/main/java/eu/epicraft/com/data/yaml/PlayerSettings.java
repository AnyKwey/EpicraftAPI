package eu.epicraft.com.data.yaml;

import eu.epicraft.com.data.mysql.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by AnyKwey
 */

public class PlayerSettings {

    public static void updateSettings(ProxiedPlayer player) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT player_name FROM settings WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE settings SET player_name=? WHERE player_uuid=?");
                update.setString(1, player.getName());
                update.setString(2, player.getUniqueId().toString());
                update.executeUpdate();
                update.close();
            } else {
                PreparedStatement insert = MySQL.getConnection().prepareStatement("INSERT INTO settings (player_uuid, player_name, youtube, tiktok, twitter, instagram, announcement_vip, lobby,visibility, lobby_spawn, friend_notif, mp_private, ask_team, ask_friend, ask_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setString(1, player.getUniqueId().toString());
                insert.setString(2, player.getName());
                insert.setString(3, "§cAucun");
                insert.setString(4, "§cAucun");
                insert.setString(5, "§cAucun");
                insert.setString(6, "§cAucun");
                insert.setInt(7, 0);
                insert.setInt(8, 0);
                insert.setString(9, "0 22 0");
                insert.setInt(10, 0);
                insert.setInt(11, 0);
                insert.setInt(12, 0);
                insert.setInt(13, 0);
                insert.setInt(14, 0);
                insert.executeUpdate();
                insert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getStatusOf(UUID uuid, String setting){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT " + setting + " FROM settings WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt(setting);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *      * announcement_vip = ...
     *      *
     *      * lobby_visibility =
     *      * 0 - show
     *      * 1 - hide
     *      *
     *      * friend_notif =
     *      * 0 - receive
     *      * 1 - not receive
     *      *
     *      * mp_private =
     *      * 0 - receive
     *      * 1 - receive only friend
     *      * 2 - not receive
     *      *
     *      * ask_team =
     *      * 0 - accept
     *      * 1 - not accept
     *      *
     *      * ask_friend =
     *      * 0 - accept
     *      * 1 - not accept
     *      *
     *      * ask_group =
     *      * 0 - accept
     *      * 1 - not accept
     *      *
     * @param uuid
     * @param setting
     * @param status
     */
    public static void setStatusOf(UUID uuid, String setting, int status){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE settings SET " + setting + "=? WHERE player_uuid=?");
            sts.setInt(1, status);
            sts.setString(2, uuid.toString());
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getStringOf(UUID uuid, String setting){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT " + setting + " FROM settings WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getString(setting);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "§cNaN";
    }

    public static void setStrinOf(UUID uuid, String setting, String string){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE settings SET " + setting + "=? WHERE player_uuid=?");
            sts.setString(1, string);
            sts.setString(2, uuid.toString());
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}