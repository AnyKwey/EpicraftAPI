package eu.epicraft.com.data.yaml;

import eu.epicraft.com.data.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum RankUnit {

    NONE(0, "§7§cAdmin", "§7 ","Joueur", "§7"),
    GRADE1(1, "§9§cAdmin", "§9§lMini-VIP §9", "Mini-VIP", "§9"),
    GRADE2(2, "§e§cAdmin", "§e§lVIP §e", "VIP", "§e"),
    GRADE3(3, "§a§cAdmin", "§a§lEpicVIP §a", "EpicVIP", "§a"),
    YOUTUBER(4, "§6§cAdmin", "§6§lGUEST §6", "Guest", "§6"),
    FRIEND(5, "§d§cAdmin", "§d§lFRIEND §d", "Friend", "§d"),
    STAFF(6, "§e§cAdmin", "§e§lSTAFF §e", "Staff", "§e"),
    HELPER(7, "§3§cAdmin", "§3§lHELPER §3", "Helper", "§3"),
    MOD(8, "§c§cAdmin", "§c§lMOD §c", "Modo", "§c"),
    MANAGER(9, "§9§cAdmin", "§9§lMANAGER §9", "Manager", "§9"),
    ADMIN(10, "§4§4Admin", "§4§lOWNER §4", "Owner", "§4")
    ;

    private String name;
    private String prefix;
    private String orderRank;
    private int power;
    private String color;
    public static Map<Integer, RankUnit> ranks = new HashMap<>();
 
    RankUnit(int power, String orderRank, String prefix, String name, String color) {
        this.name = name;
        this.prefix = prefix;
        this.orderRank = orderRank;
        this.power = power;
        this.color = color;
    }

    static {
        for(RankUnit rank : RankUnit.values()){
            ranks.put(rank.getPower(), rank);
        }
    }

    public static RankUnit powerToRank(int power){
        return ranks.get(power);
    }

    public RankUnit getByName(String name){
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(RankUnit.NONE);
    }
 
    public RankUnit getByPower(int power){
        return Arrays.stream(values()).filter(r -> r.getPower() == power).findAny().orElse(RankUnit.NONE);
    }

    public String getColor() {
        return color;
    }

    public String getOrderRank() {
        return orderRank;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPower() {
        return power;
    }

    public static int getPlayerRank(UUID uuid){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_rank FROM users WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            int power = 0;

            while (rs.next()){
                power = rs.getInt("player_rank");
            }
            preparedStatement.close();
            return power;

        } catch (SQLException e){
            System.out.println(" [ MySQL ] " + uuid.toString());
            e.printStackTrace();
        }
        return 0;
    }

    public static void setRank(UUID uuid, int power){
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE users SET player_rank=? WHERE uuid=?");
            preparedStatement.setInt(1, power);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            System.out.println("[ MySQL ] " + uuid.toString() + " > " + power);
            e.printStackTrace();
        }

    }

    public static int getPRank(UUID uuid){
        return RankUnit.getPlayerRank(uuid);
    }

    public static RankUnit getRank(UUID uuid){
        return RankUnit.powerToRank(RankUnit.getPRank(uuid));
    }
}