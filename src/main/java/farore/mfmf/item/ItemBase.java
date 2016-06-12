package farore.mfmf.item;

import farore.mfmf.Mofu;
import net.minecraft.item.Item;

/**
 * Created by farore on 16/06/12.
 */
public class ItemBase extends Item {
    public ItemBase(){
        setCreativeTab(Mofu.tabMain);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName("mfmf." + name);
    }
}
