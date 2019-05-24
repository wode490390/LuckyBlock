# Lucky Block
[![](https://i.loli.net/2019/05/24/5ce745b82a0a470870.gif)](http://www.mcbbs.net/thread-868171-1-1.html "幸运方块")

Lucky block plugin for Nukkit

Please see [mcbbs](http://www.mcbbs.net/thread-868171-1-1.html) for more information.
## Permissions
| Permission | Description | Default |
| - | - | - |
| luckyblock.trigger | Allows player to trigger lucky block | true |
## config.yml
Tip: https://minecraft.gamepedia.com/Bedrock_Edition_data_values#Block_IDs
```yaml
send-msg: true
msg: "&6You are so lucky!"
blocks:
  # block namespaced id
  emerald_ore:
    -
      # command (@p = player)
      cmd: "give @p 382 2"
      # probability (0-1)
      pr: 0.0005
    -
      cmd: "give @p 450 1"
      pr: 0.0002
```
