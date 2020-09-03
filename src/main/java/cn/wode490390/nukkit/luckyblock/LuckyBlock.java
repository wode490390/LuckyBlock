package cn.wode490390.nukkit.luckyblock;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.network.protocol.OnScreenTextureAnimationPacket;
import cn.nukkit.permission.PermissibleBase;
import cn.nukkit.permission.Permission;
import cn.nukkit.permission.PermissionAttachment;
import cn.nukkit.permission.PermissionAttachmentInfo;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cn.wode490390.nukkit.luckyblock.block.BlockIdentifiers;
import cn.wode490390.nukkit.luckyblock.util.MetricsLite;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LuckyBlock extends PluginBase implements Listener, CommandSender {

    private boolean sendMessage;
    private String message;
    private boolean sendAnimation;
    private final Int2ObjectMap<List<Entry>> blocks = new Int2ObjectOpenHashMap<>();

    private PermissibleBase perm;

    private final OnScreenTextureAnimationPacket animationPacket = new OnScreenTextureAnimationPacket();

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this, 4839);
        } catch (Throwable ignore) {

        }

        this.saveDefaultConfig();
        Config config = this.getConfig();

        Map<String, Object> section;
        String node = "blocks";
        try {
            section = config.getSection(node);
        } catch (Exception e) {
            this.logLoadException(node, e);
            return;
        }

        for (String key : section.keySet()) {
            node = "blocks." + key;
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
                    probability = ((Number) map.get("pr")).doubleValue();
                } catch (Exception ignore) {
                    continue;
                }
                try {
                    command = (String) map.get("cmd");
                } catch (Exception ignore) {
                    continue;
                }
                list.add(new Entry(probability, command));
            }

            if (!list.isEmpty()) {
                Integer id;
                try {
                    id = Integer.valueOf(key);
                    if (BlockIdentifiers.MAPPING.inverse().get(id) == null) {
                        throw new IllegalArgumentException("Unknown block ID");
                    }
                } catch (Exception e) {
                    id = BlockIdentifiers.MAPPING.get(key);
                }
                if (id != null) {
                    this.blocks.put(id, list);
                } else {
                    this.getLogger().notice("Unknown block: " + key);
                }
            }
        }

        if (!this.blocks.isEmpty()) {
            node = "send-msg";
            try {
                this.sendMessage = config.getBoolean(node, true);
            } catch (Exception e) {
                this.sendMessage = true;
                this.logLoadException(node, e);
            }

            if (this.sendMessage) {
                node = "msg";
                String value = "&6You are so lucky!";
                try {
                    this.message = TextFormat.colorize(config.getString(node, value));
                } catch (Exception e) {
                    this.message = TextFormat.colorize(value);
                    this.logLoadException(node, e);
                }
            }

            node = "send-animation";
            try {
                this.sendAnimation = config.getBoolean(node, true);
            } catch (Exception e) {
                this.sendAnimation = true;
                this.logLoadException(node, e);
            }

            if (this.sendAnimation) {
                node = "animation-id";
                try {
                    this.animationPacket.effectId = Effect.getEffect(config.getInt(node, Effect.HASTE)).getId();
                } catch (Exception e) {
                    this.animationPacket.effectId = Effect.HASTE;
                    this.logLoadException(node, e);
                }
                this.animationPacket.encode();
            }

            this.perm = new PermissibleBase(this);
            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int mode = player.getGamemode();
        int id = event.getBlock().getId();
        List<Entry> entries;
        if ((mode == Player.SURVIVAL || mode == Player.ADVENTURE) && player.hasPermission("luckyblock.trigger") && (entries = this.blocks.get(id)) != null) {
            boolean success = false;

            Random random = ThreadLocalRandom.current();
            for (Entry entry : entries) {
                if (entry.probability > random.nextDouble()) {
                    try {
                        this.getServer().dispatchCommand(this, entry.command.replace("@p", player.getName()).replace("@s", player.getName()));
                    } catch (Exception ignore) {
                        continue;
                    }
                    success = true;
                }
            }

            if (success) {
                if (this.sendMessage) {
                    player.sendMessage(this.message);
                }
                if (this.sendAnimation) {
                    player.dataPacket(this.animationPacket);
                }
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
        //NOOP
    }

    @Override
    public void sendMessage(TextContainer message) {
        //NOOP
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        //NOOP
    }

    private void logLoadException(String node, Throwable t) {
        this.getLogger().alert("An error occurred while reading the configuration '" + node + "'. Use the default value.", t);
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
