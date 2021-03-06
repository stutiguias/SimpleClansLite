package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author phaed
 */
public class LeaderboardCommand
{
    public LeaderboardCommand()
    {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        SimpleClans plugin = SimpleClans.getInstance();
        String headColor = plugin.getSettingsManager().getPageHeadingsColor();
        String subColor = plugin.getSettingsManager().getPageSubTitleColor();
        NumberFormat formatter = new DecimalFormat("#.#");

        if (arg.length == 0)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.leaderboard"))
            {
                List<ClanPlayer> clanPlayers = plugin.getClanManager().getAllClanPlayers();
                plugin.getClanManager().sortClanPlayersByKDR(clanPlayers);

                ChatBlock chatBlock = new ChatBlock();

                ChatBlock.sendBlank(player);
                ChatBlock.saySingle(player, plugin.getSettingsManager().getServerName() + subColor + " " + SimpleClans.langManager.leaderboard + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
                ChatBlock.sendBlank(player);
                ChatBlock.sendMessage(player, headColor + MessageFormat.format(SimpleClans.langManager.totalClanPlayers, subColor + clanPlayers.size()));
                ChatBlock.sendBlank(player);

                chatBlock.setAlignment("c", "l", "c", "c", "c", "c");
                chatBlock.addRow("  " + headColor + SimpleClans.langManager.rank, SimpleClans.langManager.player, SimpleClans.langManager.kdr, SimpleClans.langManager.clan, SimpleClans.langManager.seen);

                int rank = 1;

                for (ClanPlayer cp : clanPlayers)
                {
                    Player p = plugin.getServer().getPlayer(cp.getName());

                    boolean isOnline = false;

                    if (p != null)
                    {
                        isOnline = true;
                    }


                    String name = (cp.isLeader() ? plugin.getSettingsManager().getPageLeaderColor() : ((cp.isTrusted() ? plugin.getSettingsManager().getPageTrustedColor() : plugin.getSettingsManager().getPageUnTrustedColor()))) + cp.getName();
                    String lastSeen = (isOnline ? ChatColor.GREEN + SimpleClans.langManager.online : ChatColor.WHITE + cp.getLastSeenDaysString());

                    String clanTag = ChatColor.WHITE + SimpleClans.langManager.freeAgent;

                    if (cp.getClan() != null)
                    {
                        clanTag = cp.getClan().getColorTag();
                    }

                    chatBlock.addRow("  " + rank, name, ChatColor.YELLOW + "" + formatter.format(cp.getKDR()), ChatColor.WHITE + clanTag, lastSeen);
                    rank++;
                }

                boolean more = chatBlock.sendBlock(player, plugin.getSettingsManager().getPageSize());

                if (more)
                {
                    plugin.getStorageManager().addChatBlock(player, chatBlock);
                    ChatBlock.sendBlank(player);
                    ChatBlock.sendMessage(player, headColor + MessageFormat.format(SimpleClans.langManager.ViewNextPage, plugin.getSettingsManager().getCommandMore()));
                }

                ChatBlock.sendBlank(player);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageLeaderboard, plugin.getSettingsManager().getCommandClan()));
        }
    }
}
