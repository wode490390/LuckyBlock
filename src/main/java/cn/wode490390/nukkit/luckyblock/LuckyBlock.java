package cn.wode490390.nukkit.luckyblock;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.permission.PermissibleBase;
import cn.nukkit.permission.Permission;
import cn.nukkit.permission.PermissionAttachment;
import cn.nukkit.permission.PermissionAttachmentInfo;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LuckyBlock extends PluginBase implements Listener, CommandSender {

    private static final ImmutableMap<String, Integer> IDENTIFIER = ImmutableMap.<String, Integer>builder()
            .put("air", Block.AIR)
            .put("stone", Block.STONE)
            .put("grass", Block.GRASS)
            .put("dirt", Block.DIRT)
            .put("cobblestone", Block.COBBLESTONE)
            .put("planks", Block.PLANK)
            .put("sapling", Block.SAPLING)
            .put("bedrock", Block.BEDROCK)
            .put("flowing_water", Block.WATER)
            .put("water", Block.STILL_WATER)
            .put("flowing_lava", Block.LAVA)
            .put("lava", Block.STILL_LAVA)
            .put("sand", Block.SAND)
            .put("gravel", Block.GRAVEL)
            .put("gold_ore", Block.GOLD_ORE)
            .put("iron_ore", Block.IRON_ORE)
            .put("coal_ore", Block.COAL_ORE)
            .put("log", Block.LOG)
            .put("leaves", Block.LEAVES)
            .put("sponge", Block.SPONGE)
            .put("glass", Block.GLASS)
            .put("lapis_ore", Block.LAPIS_ORE)
            .put("lapis_block", Block.LAPIS_BLOCK)
            .put("dispenser", Block.DISPENSER)
            .put("sandstone", Block.SANDSTONE)
            .put("noteblock", Block.NOTEBLOCK)
            .put("bed", Block.BED_BLOCK)
            .put("golden_rail", Block.POWERED_RAIL)
            .put("detector_rail", Block.DETECTOR_RAIL)
            .put("sticky_piston", Block.STICKY_PISTON)
            .put("web", Block.COBWEB)
            .put("tallgrass", Block.TALL_GRASS)
            .put("deadbush", Block.BUSH)
            .put("piston", Block.PISTON)
            .put("pistonArmCollision", Block.PISTON_HEAD)
            .put("wool", Block.WOOL)
            .put("element_0", 36)
            .put("yellow_flower", Block.DANDELION)
            .put("red_flower", Block.POPPY)
            .put("brown_mushroom", Block.BROWN_MUSHROOM)
            .put("red_mushroom", Block.RED_MUSHROOM)
            .put("gold_block", Block.GOLD_BLOCK)
            .put("iron_block", Block.IRON_BLOCK)
            .put("double_stone_slab", Block.DOUBLE_SLAB)
            .put("stone_slab", Block.SLAB)
            .put("brick_block", Block.BRICKS)
            .put("tnt", Block.TNT)
            .put("bookshelf", Block.BOOKSHELF)
            .put("mossy_cobblestone", Block.MOSS_STONE)
            .put("obsidian", Block.OBSIDIAN)
            .put("torch", Block.TORCH)
            .put("fire", Block.FIRE)
            .put("mob_spawner", Block.MONSTER_SPAWNER)
            .put("oak_stairs", Block.WOOD_STAIRS)
            .put("chest", Block.CHEST)
            .put("redstone_wire", Block.REDSTONE_WIRE)
            .put("diamond_ore", Block.DIAMOND_ORE)
            .put("diamond_block", Block.DIAMOND_BLOCK)
            .put("crafting_table", Block.CRAFTING_TABLE)
            .put("wheat", Block.WHEAT_BLOCK)
            .put("farmland", Block.FARMLAND)
            .put("furnace", Block.FURNACE)
            .put("lit_furnace", Block.BURNING_FURNACE)
            .put("standing_sign", Block.SIGN_POST)
            .put("wooden_door", Block.DOOR_BLOCK)
            .put("ladder", Block.LADDER)
            .put("rail", Block.RAIL)
            .put("stone_stairs", Block.COBBLE_STAIRS)
            .put("wall_sign", Block.WALL_SIGN)
            .put("lever", Block.LEVER)
            .put("stone_pressure_plate", Block.STONE_PRESSURE_PLATE)
            .put("iron_door", Block.IRON_DOOR_BLOCK)
            .put("wooden_pressure_plate", Block.WOODEN_PRESSURE_PLATE)
            .put("redstone_ore", Block.REDSTONE_ORE)
            .put("lit_redstone_ore", Block.GLOWING_REDSTONE_ORE)
            .put("unlit_redstone_torch", Block.UNLIT_REDSTONE_TORCH)
            .put("redstone_torch", Block.REDSTONE_TORCH)
            .put("stone_button", Block.STONE_BUTTON)
            .put("snow_layer", Block.SNOW)
            .put("ice", Block.ICE)
            .put("snow", Block.SNOW_BLOCK)
            .put("cactus", Block.CACTUS)
            .put("clay", Block.CLAY_BLOCK)
            .put("reeds", Block.REEDS)
            .put("jukebox", Block.JUKEBOX)
            .put("fence", Block.FENCE)
            .put("pumpkin", Block.PUMPKIN)
            .put("netherrack", Block.NETHERRACK)
            .put("soul_sand", Block.SOUL_SAND)
            .put("glowstone", Block.GLOWSTONE)
            .put("portal", Block.NETHER_PORTAL)
            .put("lit_pumpkin", Block.LIT_PUMPKIN)
            .put("cake", Block.CAKE_BLOCK)
            .put("unpowered_repeater", Block.UNPOWERED_REPEATER)
            .put("powered_repeater", Block.POWERED_REPEATER)
            .put("invisibleBedrock", Block.INVISIBLE_BEDROCK)
            .put("trapdoor", Block.TRAPDOOR)
            .put("monster_egg", Block.MONSTER_EGG)
            .put("stonebrick", Block.STONE_BRICKS)
            .put("brown_mushroom_block", Block.BROWN_MUSHROOM_BLOCK)
            .put("red_mushroom_block", Block.RED_MUSHROOM_BLOCK)
            .put("iron_bars", Block.IRON_BAR)
            .put("glass_pane", Block.GLASS_PANE)
            .put("melon_block", Block.MELON_BLOCK)
            .put("pumpkin_stem", Block.PUMPKIN_STEM)
            .put("melon_stem", Block.MELON_STEM)
            .put("vine", Block.VINE)
            .put("fence_gate", Block.FENCE_GATE)
            .put("brick_stairs", Block.BRICK_STAIRS)
            .put("stone_brick_stairs", Block.STONE_BRICK_STAIRS)
            .put("mycelium", Block.MYCELIUM)
            .put("waterlily", Block.WATER_LILY)
            .put("nether_brick", Block.NETHER_BRICKS)
            .put("nether_brick_fence", Block.NETHER_BRICK_FENCE)
            .put("nether_brick_stairs", Block.NETHER_BRICKS_STAIRS)
            .put("nether_wart", Block.NETHER_WART_BLOCK)
            .put("enchanting_table", Block.ENCHANTING_TABLE)
            .put("brewing_stand", Block.BREWING_STAND_BLOCK)
            .put("cauldron", Block.CAULDRON_BLOCK)
            .put("end_portal", Block.END_PORTAL)
            .put("end_portal_frame", Block.END_PORTAL_FRAME)
            .put("end_stone", Block.END_STONE)
            .put("dragon_egg", Block.DRAGON_EGG)
            .put("redstone_lamp", Block.REDSTONE_LAMP)
            .put("lit_redstone_lamp", Block.LIT_REDSTONE_LAMP)
            .put("dropper", Block.DROPPER)
            .put("activator_rail", Block.ACTIVATOR_RAIL)
            .put("cocoa", Block.COCOA)
            .put("sandstone_stairs", Block.SANDSTONE_STAIRS)
            .put("emerald_ore", Block.EMERALD_ORE)
            .put("ender_chest", Block.ENDER_CHEST)
            .put("tripwire_hook", Block.TRIPWIRE_HOOK)
            .put("tripWire", Block.TRIPWIRE)
            .put("emerald_block", Block.EMERALD_BLOCK)
            .put("spruce_stairs", Block.SPRUCE_WOOD_STAIRS)
            .put("birch_stairs", Block.BIRCH_WOOD_STAIRS)
            .put("jungle_stairs", Block.JUNGLE_WOOD_STAIRS)
            .put("command_block", 137)
            .put("beacon", Block.BEACON)
            .put("cobblestone_wall", Block.COBBLE_WALL)
            .put("flower_pot", Block.FLOWER_POT_BLOCK)
            .put("carrots", Block.CARROT_BLOCK)
            .put("potatoes", Block.POTATO_BLOCK)
            .put("wooden_button", Block.WOODEN_BUTTON)
            .put("skull", Block.SKULL_BLOCK)
            .put("anvil", Block.ANVIL)
            .put("trapped_chest", Block.TRAPPED_CHEST)
            .put("light_weighted_pressure_plate", Block.LIGHT_WEIGHTED_PRESSURE_PLATE)
            .put("heavy_weighted_pressure_plate", Block.HEAVY_WEIGHTED_PRESSURE_PLATE)
            .put("unpowered_comparator", Block.UNPOWERED_COMPARATOR)
            .put("powered_comparator", Block.POWERED_COMPARATOR)
            .put("daylight_detector", Block.DAYLIGHT_DETECTOR)
            .put("redstone_block", Block.REDSTONE_BLOCK)
            .put("quartz_ore", Block.QUARTZ_ORE)
            .put("hopper", Block.HOPPER_BLOCK)
            .put("quartz_block", Block.QUARTZ_BLOCK)
            .put("quartz_stairs", Block.QUARTZ_STAIRS)
            .put("double_wooden_slab", Block.DOUBLE_WOOD_SLAB)
            .put("wooden_slab", Block.WOOD_SLAB)
            .put("stained_hardened_clay", Block.STAINED_TERRACOTTA)
            .put("stained_glass_pane", Block.STAINED_GLASS_PANE)
            .put("leaves2", Block.LEAVES2)
            .put("log2", Block.WOOD2)
            .put("acacia_stairs", Block.ACACIA_WOOD_STAIRS)
            .put("dark_oak_stairs", Block.DARK_OAK_WOOD_STAIRS)
            .put("slime", Block.SLIME_BLOCK)
            //166 item
            .put("iron_trapdoor", Block.IRON_TRAPDOOR)
            .put("prismarine", Block.PRISMARINE)
            .put("seaLantern", Block.SEA_LANTERN)
            .put("hay_block", Block.HAY_BALE)
            .put("carpet", Block.CARPET)
            .put("hardened_clay", Block.TERRACOTTA)
            .put("coal_block", Block.COAL_BLOCK)
            .put("packed_ice", Block.PACKED_ICE)
            .put("double_plant", Block.DOUBLE_PLANT)
            .put("standing_banner", Block.STANDING_BANNER)
            .put("wall_banner", Block.WALL_BANNER)
            .put("daylight_detector_inverted", Block.DAYLIGHT_DETECTOR_INVERTED)
            .put("red_sandstone", Block.RED_SANDSTONE)
            .put("red_sandstone_stairs", Block.RED_SANDSTONE_STAIRS)
            .put("double_stone_slab2", Block.DOUBLE_RED_SANDSTONE_SLAB)
            .put("stone_slab2", Block.RED_SANDSTONE_SLAB)
            .put("spruce_fence_gate", Block.FENCE_GATE_SPRUCE)
            .put("birch_fence_gate", Block.FENCE_GATE_BIRCH)
            .put("jungle_fence_gate", Block.FENCE_GATE_JUNGLE)
            .put("dark_oak_fence_gate", Block.FENCE_GATE_DARK_OAK)
            .put("acacia_fence_gate", Block.FENCE_GATE_ACACIA)
            .put("repeating_command_block", 188)
            .put("chain_command_block", 189)
            .put("hard_glass_pane", 190)
            .put("hard_stained_glass_pane", 191)
            .put("chemical_heat", 192)
            .put("spruce_door", Block.SPRUCE_DOOR_BLOCK)
            .put("birch_door", Block.BIRCH_DOOR_BLOCK)
            .put("jungle_door", Block.JUNGLE_DOOR_BLOCK)
            .put("acacia_door", Block.ACACIA_DOOR_BLOCK)
            .put("dark_oak_door", Block.DARK_OAK_DOOR_BLOCK)
            .put("grass_path", Block.GRASS_PATH)
            .put("frame", Block.ITEM_FRAME_BLOCK)
            .put("chorus_flower", Block.CHORUS_FLOWER)
            .put("purpur_block", Block.PURPUR_BLOCK)
            .put("colored_torch_rg", 202)
            .put("purpur_stairs", Block.PURPUR_STAIRS)
            .put("colored_torch_bp", 204)
            .put("undyed_shulker_box", Block.UNDYED_SHULKER_BOX)
            .put("end_bricks", Block.END_BRICKS)
            .put("frosted_ice", Block.ICE_FROSTED)
            .put("end_rod", Block.END_ROD)
            .put("end_gateway", Block.END_GATEWAY)
            //210 edu
            //211 edu
            //212 edu
            .put("magma", Block.MAGMA)
            .put("nether_wart_block", Block.BLOCK_NETHER_WART_BLOCK)
            .put("red_nether_brick", Block.RED_NETHER_BRICK)
            .put("bone_block", Block.BONE_BLOCK)
            //217
            .put("shulker_box", Block.SHULKER_BOX)
            .put("purple_glazed_terracotta", Block.PURPLE_GLAZED_TERRACOTTA)
            .put("white_glazed_terracotta", Block.WHITE_GLAZED_TERRACOTTA)
            .put("orange_glazed_terracotta", Block.ORANGE_GLAZED_TERRACOTTA)
            .put("magenta_glazed_terracotta", Block.MAGENTA_GLAZED_TERRACOTTA)
            .put("light_blue_glazed_terracotta", Block.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .put("yellow_glazed_terracotta", Block.YELLOW_GLAZED_TERRACOTTA)
            .put("lime_glazed_terracotta", Block.LIME_GLAZED_TERRACOTTA)
            .put("pink_glazed_terracotta", Block.PINK_GLAZED_TERRACOTTA)
            .put("gray_glazed_terracotta", Block.GRAY_GLAZED_TERRACOTTA)
            .put("silver_glazed_terracotta", Block.SILVER_GLAZED_TERRACOTTA)
            .put("cyan_glazed_terracotta", Block.CYAN_GLAZED_TERRACOTTA)
            //230
            .put("blue_glazed_terracotta", Block.BLUE_GLAZED_TERRACOTTA)
            .put("brown_glazed_terracotta", Block.BROWN_GLAZED_TERRACOTTA)
            .put("green_glazed_terracotta", Block.GREEN_GLAZED_TERRACOTTA)
            .put("red_glazed_terracotta", Block.RED_GLAZED_TERRACOTTA)
            .put("black_glazed_terracotta", Block.BLACK_GLAZED_TERRACOTTA)
            .put("concrete", Block.CONCRETE)
            .put("concretePowder", Block.CONCRETE_POWDER)
            .put("chemistry_table", 238)
            .put("underwater_torch", 239)
            .put("chorus_plant", Block.CHORUS_PLANT)
            .put("stained_glass", Block.STAINED_GLASS)
            //242
            .put("podzol", Block.PODZOL)
            .put("beetroot", Block.BEETROOT_BLOCK)
            .put("stonecutter", Block.STONECUTTER)
            .put("glowingobsidian", Block.GLOWING_OBSIDIAN)
            .put("netherreactor", Block.NETHER_REACTOR)
            .put("info_update", 248)
            .put("info_update2", 249)
            .put("movingBlock", Block.PISTON_EXTENSION)
            .put("observer", Block.OBSERVER)
            .put("structure_block", 252)
            .put("hard_glass", 253)
            .put("hard_stained_glass", 254)
            .put("reserved6", 255)
            .build();

    private static final String PERMISSION_LUCKYBLOCK_TRIGGER = "luckyblock.trigger";

    private static final String CONFIG_SEND_MSG = "send-msg";
    private static final String CONFIG_MSG = "msg";
    private static final String CONFIG_BLOCKS = "blocks";
    private static final String CONFIG_PR = "pr";
    private static final String CONFIG_CMD = "cmd";

    private boolean send;
    private String msg;
    private final Map<Integer, List<Entry>> blocks = new Int2ObjectOpenHashMap<>();

    private PermissibleBase perm;

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this);
        } catch (Exception ignore) {

        }
        this.saveDefaultConfig();
        Config config = this.getConfig();
        Map<String, Object> section;
        String node = CONFIG_BLOCKS;
        try {
            section = config.getSection(node);
        } catch (Exception e) {
            this.logLoadException(node);
            return;
        }
        for (String key : section.keySet()) {
            node = CONFIG_BLOCKS + "." + key;
            List<Map> cfg;
            try {
                cfg = config.getMapList(node);
            } catch (Exception ignore) {
                continue;
            }
            List<Entry> list = new ObjectArrayList<>();
            for (Map map : cfg) {
                double probability;
                String command;
                try {
                    probability = ((Number) map.get(CONFIG_PR)).doubleValue();
                } catch (Exception ignore) {
                    continue;
                }
                try {
                    command = (String) map.get(CONFIG_CMD);
                } catch (Exception ignore) {
                    continue;
                }
                list.add(new Entry(probability, command));
            }
            if (!list.isEmpty()) {
                if (IDENTIFIER.containsKey(key)) {
                    this.blocks.put(IDENTIFIER.get(key), list);
                } else {
                    this.getLogger().notice("Unknown block: " + key);
                }
            }
        }
        if (!this.blocks.isEmpty()) {
            node = CONFIG_SEND_MSG;
            try {
                this.send = config.getBoolean(node, true);
            } catch (Exception e) {
                this.send = true;
                this.logLoadException(node);
            }
            if (this.send) {
                node = CONFIG_MSG;
                String value = "&6You are so lucky!";
                try {
                    this.msg = TextFormat.colorize(config.getString(node, value));
                } catch (Exception e) {
                    this.msg = TextFormat.colorize(value);
                    this.logLoadException(node);
                }
            }
            this.getServer().getPluginManager().registerEvents(this, this);
            this.perm = new PermissibleBase(this);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int id = event.getBlock().getId();
        if (player.getGamemode() == Player.SURVIVAL && player.hasPermission(PERMISSION_LUCKYBLOCK_TRIGGER) && this.blocks.containsKey(id)) {
            boolean success = false;
            for (Entry entry : this.blocks.get(id)) {
                if (entry.probability > ThreadLocalRandom.current().nextDouble()) {
                    try {
                        this.getServer().dispatchCommand(this, entry.command.replace("@p", player.getName()).replace("@s", player.getName()));
                    } catch (Exception ignore) {
                        continue;
                    }
                    success = true;
                }
            }
            if (success && this.send) {
                player.sendMessage(this.msg);
            }
        }
    }

    @Override
    public boolean isPermissionSet(String name) {
        return this.perm.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return this.perm.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String name) {
        return this.perm.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return this.perm.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.perm.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name) {
        return this.perm.addAttachment(plugin, name);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, Boolean value) {
        return this.perm.addAttachment(plugin, name, value);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        this.perm.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        this.perm.recalculatePermissions();
    }

    @Override
    public Map<String, PermissionAttachmentInfo> getEffectivePermissions() {
        return this.perm.getEffectivePermissions();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendMessage(TextContainer message) {

    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {

    }

    private void logLoadException(String node) {
        this.getLogger().alert("An error occurred while reading the configuration '" + node + "'. Use the default value.");
    }

    private static class Entry {

        private final double probability;
        private final String command;

        private Entry(double probability, String command) {
            this.probability = probability;
            this.command = command;
        }
    }
}
