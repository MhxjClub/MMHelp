package me.frkovo.mmhelp;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(!sender.hasPermission("mmhelp.use"))return false;
       if(args.length < 1){
           sender.sendMessage("§c理解不了你在说啥。");
           sender.sendMessage("§c/mmhelp set <mmid> - 修改Mob");
           sender.sendMessage("§c/mmhelp tool - 修改绑定工具");
            return true;
       }
       switch (args[0]){
           case "set":{
                if(args.length < 2)break;
                main.mmid = args[1];
                sender.sendMessage("§aSUCCESSFUL- "+main.mmid);
                main.s = 0;
                break;
           }
           case "tool":{
               if(((Player)sender).getInventory().getItemInMainHand().getType() == Material.AIR){
                   sender.sendMessage("§c绑NM空气");
                   break;
               }
               main.material = ((Player)sender).getInventory().getItemInMainHand().getType();
               main.cfg.set("item",((Player)sender).getInventory().getItemInMainHand().getType().toString().replace("Material.",""));
               sender.sendMessage("§aSUCCESSFUL- "+main.material);
               break;
           }
           default:{
               sender.sendMessage("§c理解不了你在说啥。");
               sender.sendMessage("§c/mmhelp set <mmid> - 修改Mob");
               sender.sendMessage("§c/mmhelp tool - 修改绑定工具");
               break;
           }
       }
        return true;
    }
}
