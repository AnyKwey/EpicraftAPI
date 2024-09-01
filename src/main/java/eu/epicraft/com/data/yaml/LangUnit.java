package eu.epicraft.com.data.yaml;

import eu.epicraft.com.data.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum LangUnit {

    ENGLISH(0, "English"),
    FRANCAIS(1, "Français"),
    DEUTSCH(2, "Deutsch"),
    ;

    private String name;
    private int power;

    LangUnit(int power, String name) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public static int getPlayerLang(UUID uuid){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT lang FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            int power = 0;

            while (rs.next()){
                power = rs.getInt("lang");
            }
            preparedStatement.close();
            return power;

        } catch (SQLException e){
            System.out.println(" [ MySQL ] " + uuid.toString());
            e.printStackTrace();
        }
        return 0;
    }

    public static void setLang(UUID uuid, int power){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE users SET lang=? WHERE uuid=?");
            preparedStatement.setInt(1, power);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            System.out.println("[ MySQL ] " + uuid.toString() + " > " + power);
            e.printStackTrace();
        }

    }
}