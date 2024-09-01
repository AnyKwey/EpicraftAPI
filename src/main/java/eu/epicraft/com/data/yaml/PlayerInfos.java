package eu.epicraft.com.data.yaml;

import eu.epicraft.com.data.mysql.MySQL;
import eu.epicraft.com.data.tools.TimeUnit;
import net.md_5.bungee.BungeeCord;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by AnyKwey
 */
public class PlayerInfos {

    public static void update(UUID uuid) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT name FROM users WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE users SET name=? WHERE uuid=?");
                update.setString(1, BungeeCord.getInstance().getPlayer(uuid).getName());
                update.setString(2, uuid.toString());
                update.executeUpdate();
                update.close();
            } else {
                long time = System.currentTimeMillis();

                PreparedStatement insert = MySQL.getConnection().prepareStatement("INSERT INTO users (uuid, name, nickname, player_rank, gems, credits, lang, address, playtime, party_play, last_connection, first_connection) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, BungeeCord.getInstance().getPlayer(uuid).getName());
                insert.setString(3, BungeeCord.getInstance().getPlayer(uuid).getName());
                insert.setInt(4, 0);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.setString(8, BungeeCord.getInstance().getPlayer(uuid).getAddress().getHostName());
                insert.setInt(9, 0);
                insert.setInt(10, 0);
                insert.setTimestamp(11, new Timestamp(time));
                insert.setTimestamp(12, new Timestamp(time));
                insert.executeUpdate();
                insert.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exist(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UUID getUUID(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT uuid FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return UUID.fromString(rs.getString("uuid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPseudo(UUID uuid) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT name FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String pseudo = "";

            while (rs.next()) {
                pseudo = rs.getString("name");
            }
            preparedStatement.close();
            return pseudo;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "?";
    }

    public static String getNickName(UUID uuid){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT nick FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String pseudo = "";

            while (rs.next()){
                pseudo = rs.getString("nick");
            }
            preparedStatement.close();
            return pseudo;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return "?";
    }
    public static String getFirstConnection(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT first_connection FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getString("first_connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "?";
    }

    public static String getLastConnection(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT last_connection FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getString("last_connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "?";
    }

    public static void updateLastConnection(String playerName){
        long time = System.currentTimeMillis();

        try {
            PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE users SET last_connection=? WHERE name=?");
            update.setTimestamp(1, new Timestamp(time));
            update.setString(2, playerName);
            update.executeUpdate();
            update.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static int methodToIgnoreIsJustHereToGetTimeTotalTo(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("playtime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

        public static String getTimeTotal(String playerName) {
            long tempsRestant = (methodToIgnoreIsJustHereToGetTimeTotalTo(playerName));
            int heures = 0;
            int minutes = 0;
            while (tempsRestant >= 3600L) {
                heures++;
                tempsRestant -= 3600L;
            }
            while (tempsRestant >= TimeUnit.MINUTE.getToSecond()) {
                minutes++;
                tempsRestant -= TimeUnit.MINUTE.getToSecond();
            }
            return heures + " " + TimeUnit.HOUR.getName() + " " + minutes + " " + TimeUnit.MINUTE.getName();
        }

    public static String getTotalTimePrecisely(String playerName) {
        long tempsRestant = (methodToIgnoreIsJustHereToGetTimeTotalTo(playerName));
        int mois = 0;
        int jours = 0;
        int heures = 0;
        int minutes = 0;
        while (tempsRestant >= TimeUnit.MOUTH.getToSecond()) {
            mois++;
            tempsRestant -= TimeUnit.MOUTH.getToSecond();
        }
        while (tempsRestant >= TimeUnit.MOUTH.getToSecond()) {
            jours++;
            tempsRestant -= TimeUnit.MOUTH.getToSecond();
        }
        while (tempsRestant >= TimeUnit.HOUR.getToSecond()) {
            heures++;
            tempsRestant -= TimeUnit.HOUR.getToSecond();
        }
        while (tempsRestant >= TimeUnit.MINUTE.getToSecond()) {
            minutes++;
            tempsRestant -= TimeUnit.MINUTE.getToSecond();
        }
        return mois + " " + TimeUnit.MOUTH.getName() + ", " + jours + " " + TimeUnit.DAY.getName() + ", " + heures + " " + TimeUnit.HOUR.getName() + ", " + minutes + " " + TimeUnit.MINUTE.getName();
    }

    public static void updatePlayTime(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE users SET playtime=" + (methodToIgnoreIsJustHereToGetTimeTotalTo(playerName) + 1) + " WHERE name=?");
            sts.setString(1, playerName);
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePartyPlay(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE users SET party_play=" + (methodToIgnoreIsJustHereToGetTimeTotalTo(playerName) + 1) + " WHERE name=?");
            sts.setString(1, playerName);
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getPartyPlayed(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("party_play");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return '?';
    }

    public static int getLang(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("lang");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setLang(String playerName, int amount) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE users SET lang=? WHERE name=?");
            sts.setInt(1, amount);
            sts.setString(2, playerName);
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getGemes(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("gems");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return '?';
    }

    public static void addGems(String playerName, int amount) {
        setGems(playerName, getGemes(playerName) + amount);
    }

    public static void removeGems(String playerName, int amount) {
        setGems(playerName, getGemes(playerName) - amount);
    }

    public static void setGems(String playerName, int amount) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE users SET gems=? WHERE name=?");
            sts.setInt(1, amount);
            sts.setString(2, playerName);
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCredits(String playerName) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            if (rs.next())
                return rs.getInt("credits");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return '?';
    }

    public static void addCredits(String playerName, int amount) {
        setCredits(playerName, getCredits(playerName) + amount);
    }

    public static void removeCredits(String playerName, int amount) {
        setCredits(playerName, getCredits(playerName) - amount);
    }

    public static void setCredits(String playerName, int amount) {
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE users SET credits=? WHERE name=?");
            sts.setInt(1, amount);
            sts.setString(2, playerName);
            sts.executeUpdate();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getTotalPlayers(UUID uuid) {
        List<Integer> ids = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("`#`"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static List<String> getTotalPlayersName(UUID uuid) {
        List<String> ids = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT name FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ids.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public Player getRandomPlayer() {
        List<Player> tempPlayers = (List<Player>) Bukkit.getServer().getOnlinePlayers();

        Collections.shuffle(tempPlayers);

        Player RandomPlayer = tempPlayers.get(0);

        tempPlayers = null;
        return RandomPlayer;
    }
}
