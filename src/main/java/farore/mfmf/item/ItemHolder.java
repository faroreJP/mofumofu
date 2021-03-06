package farore.mfmf.item;

import farore.mfmf.Mofu;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by farore on 16/06/12.
 */
public class ItemHolder {
    public static Item whetstone;
    public static Item knife;

    public static void register(){
        whetstone = new ItemWhetstone(0.05f).setUnlocalizedName("whetstone");
        GameRegistry.register(whetstone, new ResourceLocation(Mofu.MODID, "whetstone"));

        knife = new ItemKnife();
        GameRegistry.register(knife, new ResourceLocation(Mofu.MODID, "knife"));
    }

    public static void bakeJson(){
        ModelLoader.setCustomModelResourceLocation(whetstone, 0, new ModelResourceLocation(whetstone.getRegistryName(), "whetstone"));
        ModelLoader.setCustomModelResourceLocation(knife, 0, new ModelResourceLocation(knife.getRegistryName(), "knife"));
    }
}

