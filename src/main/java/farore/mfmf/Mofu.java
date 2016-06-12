package farore.mfmf;

import farore.mfmf.common.TabBase;
import farore.mfmf.item.ItemHolder;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

/**
 * Created by farore on 16/06/12.
 */
@Mod(modid = Mofu.MODID, version = Mofu.VERSION, dependencies = Mofu.MOD_DEPENDENCIES)
public class Mofu {
    public static final String MODID = "mofumofu";
    public static final String VERSION = "0.0.1";
    public static final String MOD_DEPENDENCIES = "required-after:Forge@[1.9-12.17.0.1953,)";

    public static Random rand = new Random();

    @Mod.Instance
    public static Object obj;

    public static CreativeTabs tabMain;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        tabMain = new TabBase("main");
        ItemHolder.register();

        if(event.getSide()== Side.CLIENT){
            ItemHolder.bakeJson();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new farore.mfmf.common.EventHandler());
    }
}
