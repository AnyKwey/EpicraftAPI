package eu.epicraft.com.data.utils;

import eu.epicraft.com.EpicraftAPI;
import eu.epicraft.com.data.tools.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

/**
 * created by AnyKwey
 */
public class TextUtil {

    private static final int CENTER_PX = 154;
    private static final Pattern HEX_PATTERN = Pattern.compile("#<([A-Fa-f0-9]){6}>");

    public static String getCenteredMessage(String message) {
        if (message == null || message.equals("")) return "";

        message = ChatColor.translateAlternateColorCodes('&', message);
        message = message.replace("<center>", "").replace("</center>", "");

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '�') {
                previousCode = true;

            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                } else isBold = false;

            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        return sb.toString() + message;

    }

    public static String setLine(){
        return "§6§m                                        §r";
    }

    public static String setLineRed(){
        return "§c§m                                        §r";
    }

    public static String sendNeedPerm(String requireRank){
        return setLineRed() + setLineRed() +
                "\n" +
                "\n   §c» You do not have the required permission." +
                "\n   §c» Required rank: " + requireRank +
                "\n \n" + setLineRed() + setLineRed();
    }

    public static String sendNeedRankVIP(String requireRank){
        return "§eYou must have the rank " +requireRank+ " which you can purchase on: \n§6> §a" + EpicraftAPI.getInstance().getConfig().getString("message.store");
    }

    public static String stopServer(String serverName){
        return TextUtil.setLineRed() + TextUtil.setLineRed() +
                "§cKicked from " + serverName + " for: §fServer is stopping" +
                TextUtil.setLineRed() + TextUtil.setLineRed();
    }

    public static void falseArgument(Player player, String args){
        player.sendMessage("§cError: The argument §f'" + args + "' §cis not recognized!");
    }
}