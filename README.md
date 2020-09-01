# Lucky Block
[![Nukkit](https://img.shields.io/badge/Nukkit-1.0-green)](https://github.com/NukkitX/Nukkit)
[![Build](https://img.shields.io/circleci/build/github/wode490390/LuckyBlock/master)](https://circleci.com/gh/wode490390/LuckyBlock/tree/master)
[![Release](https://img.shields.io/github/v/release/wode490390/LuckyBlock)](https://github.com/wode490390/LuckyBlock/releases)
[![Release date](https://img.shields.io/github/release-date/wode490390/LuckyBlock)](https://github.com/wode490390/LuckyBlock/releases)
<!--[![MCBBS](https://img.shields.io/badge/-mcbbs-inactive)](https://www.mcbbs.net/thread-868171-1-1.html "幸运方块")
[![Servers](https://img.shields.io/bstats/servers/4839)](https://bstats.org/plugin/bukkit/LuckyBlock/4839)
[![Players](https://img.shields.io/bstats/players/4839)](https://bstats.org/plugin/bukkit/LuckyBlock/4839)-->

The customizable LuckyBlock plugin for Nukkit servers.

![](https://i.loli.net/2019/05/24/5ce745b82a0a470870.gif)

If you found any bugs or have any suggestions, please open an issue on [GitHub Issues](https://github.com/wode490390/LuckyBlock/issues).

If you like this plugin, please star it on [GitHub](https://github.com/wode490390/LuckyBlock).

## Configuration
<details>
<summary>config.yml</summary>

```yaml
send-msg: true
msg: "&6You are so lucky!"

send-animation: true
animation-id: 3

blocks:
  # block namespaced id
  # see https://minecraft.gamepedia.com/Bedrock_Edition_data_values#Block_IDs
  emerald_ore:
    -
      # command (@p = player's name)
      cmd: "give @p 382 2"
      # probability (0-1)
      pr: 0.0005
    -
      cmd: "give @p 450 1"
      pr: 0.0002
```
</details>

## Permissions
| Permission | Description | Default |
| - | - | - |
| luckyblock.trigger | Allows a player to trigger the lucky block | true |

## Download
- [Releases](https://github.com/wode490390/LuckyBlock/releases)
- [Snapshots](https://circleci.com/gh/wode490390/LuckyBlock)

## Compiling
1. Install [Maven](https://maven.apache.org/).
2. Run `mvn clean package`. The compiled JAR can be found in the `target/` directory.

## Metrics Collection

This plugin uses [bStats](https://github.com/wode490390/bStats-Nukkit). You can opt out using the global bStats config; see the [official website](https://bstats.org/getting-started) for more details.

<!--[![Metrics](https://bstats.org/signatures/bukkit/LuckyBlock.svg)](https://bstats.org/plugin/bukkit/LuckyBlock/4839)-->

###### If I have any grammar and/or term errors, please correct them :)
