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
public class ModtagCommand
{
    public ModtagCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.modtag"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (clan.isLeader(player))
                    {
                        if (arg.length == 1)
                        {
                            String newtag = arg[0];
                            String cleantag = Helper.cleanTag(newtag);

                            if (Helper.stripColors(newtag).length() <= plugin.getSettingsManager().getTagMaxLength())
                            {
                                if (!plugin.getSettingsManager().hasDisallowedColor(newtag))
                                {
                                    if (Helper.stripColors(newtag).matches("[0-9a-zA-Z]*"))
                                    {
                                        if (cleantag.equals(clan.getTag()))
                                        {
                                            clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.tagChangedTo, Helper.parseColors(newtag)));
                                            clan.changeClanTag(newtag);
                                            plugin.getClanManager().updateDisplayName(player.getPlayer());
                                        }
                                        else
                                        {
                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.OnlyModifyColorTag);
                                        }
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.TagCanOnlyContainLettersNumbersColorCodes);
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.TagCannotContainFollowingColors, plugin.getSettingsManager().getDisallowedColorString()));
                                }

                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.ClanTagCannotBeLongerCharacters, plugin.getSettingsManager().getTagMaxLength()));
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageModtagTag, plugin.getSettingsManager().getCommandClan()));
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
