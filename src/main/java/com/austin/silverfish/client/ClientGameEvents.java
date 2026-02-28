package com.austin.silverfish.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;

@EventBusSubscriber(modid = "peacefulsilverfish", bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientGameEvents {

    private static boolean silverfishMuted = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ClientModBusEvents.MUTE_SILVERFISH_KEY.get().consumeClick()) {
            silverfishMuted = !silverfishMuted;

            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.displayClientMessage(
                        Component.literal("Silverfish sounds: " + (silverfishMuted ? "MUTED" : "ON")),
                        true
                );
            }
        }
    }

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        if (!silverfishMuted) return;
        if (event.getSound() == null) return;

        var id = event.getSound().getLocation();
        if (!"minecraft".equals(id.getNamespace())) return;

        if (id.getPath().startsWith("entity.silverfish.")) {
            event.setSound(null);
        }
    }
}