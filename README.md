# Comforts x Sable Compat

A small NeoForge 1.21.1 compatibility mod that lets you **sleep in [Comforts](https://modrinth.com/mod/comforts) sleeping bags and hammocks placed on [Sable](https://modrinth.com/mod/sable) physics structures**, such as [Create Aeronautics](https://modrinth.com/mod/create-aeronautics) ships.

Without it, using a sleeping bag or hammock on a ship always fails with *"You may not rest now, the sleeping bag is too far away"*.

## Why it happens

Sable stores the blocks of a moving structure in a separate "plot" far away from where the ship is shown in the world. Sable already fixes the vanilla bed distance check for this, but Comforts uses its own distance check (`BaseComfortsBlock#isReachableBedBlock`), which still compares the player's world position with the plot position of the bed.

This mod adds a single mixin to that check: when the bed is on a Sable structure, the player's position is first converted into the structure's local frame (using [Sable Companion](https://github.com/ryanhcode/sable-companion), Sable's public compatibility API), then Comforts' original rule is applied. Everything else (lying down, waking up) already goes through vanilla code that Sable handles.

## Requirements

| Mod | Version | |
|---|---|---|
| Minecraft / NeoForge | 1.21.1 / 21.1.233+ | required |
| Comforts | 9.0.5+ | required |
| Sable | 2.0.5+ | optional: without it the mod does nothing |

Sable Companion (MIT) is bundled inside the jar.

**Server-side:** the fix only runs on the server. Install it on the server (or in your client for singleplayer). Clients without the mod can still join a server that has it.

## Known limitations

- Comforts' "monsters nearby" check does not detect monsters around a ship. Vanilla beds behave the same way with Sable.
- If the ship has rotated since it was assembled, the sleeping player is rendered with the wrong orientation. This also happens with vanilla beds and comes from Sable, not from this mod.

## Building

Requires JDK 21.

```
./gradlew build       # jar in build/libs/
./gradlew runClient   # dev client with Comforts, Sable, Create and Create Aeronautics
```

## Credits

- [Comforts](https://github.com/illusivesoulworks/comforts) by TheIllusiveC4 (LGPL-3.0)
- [Sable](https://github.com/ryanhcode/sable) and [Sable Companion](https://github.com/ryanhcode/sable-companion) by ryanhcode and contributors (Sable Companion is MIT and bundled in this mod)
- [Create Aeronautics](https://modrinth.com/mod/create-aeronautics) by the Simulated Team
- [Create Aeronautics: Compatibility](https://modrinth.com/mod/create-aeronautics-compatability) by rsap, whose fixes for other mods inspired this project. No code was copied from it.

This mod contains no code from Comforts, Sable or Create Aeronautics; it only targets them with a mixin.

## License

[MIT](LICENSE) © 2026 Areloup
