package com.example.skywars;

import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.exception.CommandException;
import net.minecraft.server.command.source.CommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Formatting;

public class CommandSetHP extends AbstractCommand {
   private final HealthEventHandler handler;

   public CommandSetHP(HealthEventHandler handler) {
      this.handler = handler;
   }

   public String getName() {
      return "sethp";
   }

   public String getCommandUsage(CommandSource sender) {
      return "/sethp <amount>";
   }

   public int hasTargetSelectorAt() {
      return 0;
   }

   public void run(CommandSource sender, String[] args) throws CommandException {
      if (args.length < 1) {
         sender.sendMessage(new LiteralText(Formatting.RED + "Usage: /sethp <amount>"));
      } else {
         try {
            float newHP = Float.parseFloat(args[0]);
            this.handler.setThreshold(newHP);
            sender.sendMessage(new LiteralText(Formatting.GREEN + "Health threshold set to " + newHP + " HP (" + newHP / 2.0F + " hearts)."));
         } catch (NumberFormatException var4) {
            sender.sendMessage(new LiteralText(Formatting.RED + "Invalid number!"));
         }

      }
   }
}
