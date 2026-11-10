package com.example.skywars;

import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.exception.CommandException;
import net.minecraft.server.command.source.CommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Formatting;

public class CommandSetMsg extends AbstractCommand {
   private final HealthEventHandler handler;

   public CommandSetMsg(HealthEventHandler handler) {
      this.handler = handler;
   }

   public String getName() {
      return "setmsg";
   }

   public String getCommandUsage(CommandSource sender) {
      return "/setmsg <message>";
   }

   public int hasTargetSelectorAt() {
      return 0;
   }

   public void run(CommandSource sender, String[] args) throws CommandException {
      if (args.length < 1) {
         sender.sendMessage(new LiteralText(Formatting.RED + "Usage: /setmsg <message>"));
      } else {
         StringBuilder builder = new StringBuilder();

         for(int i = 0; i < args.length; ++i) {
            builder.append(args[i]);
            if (i < args.length - 1) {
               builder.append(" ");
            }
         }

         String newMessage = builder.toString();
         this.handler.setCustomMessage(newMessage);
         sender.sendMessage(new LiteralText(Formatting.GREEN + "Warning message updated to: " + Formatting.RESET + newMessage.replace('&', '§')));
      }
   }
}
