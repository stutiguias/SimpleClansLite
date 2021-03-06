package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @author phaed
 */
public class MenuCommand
{
    private final List<String> menuItems = new LinkedList<>();

    public MenuCommand()
    {
    }

    /**
     * Execute the command
     *
     * @param player
     */
    public void execute(Player player)
    {
        SimpleClans plugin = SimpleClans.getInstance();

        String headColor = plugin.getSettingsManager().getPageHeadingsColor();
        String subColor = plugin.getSettingsManager().getPageSubTitleColor();

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);
        Clan clan = cp == null ? null : cp.getClan();

        boolean isLeader = cp != null && cp.isLeader();
        boolean isTrusted = cp != null && cp.isTrusted();

        String clanCommand = plugin.getSettingsManager().getCommandClan();

        ChatBlock chatBlock = new ChatBlock();

        if (clan == null && plugin.getPermissionsManager().has(player, "simpleclans.leader.create"))
        {
            if (plugin.getSettingsManager().isePurchaseCreation())
            {
                chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.createPurchase, clanCommand, ChatColor.WHITE));
            }
            else
            {
                chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.createCreate, clanCommand, ChatColor.WHITE));
            }
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.list"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpList, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.profile"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpProfileView, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.profile"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpProfileTag, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.lookup"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpLookup, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.lookup"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpLookupPlayer, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.leaderboard"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpLeaderboardView, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.alliances"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpAlliances, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.rivalries"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpRivalries, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.roster"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpRoster, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.roster"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpRosterTag, clanCommand, ChatColor.WHITE));
        }
        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.stats"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpStats, clanCommand, ChatColor.WHITE));
        }
        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.kills"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpKills, clanCommand, ChatColor.WHITE));
        }
        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.kills"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpKillsplayer, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.ally"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpAllyAddRemove, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.rival"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpRivalAddRemove, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.home"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpHomeMenu, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.home-set"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpHomeSetMenu, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.war"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpWar, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.bb"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpBb, clanCommand, ChatColor.WHITE));
        }
        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.bb-add"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpBbMsg, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.modtag"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpModtagTag, clanCommand, ChatColor.WHITE));
        }

        String toggles = "";

        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.bb-toggle"))
        {
            toggles += "bb/";
        }

        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.member.tag-toggle"))
        {
            toggles += "tag/";
        }

        if (!toggles.isEmpty())
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpToggle, clanCommand, ChatColor.WHITE, Helper.stripTrailing(toggles, "/")));
        }

        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.invite"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpInvitePlayer, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.kick"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpKickPlayer, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.setrank"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpSetrank, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.settrust"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpTrust, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.settrust"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpUntrust, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.promote"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpPromoteMember, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.demote"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpDemoteLeader, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.ff"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpClanff, clanCommand, ChatColor.WHITE));
        }
        if (isLeader && plugin.getPermissionsManager().has(player, "simpleclans.leader.disband"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpDisband, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.ff"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpFf, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.member.resign"))
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + MessageFormat.format(SimpleClans.langManager.helpResign, clanCommand, ChatColor.WHITE));
        }

        for (String item : menuItems)
        {
            chatBlock.addRow(ChatColor.AQUA + "  " + item);
        }

        if (isTrusted && plugin.getPermissionsManager().has(player, "simpleclans.mod.mostkilled"))
        {
            chatBlock.addRow(ChatColor.DARK_RED + "  " + MessageFormat.format(SimpleClans.langManager.helpMostkilled, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.mod.disband"))
        {
            chatBlock.addRow(ChatColor.DARK_RED + "  " + MessageFormat.format(SimpleClans.langManager.helpDisbandAdm, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.mod.globalff"))
        {
            chatBlock.addRow(ChatColor.DARK_RED + "  " + MessageFormat.format(SimpleClans.langManager.helpGlobalff, clanCommand, ChatColor.WHITE));
        }
        if (plugin.getPermissionsManager().has(player, "simpleclans.admin.reload"))
        {
            chatBlock.addRow(ChatColor.DARK_RED + "  " + MessageFormat.format(SimpleClans.langManager.helpReload, clanCommand, ChatColor.WHITE));
        }
        if (chatBlock.isEmpty())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            return;
        }

        ChatBlock.sendBlank(player);
        ChatBlock.saySingle(player, plugin.getSettingsManager().getServerName() + subColor + " " + SimpleClans.langManager.clanCommands + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
        ChatBlock.sendBlank(player);

        boolean more = chatBlock.sendBlock(player, plugin.getSettingsManager().getPageSize());

        if (more)
        {
            plugin.getStorageManager().addChatBlock(player, chatBlock);
            ChatBlock.sendBlank(player);
            ChatBlock.sendMessage(player, headColor + MessageFormat.format(SimpleClans.langManager.ViewNextPage, plugin.getSettingsManager().getCommandMore()));
        }

        ChatBlock.sendBlank(player);
    }

    /**
     * Execute the command
     *
     * @param sender
     */
    public void executeSender(CommandSender sender)
    {
        SimpleClans plugin = SimpleClans.getInstance();

        String headColor = plugin.getSettingsManager().getPageHeadingsColor();
        String subColor = plugin.getSettingsManager().getPageSubTitleColor();

        String clanCommand = plugin.getSettingsManager().getCommandClan();

        ChatBlock chatBlock = new ChatBlock();

        chatBlock.addRow(ChatColor.DARK_RED + "  " + MessageFormat.format(SimpleClans.langManager.helpReload, clanCommand, ChatColor.WHITE));

        ChatBlock.sendBlank(sender);
        ChatBlock.saySingle(sender, plugin.getSettingsManager().getServerName() + subColor + " " + SimpleClans.langManager.clanCommands + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
        ChatBlock.sendBlank(sender);
        chatBlock.sendBlock(sender, plugin.getSettingsManager().getPageSize());
        ChatBlock.sendBlank(sender);
    }

    /**
     * Adds a menu item to the /clan menu
     *
     * @param syntax
     * @param description
     */
    public void addMenuItem(String syntax, String description)
    {
        addMenuItem(syntax, description, ChatColor.AQUA);
    }

    /**
     * Adds a menu item to the /clan menu, specifying syntax color
     * [color] /[syntax] - [description]
     *
     * @param syntax
     * @param description
     * @param color
     */
    public void addMenuItem(String syntax, String description, ChatColor color)
    {
        menuItems.add(color + "/" + syntax + ChatColor.WHITE + " - " + description);
    }
}
