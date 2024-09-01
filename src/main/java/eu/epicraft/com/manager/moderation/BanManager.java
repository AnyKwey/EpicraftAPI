package eu.epicraft.com.manager.moderation;

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
public class BanManager {

  public static void ban(UUID uuid, long endInSeconds, String reason, String banner) {
    if (isBanned(uuid))
      return; 
    long endToMillis = endInSeconds * 1000L;
    long end = endToMillis + System.currentTimeMillis();
    if (endInSeconds == -1L)
      end = -1L;
    long time = System.currentTimeMillis();

    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("INSERT INTO bans (uuid, end, reason, author, banned) VALUES (?, ?, ?, ?, ?)");
      sts.setString(1, uuid.toString());
      sts.setLong(2, end);
      sts.setString(3, reason);
      sts.setString(4, banner);
      sts.setTimestamp(5, new Timestamp(time));
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void unban(UUID uuid) {
    if (!isBanned(uuid))
      return; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("DELETE FROM bans WHERE uuid=?");
      sts.setString(1, uuid.toString());
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isBanned(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      return rs.next();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public static void checkDuration(UUID uuid) {
    if (!isBanned(uuid))
      return; 
    if (getEnd(uuid) == -1L)
      return; 
    if (getEnd(uuid) < System.currentTimeMillis())
      unban(uuid); 
  }
  
  public static long getEnd(UUID uuid) {
    if (!isBanned(uuid))
      return 0L; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
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
    if (!isBanned(uuid))
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

  public static String getReason(UUID uuid) {
    if (!isBanned(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("reason"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return "?";
  }

  public static String getWhoBanned(UUID uuid) {
    if (!isBanned(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("author");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String getWhenBanned(UUID uuid) {
    if (!isBanned(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("banned");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String banMessage(UUID uuid){
    return "§6§lEPICRAFT \n\n§4⚠ §fVotre compte est suspendu temporairement §4⚠\n§fpour §b" + getReason(uuid) +
            "\n\n§fExpire dans §a" + getTimeLeft(uuid) +
            "\n\n§7S'il s'agît d'une erreur, créer un ticket sur §9discord.gg/epicraft\n§8Epicraft Mod-H : AF - " + getWhenBanned(uuid) + " " + uuid.toString();
  }

  public static String banPermMessage(UUID uuid){
    return "§6§lEPICRAFT \n\n§4⚠ §fVotre compte est suspendu pour une durée permanent §4⚠\n§fpour §b" + getReason(uuid) +
            "\n\n§7S'il s'agît d'une erreur, créer un ticket sur §9discord.gg/epicraft\n§8Epicraft Mod-H : AF - " + getWhenBanned(uuid) + " " + uuid.toString();
  }
}