package dev.comfortssablecompat;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ComfortsSableCompat.MOD_ID)
public final class ComfortsSableCompat {
    public static final String MOD_ID = "comforts_sable_compat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ComfortsSableCompat() {
        LOGGER.info("Comforts x Sable Compat loaded");
    }
}
