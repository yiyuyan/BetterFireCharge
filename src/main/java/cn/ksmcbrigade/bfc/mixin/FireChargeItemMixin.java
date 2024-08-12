package cn.ksmcbrigade.bfc.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin extends Item {
    public FireChargeItemMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    @Unique
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, InteractionHand p_41434_) {

        int expLevel = 4;
        CompoundTag tag = player.getItemInHand(p_41434_).getTag();
        if(tag!=null && tag.get("level")!=null){
            try {
                expLevel = tag.getInt("level");
            }
            catch (Exception e){
            }
        }

        LargeFireball fireball = new LargeFireball(level,player,player.getLookAngle().x,player.getLookAngle().y,player.getLookAngle().z,expLevel);

        fireball.setPos(player.getEyePosition());
        level.addFreshEntity(fireball);

        player.getItemInHand(p_41434_).shrink(1);

        return super.use(level,player, p_41434_);
    }
}
