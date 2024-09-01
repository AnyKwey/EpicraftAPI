package eu.epicraft.com.manager.moderation;

import eu.epicraft.com.data.mysql.MySQL;
import eu.epicraft.com.data.tools.TimeUnit;
import eu.epicraft.com.data.utils.StringUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by AnyKwey
 */
public class RecordManager {

  public static void addRecorder(UUID uuid, String reason, String recorder) {
    long time = System.currentTimeMillis();
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("INSERT INTO record (uid, uuid, recorder, reason, when) VALUES (?, ?, ?, ?, ?)");
      sts.setString(1, StringUtility.randomCharacters(5));
      sts.setString(2, uuid.toString());
      sts.setString(3, recorder);
      sts.setString(4, reason);
      sts.setTimestamp(5, new Timestamp(time));
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void deleteRecorder(String uid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("DELETE FROM record WHERE uuid=?");
      sts.setString(1, uid);
      sts.executeUpdate();
      sts.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }

  public static String getRecorded(String uid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM record WHERE uuid=?");
      sts.setString(1, uid);
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("uuid");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return "?";
  }

  public static String getReason(String uid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM record WHERE uuid=?");
      sts.setString(1, uid);
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("reason");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String getRecorder(String uid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM record WHERE uuid=?");
      sts.setString(1, uid);
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("recorder");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }

  public static String getWhenRecorded(String uid) {
    try {
      PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM record WHERE uuid=?");
      sts.setString(1, uid);
      ResultSet rs = sts.executeQuery();
      if (rs.next())
        return rs.getString("when");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "?";
  }
}