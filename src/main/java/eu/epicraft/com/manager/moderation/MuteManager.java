package eu.epicraft.com.manager.moderation;

import eu.epicraft.com.data.mysql.MySQL;
import eu.epicraft.com.data.tools.TimeUnit;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by AnyKwey
 */
public class MuteManager {

  public static void mute(UUID uuid, long endInSeconds, String reason, String muter) {
    if (isMute(uuid))
      return; 
    long endToMillis = endInSeconds * 1000L;
    long end = endToMillis + System.currentTimeMillis();
    if (endInSeconds == -1L)
      end = -1L;
    long time = System.currentTimeMillis();

    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("INSERT INTO mutes (uuid, end, reason, author, muted) VALUES (?, ?, ?, ?, ?)");
      sts.setString(1, uuid.toString());
      sts.setLong(2, end);
      sts.setString(3, reason);
      sts.setString(4, muter);
      sts.setTimestamp(5, new Timestamp(time));
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void unmute(UUID uuid) {
    if (!isMute(uuid))
      return; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("DELETE FROM mutes WHERE uuid=?");
      sts.setString(1, uuid.toString());
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isMute(UUID uuid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM mutes WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      return rs.next();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public static void checkDuration(UUID uuid) {
    if (!isMute(uuid))
      return; 
    if (getEnd(uuid) == -1L)
      return; 
    if (getEnd(uuid) < System.currentTimeMillis())
      unmute(uuid);
  }
  
  public static long getEnd(UUID uuid) {
    if (!isMute(uuid))
      return 0L; 
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM mutes WHERE uuid=?");
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
    if (!isMute(uuid))
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
    if (!isMute(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM mutes WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("reason"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return "?";
  }

  public static String getWhoMuted(UUID uuid) {
    if (!isMute(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM mutes WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("author");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String getWhenMuted(UUID uuid) {
    if (!isMute(uuid))
      return "§cNaN";
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM mutes WHERE uuid=?");
      sts.setString(1, uuid.toString());
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("muted");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String muteMessage(UUID uuid){
    return "\n§cVous avez été réduit au silence\n§cpour : " + getReason(uuid) + "\n";
  }

  public static TextComponent forbideChatMessage(UUID uuid){
    TextComponent m = new TextComponent("§cVous êtes réduit au silence.");
    m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cRaison: §f" + getReason(uuid) + " §cLe: §f" + getWhenMuted(uuid)).create()));
    return m;
  }
}