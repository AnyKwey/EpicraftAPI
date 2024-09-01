/**package eu.epicraft.com.manager.players;

import com.mojang.authlib.GameProfile;
import eu.epicraft.com.data.mysql.MySQL;
import eu.epicraft.com.data.yaml.PlayerInfos;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class NickManager {

    public static void setNickPlayer(UUID uuid) {
        try {
            PacketPlayOutPlayerInfo playerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, ((CraftPlayer) Bukkit.getPlayer(uuid)).getHandle());
            if(PlayerInfos.exist(randomPseudo())){
                Bukkit.getPlayer(uuid).sendMessage("§cErreur: Le pseudo choisis par notre bot appartient déjà à un autre joueur.");
                return;
            }

            Bukkit.getPlayer(uuid).setDisplayName(randomPseudo());
            for (Player players : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(playerInfo);
            }
            GameProfile profile = ((CraftPlayer) Bukkit.getPlayer(uuid)).getProfile();
            Field nameField = profile.getClass().getDeclaredField("name");
            nameField.setAccessible(true);
            int modifiers = nameField.getModifiers();
            Field modifiersField = nameField.getClass().getDeclaredField("modifiers");
            modifiers = modifiers & Modifier.FINAL;
            modifiersField.setAccessible(true);
            modifiersField.setInt(nameField, modifiers);
            nameField.set(profile, Bukkit.getPlayer(uuid).getDisplayName());

            updateNickName(uuid, Bukkit.getPlayer(uuid).getDisplayName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetNickName(UUID uuid) {
        try {
            PacketPlayOutPlayerInfo playerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, ((CraftPlayer)
                    Bukkit.getPlayer(uuid)).getHandle());
            Bukkit.getPlayer(uuid).setDisplayName(getPseudo(uuid));
            for (Player players : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(playerInfo);
            }
            GameProfile profile = ((CraftPlayer) Bukkit.getPlayer(uuid)).getProfile();
            Field nameField = profile.getClass().getDeclaredField("name");
            nameField.setAccessible(true);
            int modifiers = nameField.getModifiers();
            Field modifiersField = nameField.getClass().getDeclaredField("modifiers");
            modifiers = modifiers & Modifier.FINAL;
            modifiersField.setAccessible(true);
            modifiersField.setInt(nameField, modifiers);
            nameField.set(profile, getPseudo(uuid));

            updateNickName(uuid, getPseudo(uuid));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPseudo(UUID uuid) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_name FROM users WHERE player_uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String pseudo = "";

            while (rs.next()) {
                pseudo = rs.getString("player_name");
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_nick FROM users WHERE player_uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String pseudo = "";

            while (rs.next()){
                pseudo = rs.getString("player_nick");
            }
            preparedStatement.close();
            return pseudo;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return "?";
    }

    public static void updateNickName(UUID uuid, String nickName){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE users SET player_nick=? WHERE player_uuid=?");
            preparedStatement.setString(1, nickName);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean isNickName(UUID uuid){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_nick FROM users WHERE player_uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            String nickName = "";
            boolean isNick = false;

            while (rs.next()){
                nickName = rs.getString("player_nick");
                isNick = !PlayerInfos.exist(nickName);
            }
            preparedStatement.close();
            return isNick;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    static String randomPseudo(){
        int r = (int) (Math.random()*5);
        String name = new String [] {"hcj","9Queues","TryhardWsh","Alta", "Numzzi_", "Alcatin", "Gaming2013", "BillyBoyz", "Palkanta", "S69___", "4_9_tp", "livaydaddy", "MegaPerm", "Altromaz", "Neousium", "ValtoSky", "Noumleot", "456421848", "321vivaFR", "OverHype", "4_7_12", "BestWWer", "Willdaddy", "Mahdame", "MamadouDuBled", "Tromate"}[r];
        return name;
    }
}**/