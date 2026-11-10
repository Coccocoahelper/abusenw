package com.example.skywars;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
   modid = "healthtracker",
   version = "1.0",
   clientSideOnly = true,
   acceptedMinecraftVersions = "[1.8.9]"
)
public class HealthTracker {
   public static final String MODID = "healthtracker";
   public static final String VERSION = "1.0";

   @EventHandler
   public void init(FMLInitializationEvent event) {
      HealthEventHandler eventHandler = new HealthEventHandler();
      MinecraftForge.EVENT_BUS.register(eventHandler);
      ClientCommandHandler.instance.func_71560_a(new CommandSetHP(eventHandler));
      ClientCommandHandler.instance.func_71560_a(new CommandSetMsg(eventHandler));
      ClientCommandHandler.instance.func_71560_a(new CommandToggleHP(eventHandler));
   }
}
