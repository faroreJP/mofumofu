package farore.mfmf.common;

import farore.mfmf.item.ItemHolder;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by farore on 16/06/12.
 */
public class TabBase extends CreativeTabs {
    public TabBase(String label) {
        super("mfmf." + label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemHolder.whetstone;
    }
}
