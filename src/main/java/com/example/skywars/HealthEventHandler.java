package com.example.skywars;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class HealthEventHandler {
   private float warningThresholdHP = 6.0F;
   private String customMessage = "";
   private boolean warningTriggered = false;
   private int cooldownCounter = 0;
   private boolean enabled = true;

   @SubscribeEvent
   public void onClientTick(TickEvent.ClientTickEvent event) {
      if (event.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         LocalClientPlayerEntity player = mc.field_71439_g;
         if (player != null) {
            if (this.enabled && this.customMessage != null && !this.customMessage.trim().isEmpty()) {
               float currentHealth = player.func_110143_aJ();
               if (currentHealth <= this.warningThresholdHP && !this.warningTriggered && this.cooldownCounter <= 0) {
                  this.sendWarningMessage(player, currentHealth);
                  this.warningTriggered = true;
                  this.cooldownCounter = 100;
               }

               if (currentHealth > this.warningThresholdHP && this.warningTriggered) {
                  this.warningTriggered = false;
               }

               if (this.cooldownCounter > 0) {
                  --this.cooldownCounter;
               }

            }
         }
      }
   }

   private void sendWarningMessage(LocalClientPlayerEntity player, float health) {
      String formattedMessage = this.customMessage.replace("{hp}", String.format("%.1f", health));
      player.func_71165_d(formattedMessage);
   }

   public float getThreshold() {
      return this.warningThresholdHP;
   }

   public void setThreshold(float threshold) {
      this.warningThresholdHP = threshold;
   }

   public String getCustomMessage() {
      return this.customMessage;
   }

   public void setCustomMessage(String message) {
      this.customMessage = message;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean toggleEnabled() {
      this.enabled = !this.enabled;
      return this.enabled;
   }
}
