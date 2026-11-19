package dev.comfortssablecompat.mixin;

import com.illusivesoulworks.comforts.common.block.BaseComfortsBlock;
import dev.ryanhcode.sable.companion.SableCompanion;
import dev.ryanhcode.sable.companion.SubLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Comforts checks the sleeping distance itself (instead of going through vanilla
 * {@code ServerPlayer#isReachableBedBlock}, which Sable already patches). On a Sable structure the
 * bed's BlockPos lives in the structure's plot, far away from the player's world position, so the
 * check always fails with "too far away".
 * <p>
 * When the bed is inside a Sable sub-level, we move the player's position into the sub-level's local
 * frame first, then apply Comforts' original rule (3 blocks horizontally, 2 vertically).
 */
@Mixin(BaseComfortsBlock.class)
public abstract class BaseComfortsBlockMixin {

    @Inject(method = "isReachableBedBlock", at = @At("HEAD"), cancellable = true)
    private static void comforts_sable_compat$reachableOnSubLevel(ServerPlayer player, BlockPos bedPos,
                                                                   CallbackInfoReturnable<Boolean> cir) {
        SubLevelAccess subLevel = SableCompanion.INSTANCE.getContaining(player.level(), bedPos);

        if (subLevel == null) {
            return; // Not on a Sable structure: keep Comforts' behaviour untouched.
        }
        Vec3 playerLocal = subLevel.logicalPose().transformPositionInverse(player.position());
        Vec3 bed = Vec3.atBottomCenterOf(bedPos);
        cir.setReturnValue(Math.abs(playerLocal.x - bed.x) <= 3.0D
                && Math.abs(playerLocal.y - bed.y) <= 2.0D
                && Math.abs(playerLocal.z - bed.z) <= 3.0D);
    }
}
