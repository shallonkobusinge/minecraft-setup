package utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collection;


public record PlayerResponseUtil() {

    /**
     * Sends a message to the player, supports single messages or a collection of messages like Lists
     * @param sender the command sender to send the messages to either Player or CommandSender.
     * @param messages message(s) to send.
     * @param color the color of the messages; defaults to RESET if null
     * @return true if message(s) were sent in GREEN color, false otherwise.
     * */
    public <T> boolean sendMessage(CommandSender sender, T messages, ChatColor color){
        boolean isGreen = color == ChatColor.GREEN;
        ChatColor messageColor = (color != null) ? color: ChatColor.RESET;

        if(messages instanceof Collection<?> collection){
            for(Object message: collection){
                sender.sendMessage(messageColor + message.toString());
            }
        }else{
            sender.sendMessage(messageColor + (String) messages);

        }
        return isGreen;
    }
}
