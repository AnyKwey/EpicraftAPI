package eu.epicraft.com.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private static Connection conn;

    public static void connect(String host, int port, String database, String user, String password) {
        if (!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=latin1&autoReconnect=true", user, password);
                System.out.println("[ MySQL ] Connection accepted!");
            } catch (SQLException e) {
                System.out.println("[ MySQL ] Connection Refused!");
                e.printStackTrace();
            }
        }
    }

    public static void disconnect(){
        if (isConnected()){
            try {
                conn.close();
                System.out.println("[ MySQL ] MySQL disconnected!");
            } catch (SQLException e) {
                System.out.println("[ MySQL ] MySQL could not disconnect!");
                e.printStackTrace();
            }
        }
    }

    public static void createTables() {
        try {
            Statement state = conn.createStatement();
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `name` VARCHAR(255)," +
                    " `nickname` VARCHAR(255)," +
                    " `player_rank` INT," +
                    " `gems` INT," +
                    " `credits` INT," +
                    " `lang` INT," +
                    " `address` VARCHAR(255)," +
                    " `playtime` INT," +
                    " `party_play` INT," +
                    " `last_connection` TIMESTAMP," +
                    " `first_connection` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `boost` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `end` BIGINT," +
                    " `when` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `bans` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `end` BIGINT," +
                    " `reason` VARCHAR(255)," +
                    " `author` VARCHAR(255)," +
                    " `banned` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `mutes` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `end` BIGINT," +
                    " `reason` VARCHAR(255)," +
                    " `author` VARCHAR(255)," +
                    " `muted` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `moderation` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `name` VARCHAR(255))");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `record` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uid` VARCHAR(255)," +
                    " `uuid` VARCHAR(255)," +
                    " `recorder` VARCHAR(255)," +
                    " `reason` VARCHAR(255)," +
                    " `when` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `settings` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `player_uuid` VARCHAR(255)," +
                    " `player_name` VARCHAR(255)," +
                    " `youtube` VARCHAR(255)," +
                    " `tiktok` VARCHAR(255)," +
                    " `twitter` VARCHAR(255)," +
                    " `instagram` VARCHAR(255)," +
                    " `announcement_vip` INT," +
                    " `lobby_visibility` INT," +
                    " `lobby_spawn` VARCHAR(255)," +
                    " `friend_notif` INT," +
                    " `mp_private` INT," +
                    " `ask_team` INT," +
                    " `ask_friend` INT," +
                    " `ask_group` INT)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `friends` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `player_uuid` VARCHAR(255)," +
                    " `player_name` VARCHAR(255)," +
                    " `friend_uuid` VARCHAR(255)," +
                    " `friend_name` VARCHAR(255)," +
                    " `when` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `shop` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `object` VARCHAR(255)," +
                    " `bought` TIMESTAMP)");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `stats` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `uuid` VARCHAR(255)," +
                    " `name` VARCHAR(255)," +
                    " `victory` BIGINT," +
                    " `defeat` BIGINT," +
                    " `playtime` BIGINT," +
                    " `kills` BIGINT," +
                    " `deaths` BIGINT," +
                    " `party_play` BIGINT," +
                    " `stars` INT," +
                    " `game` VARCHAR(255))");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `servers` (`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " `name` VARCHAR(255)," +
                    " `status` INT," +
                    " `port` INT," +
                    " `ip` VARCHAR(255))");
        } catch (SQLException e){
            System.out.println("[ MySQL ] tables could not be created");
            e.printStackTrace();
        }
    }

    public static boolean isConnected(){
        try {
            if ((conn == null) || (conn.isClosed()) || conn.isValid(5)){
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("[ MySQL ] MySQL could not stay connected!");
            e.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection() {
        return conn;
    }
}