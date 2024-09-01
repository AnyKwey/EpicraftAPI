package eu.epicraft.com.manager.players;

import eu.epicraft.com.data.mysql.MySQL;
import eu.epicraft.com.data.tools.TimeUnit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by AnyKwey
 */
public class BoosterManager {

  public static void add(UUID uuid, long endInSeconds) {
    if (isBoosted(uuid))
      return; 
    long endToMillis = endInSeconds * 1000L;
    long end = endToMillis + System.currentTimeMillis();
    if (endInSeconds == -1L)
      end = -1L;
    long time = System.currentTimeMillis();

    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("INSERT INTO boost (uuid, end, when) VALUES (?, ?, ?)");
      sts.setString(1, uuid.toString());
      sts.setLong(2, end);
      sts.setTimestamp(5, new Timestamp(time));
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void remove(UUID uuid) {
    if (!isBoosted(uuid))
      return; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("DELETE FROM boost WHERE uuid=?");
      sts.setString(1, uuid.toString());
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isBoosted(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM boost WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      return rs.next();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public static void checkDuration(UUID uuid) {
    if (!isBoosted(uuid))
      return; 
    if (getEnd(uuid) == -1L)
      return; 
    if (getEnd(uuid) < System.currentTimeMillis())
      remove(uuid);
  }
  
  public static long getEnd(UUID uuid) {
    if (!isBoosted(uuid))
      return 0L; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM boost WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getLong("end"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return 0L;
  }
  
  public static String getTimeLeft(UUID uuid) {
    if (!isBoosted(uuid))
      return "§cNaN";
    if (getEnd(uuid) == -1L)
      return "§cPermanent";
    long tempsRestant = (getEnd(uuid) - System.currentTimeMillis()) / 1000L;
    int mois = 0;
    int jours = 0;
    int heures = 0;
    int minutes = 0;
    int secondes = 0;
    while (tempsRestant >= TimeUnit.MOUTH.getToSecond()) {
      mois++;
      tempsRestant -= TimeUnit.MOUTH.getToSecond();
    } 
    while (tempsRestant >= TimeUnit.DAY.getToSecond()) {
      jours++;
      tempsRestant -= TimeUnit.DAY.getToSecond();
    } 
    while (tempsRestant >= TimeUnit.HOUR.getToSecond()) {
      heures++;
      tempsRestant -= TimeUnit.HOUR.getToSecond();
    } 
    while (tempsRestant >= TimeUnit.MINUTE.getToSecond()) {
      minutes++;
      tempsRestant -= TimeUnit.MINUTE.getToSecond();
    } 
    while (tempsRestant >= TimeUnit.SECOND.getToSecond()) {
      secondes++;
      tempsRestant -= TimeUnit.SECOND.getToSecond();
    } 
    return mois + " " + TimeUnit.MOUTH.getName() + ", " + jours + " " + TimeUnit.DAY.getName() + ", " + heures + " " + TimeUnit.HOUR.getName() + ", " + minutes + " " + TimeUnit.MINUTE.getName() + ", " + secondes + " " + TimeUnit.SECOND.getName();
  }

  public static String getWhen(UUID uuid) {
    if (!isBoosted(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM boost WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("when");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }
}