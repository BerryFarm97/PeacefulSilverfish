package com.austin.silverfish.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "peacefulsilverfish", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModBusEvents {

    public static final Lazy<KeyMapping> MUTE_SILVERFISH_KEY = Lazy.of(() ->
            new KeyMapping(
                    "key.peacefulsilverfish.mute_silverfish",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_U,
                    "key.categories.peacefulsilverfish"
            )
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(MUTE_SILVERFISH_KEY.get());
    }
}