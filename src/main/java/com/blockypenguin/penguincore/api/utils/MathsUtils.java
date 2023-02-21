package com.blockypenguin.penguincore.api.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathsUtils {
	/**
	 * Code yoinked from {@link ProjectileEntity#setVelocity(Entity, float, float, float, float, float)}
	 * @param pitch The pitch of the input direction
	 * @param yaw The yaw of the input direction
	 * @param roll The roll of the input direction
	 * @param speed The speed
	 * @return A {@link Vec3d} representing the inputted direction at the inputted speed
	 */
	public Vec3d getVelocityFromDirection(float pitch, float yaw, float roll, float speed) {
        float a = -MathHelper.sin(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        float b = -MathHelper.sin((pitch + roll) * ((float)Math.PI / 180));
        float c = MathHelper.cos(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        
        return new Vec3d(a, b, c).normalize().multiply(speed);
    }
}