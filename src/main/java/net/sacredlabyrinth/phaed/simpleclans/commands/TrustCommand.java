package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 *
 * @author phaed
 */
public class TrustCommand
{
    public TrustCommand()
    {
    }

    /**
     * Execute the command
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        SimpleClans plugin = SimpleClans.getInstance();

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.settrust"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                if (clan.isLeader(player))
                {
                    if (arg.length == 1)
                    {
                        String trusted = arg[0];

                        if (trusted != null)
                        {
                            if (!trusted.equals(player.getName()))
                            {
                                if (clan.isMember(trusted))
                                {
                                    if (!clan.isLeader(trusted))
                                    {
                                        ClanPlayer tcp = plugin.getClanManager().getCreateClanPlayer(trusted);

                                        if (!tcp.isTrusted())
                                        {
                                            clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasBeenGivenTrustedStatusBy, Helper.capitalize(trusted), player.getName()));
                                            tcp.setTrusted(true);
                                            plugin.getStorageManager().updateClanPlayer(tcp);
                                        }
                                        else
                                        {
                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.PlayerAlreadyTrusted);
                                        }
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.leadersAreAlreadyTrusted);
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.PlayerNotMemberOfYourClan);
                                }
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCannotTrustYourself);
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoPlayerMatched);
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageTrustPlayer, plugin.getSettingsManager().getCommandClan()));
                    }
                }
                else
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
        }
    }
}
