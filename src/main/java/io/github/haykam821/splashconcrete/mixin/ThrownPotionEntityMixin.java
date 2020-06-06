package io.github.haykam821.splashconcrete.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.entity.thrown.ThrownPotionEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(ThrownPotionEntity.class)
class ThrownPotionEntityMixin {
	@Inject(method = "extinguishFire", at = @At("TAIL"))
	public void hardenSplashedConcrete(BlockPos pos, Direction direction, CallbackInfo ci) {
		World world = ((ThrownPotionEntity) (Object) this).world;

		BlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();

		if (block instanceof ConcretePowderBlock) {
			ConcretePowderBlock concretePowderBlock = (ConcretePowderBlock) block;
			world.setBlockState(pos, concretePowderBlock.hardenedState);
		}
	}
}