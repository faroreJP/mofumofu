package farore.mfmf.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by farore on 16/06/12.
 */
public class ItemWhetstone extends ItemBase{
    public float repairRatio;

    public ItemWhetstone(float repair) {
        repairRatio = repair;
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (hand != EnumHand.OFF_HAND) return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);

        ItemStack mainItemStack = playerIn.getHeldItem(EnumHand.MAIN_HAND);
        if (mainItemStack == null || !(mainItemStack.getItem() instanceof ItemSword))
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);

        int damage = mainItemStack.getItemDamage();
        mainItemStack.setItemDamage(Math.max(damage - (int) (mainItemStack.getMaxDamage() * repairRatio), 0));

        itemStackIn.stackSize--;

        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}
