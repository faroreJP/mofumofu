package farore.mfmf.common;

import farore.mfmf.Mofu;
import farore.mfmf.item.ItemHolder;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by farore on 16/06/12.
 */
public class EventHandler {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.HarvestDropsEvent event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();

        if (block == Blocks.GRAVEL && Mofu.rand.nextFloat() < 0.1f) {
            event.getDrops().add(new ItemStack(ItemHolder.whetstone));
        }
    }
}
