package eu.epicraft.com.manager.moderation;

import eu.epicraft.com.data.yaml.RankUnit;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnyKwey
 */
public class StaffManager {

    private static List<String> staffs = new ArrayList<>();

    public static List<String> getStaffs() {
        return staffs;
    }

    public static void addToStaffList(ProxiedPlayer player){
        getStaffs().add(RankUnit.getRank(player.getUniqueId()).getPrefix() + player.getName());
    }

    public static void removeToStaffList(ProxiedPlayer player){
        getStaffs().remove(player);
    }

    public static boolean IsInStaffList(ProxiedPlayer player){
        return getStaffs().contains(player);
    }
}
