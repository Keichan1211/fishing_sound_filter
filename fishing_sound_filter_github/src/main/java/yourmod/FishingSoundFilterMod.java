package yourmod;

import net.fabricmc.api.ClientModInitializer;

public class FishingSoundFilterMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("[FishingSoundFilter] Client initialized.");
    }
}
