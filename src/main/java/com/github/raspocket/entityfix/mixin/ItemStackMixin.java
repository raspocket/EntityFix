package com.github.raspocket.entityfix.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Unique
    private static final Logger soapClient$LOGGER = LogManager.getLogger("ItemStackMixin");

    @Inject(method = "hasEffect", at = @At("HEAD"), cancellable = true)
    private void wrapHasEffect(CallbackInfoReturnable<Boolean> cir) {
        try {
            cir.setReturnValue(((ItemStack) (Object) this).getItem().hasEffect((ItemStack) (Object) this));
        } catch (Exception e) {
            soapClient$LOGGER.info("Error in hasEffect: ", e);
            cir.setReturnValue(false);
        }
    }
}
