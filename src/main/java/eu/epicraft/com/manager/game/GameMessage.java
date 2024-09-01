package eu.epicraft.com.manager.game;

import eu.epicraft.com.EpicraftAPI;
import eu.epicraft.com.data.utils.TextUtil;
import eu.epicraft.com.data.yaml.PlayerInfos;
import eu.epicraft.com.data.yaml.RankUnit;
import eu.epicraft.com.manager.players.BoosterManager;
import eu.epicraft.com.manager.players.StatsManager;
import org.bukkit.entity.Player;

public enum GameMessage {

    NAMESERVER_PREFIX("§6[Epicraft] "),
    START("§6> §eDébut de la partie, bonne chance !"),
    PLAYER_OFFLINE("§cErreur: Ce joueur est hors-ligne."),
    PLAYER_OFFLINE_ONTHISSERVER("§cErreur: Ce joueur n'est pas connecté sur ce serveur."),
    PLAYER_NEVER_CONNECT("§cErreur: Ce joueur ne s'est jamais connecté."),
    STORE(EpicraftAPI.getInstance().getConfig().getString("message.store")),
    WEBSITE(EpicraftAPI.getInstance().getConfig().getString("message.website")),
    DISCORD(EpicraftAPI.getInstance().getConfig().getString("message.discord")),
    TWITTER(EpicraftAPI.getInstance().getConfig().getString("message.twitter"))
    ;

    String message;

    GameMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void falseArgument(Player player, String args){
        player.sendMessage("§cErreur: L'argument '§f"+args+"§c' est inconnu.");
    }
    public static void rewards(Player player, String gameName, int gems, int points, boolean win) {
        int none = (int) (gems * 0.15);
        int grade1 = (int) (gems * 0.35);
        int grade2 = (int) (gems * 0.55);
        int grade3 = (int) (gems * 0.70);
        int boost = (int) (gems * 0.50);

        if (win) {
            player.sendMessage("§6✛§m             §6 V I C T O I R E §6§m                     §6✛§r");
        } else if (!win){
            player.sendMessage("§6✛§m              §c D É F A I T E §6§m                      §6✛§r");
        }

        player.sendMessage(" ");
        player.sendMessage(TextUtil.getCenteredMessage("§f&lLet's go! §b&l+" + points + " points"));
        new StatsManager(gameName).addStars(player.getUniqueId(), points);
        player.sendMessage(" ");
        if (RankUnit.getRank(player.getUniqueId()).getPower() == 0) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + none + " §7(+15%)");
            PlayerInfos.addGems(player.getName(), gems + none);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() == 1) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade1 + " §7(+35%)");
            PlayerInfos.addGems(player.getName(), gems + grade1);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() == 2) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade2 + " §7(+55%)");
            PlayerInfos.addGems(player.getName(), gems + grade2);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() >= 3) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade3 + " §7(+70%)");
            PlayerInfos.addGems(player.getName(), gems + boost);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() == 0 && BoosterManager.isBoosted(player.getUniqueId())) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade3 + " §7(+65%)");
            PlayerInfos.addGems(player.getName(), gems + none + boost);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() == 1 && BoosterManager.isBoosted(player.getUniqueId())) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade3 + " §7(+85%)");
            PlayerInfos.addGems(player.getName(), gems + grade1 + boost);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() == 2 && BoosterManager.isBoosted(player.getUniqueId())) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade3 + " §7(+105%)");
            PlayerInfos.addGems(player.getName(), gems + grade2 + boost);
        } else if (RankUnit.getRank(player.getUniqueId()).getPower() >= 3 && BoosterManager.isBoosted(player.getUniqueId())) {
            player.sendMessage(" §8■ §fGemmes gagnée: §a" + gems + " §8■ §fGemmes bonus: §c" + grade3 + " §7(+120%)");
            PlayerInfos.addGems(player.getName(), gems + grade3 + boost);
        }
        player.sendMessage("§6✛§m                                          §6✛§r");
    }
}
