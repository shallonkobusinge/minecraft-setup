package edu.caltech.cs01;

import utils.PlayerResponseUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PlaceMinecraftObjects extends JavaPlugin implements CommandExecutor, Listener {
    private PlayerResponseUtil responseUtil;
   @Override
    public void onEnable(){
       getServer().getPluginManager().registerEvents(this, this);
       this.getCommand("place").setExecutor(this);
   }

   @Override
    public void onDisable(){
       getLogger().info("Place Minecraft Objects plugin disabled.");
   }
   @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if (!(sender instanceof Player)) {
           sender.sendMessage(ChatColor.RED + "Only players can execute this command");
           return true;
       }

       Player player = (Player) sender;
       if (command.getName().equalsIgnoreCase("place")) {
           if (args.length == 0) {
               player.sendMessage("Usage: /place <object_type>");
               return true;
           }
           try {
               Material blockType = Material.valueOf(args[0].toUpperCase());
               if (!blockType.isBlock()) {
                   player.sendMessage("Invalid block type! Please use valid block types");
                   return false;
               }
               Location location = player.getLocation();
               Block block = location.getBlock();
               block.setType(blockType);
               player.sendMessage(
                       ChatColor.GREEN + "Placed " + blockType.toString());
           }  catch (Exception e) {
               return responseUtil.sendMessage(player, e.getMessage(), ChatColor.RED);
           }
       }
           return false;


   }
}