package com.example.skywars;

import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.exception.CommandException;
import net.minecraft.server.command.source.CommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Formatting;

public class CommandToggleHP extends AbstractCommand {
   private final HealthEventHandler handler;

   public CommandToggleHP(HealthEventHandler handler) {
      this.handler = handler;
   }

   public String getName() {
      return "togglehp";
   }

   public String getCommandUsage(CommandSource sender) {
      return "/togglehp";
   }

   public int hasTargetSelectorAt() {
      return 0;
   }

   public void run(CommandSource sender, String[] args) throws CommandException {
      boolean newState = this.handler.toggleEnabled();
      if (newState) {
         sender.sendMessage(new LiteralText(Formatting.GREEN + "Low HP auto message has been " + Formatting.BOLD + "ENABLED" + Formatting.GREEN + "."));
      } else {
         sender.sendMessage(new LiteralText(Formatting.RED + "Low HP auto message has been " + Formatting.BOLD + "DISABLED" + Formatting.RED + "."));
      }
   }
}
