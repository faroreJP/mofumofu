package farore.mfmf.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by farore on 16/06/14.
 */
public class ItemKnife extends ItemBase {
    public ItemKnife() {
        setUnlocalizedName("knife");
        setMaxDamage(128);
        setHasSubtypes(false);
        setMaxStackSize(1);
    }

    @Override
    public boolean isFull3D(){
        return true;
    }

    @Override
    public int getItemEnchantability(){
        return 0;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        stack.damageItem(1, entityLiving);
        Block block = state.getBlock();
        return state.getMaterial() != Material.LEAVES && block != Blocks.WEB && block != Blocks.TALLGRASS && block != Blocks.VINE && block != Blocks.TRIPWIRE && block != Blocks.WOOL && !(state instanceof net.minecraftforge.common.IShearable) ? super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving) : true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        Block block = blockIn.getBlock();
        return block == Blocks.WEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();
        return block != Blocks.WEB && state.getMaterial() != Material.LEAVES ? (block == Blocks.WOOL ? 5.0F : super.getStrVsBlock(stack, state)) : 15.0F;
    }


    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand) {
        if (entity.worldObj.isRemote) {
            return false;
        }
        if (entity instanceof net.minecraftforge.common.IShearable) {
            net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable) entity;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (target.isShearable(itemstack, entity.worldObj, pos)) {
                java.util.List<ItemStack> drops = target.onSheared(itemstack, entity.worldObj, pos, 5);

                java.util.Random rand = new java.util.Random();
                for(int i=0;i<2;i++) {
                    for (ItemStack stack : drops) {
                        net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(stack.copy(), 1.0F);
                        ent.motionY += rand.nextFloat() * 0.05F;
                        ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                        ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    }
                }
                itemstack.damageItem(1, entity);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, net.minecraft.entity.player.EntityPlayer player) {
        if (player.worldObj.isRemote || player.capabilities.isCreativeMode) {
            return false;
        }
        Block block = player.worldObj.getBlockState(pos).getBlock();
        if (block instanceof net.minecraftforge.common.IShearable) {
            net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable) block;
            if (target.isShearable(itemstack, player.worldObj, pos)) {
                java.util.List<ItemStack> drops = target.onSheared(itemstack, player.worldObj, pos, 5);
                java.util.Random rand = new java.util.Random();

                for (ItemStack stack : drops) {
                    float f = 0.7F;
                    double d = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d2 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    net.minecraft.entity.item.EntityItem entityitem = new net.minecraft.entity.item.EntityItem(player.worldObj, (double) pos.getX() + d, (double) pos.getY() + d1, (double) pos.getZ() + d2, stack);
                    entityitem.setDefaultPickupDelay();
                    player.worldObj.spawnEntityInWorld(entityitem);
                }

                itemstack.damageItem(1, player);
                player.addStat(net.minecraft.stats.StatList.getBlockStats(block));
            }
        }
        return false;
    }
}