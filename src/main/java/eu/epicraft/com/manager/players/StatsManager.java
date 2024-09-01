package eu.epicraft.com.manager.players;

import eu.epicraft.com.data.mysql.MySQL;
import net.md_5.bungee.BungeeCord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by AnyKwey
 */
public class StatsManager {

  String game;

  public StatsManager(String game){
    this.game = game;
  }

  public void init(UUID uuid) {
    if (isAlreadyExistFromGame(uuid))
      return;

    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("INSERT INTO stats (player_uuid, player_name, victory, defeat, playtime, kills, deaths, party_play, stars, game) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      sts.setString(1, uuid.toString());
      sts.setString(2, BungeeCord.getInstance().getPlayer(uuid).getName());
      sts.setInt(3, 0);
      sts.setLong(4, 0);
      sts.setInt(5, 0);
      sts.setInt(6, 0);
      sts.setInt(7, 0);
      sts.setInt(8, 0);
      sts.setString(9, getGame());
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void remove(UUID uuid) {
    if (!isAlreadyExistFromGame(uuid))
      return; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("DELETE FROM stats WHERE player_uuid=?");
      sts.setString(1, uuid.toString());
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }

  public String getGame() {
    return game;
  }

  public boolean isAlreadyExistFromGame(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT game FROM stats WHERE player_uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      return rs.next();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }

  public static int getVictory(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT victory FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("victory");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public static int getDefeat(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT defeat FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("defeat");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void finishVictorious(UUID uuid){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET victory=? WHERE player_uuid=? and game=?");
      update.setInt(1, getVictory(uuid) + 1);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public int getKills(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT kills FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("kills");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void setKills(UUID uuid, int kills){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET kills=? WHERE player_uuid=? and game=?");
      update.setInt(1, getKills(uuid) + kills);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public int getDeaths(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT deaths FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("deaths");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void setDeaths(UUID uuid, int death){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET deaths=? WHERE player_uuid=? and game=?");
      update.setInt(1, getDeaths(uuid) + death);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public long getPartyPlayed(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT party_play FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("party_play");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void addPartyPlayed(UUID uuid){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET party_play=? WHERE player_uuid=? and game=?");
      update.setLong(1, getPartyPlayed(uuid) + 1);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public int getTime(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT playtime FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("playtime");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void addTime(UUID uuid){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET playtime=? WHERE player_uuid=? and game=?");
      update.setInt(1, getVictory(uuid) + 1);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  private int getStars(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT stars FROM stats WHERE player_uuid=? and game=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getInt("stars");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return '?';
  }

  public void addStars(UUID uuid, int amount){
    try {
      PreparedStatement update = MySQL.getConnection().prepareStatement("UPDATE stats SET stars=? WHERE player_uuid=? and game=?");
      update.setInt(1, getStars(uuid) + amount);
      update.setString(2, uuid.toString());
      update.executeUpdate();
      update.close();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }
}