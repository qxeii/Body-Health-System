package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import xyz.srgnis.bodyhealthsystem.client.screen.HealScreen;

public class CustomScreen {
    public static void registerScreens() {
        HandledScreens.register(CustomScreenHandler.BAG_SCREEN_HANDLER, HealScreen::new);
    }
}
