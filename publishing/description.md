<!-- Description for the Modrinth and CurseForge project pages (both accept Markdown). -->

# Comforts x Sable Compat

**Sleep in your [Comforts](https://modrinth.com/mod/comforts) sleeping bags and hammocks on [Create Aeronautics](https://modrinth.com/mod/create-aeronautics) ships!**

Without this mod, trying to sleep in a sleeping bag or hammock placed on a [Sable](https://modrinth.com/mod/sable) physics structure always fails with:

> *You may not rest now, the sleeping bag is too far away*

This mod fixes that. Sleeping bags work at night and hammocks during the day, on ships and on the ground, just like normal.

## How it works

Sable keeps the blocks of a ship in a separate area far away from where the ship is shown in the world. Comforts uses its own distance check, which compares your position with that far-away area. This mod adds one small mixin that converts your position into the ship's frame before checking the distance, using Sable Companion, Sable's official compatibility API.

## Installation

- **Required:** Comforts 9.0.5+ (NeoForge 1.21.1)
- **Optional:** Sable 2.0.5+ / Create Aeronautics. Without Sable, the mod simply does nothing.
- **Server-side:** install it on the server, or in your game for singleplayer. Players without the mod can still join.

## Known limitations

- Comforts' "monsters nearby" check does not see monsters around a ship. Vanilla beds behave the same with Sable.
- If the ship has turned since it was assembled, the sleeping player can look rotated. This also happens with vanilla beds and comes from Sable itself.

## Credits

- **Comforts** by TheIllusiveC4
- **Sable** and **Sable Companion** by ryanhcode and contributors (Sable Companion, MIT, is bundled)
- **Create Aeronautics** by the Simulated Team
- **Create Aeronautics: Compatibility** by rsap, which fixes many other mods on Sable ships and inspired this project

This is an unofficial compatibility patch, not affiliated with the authors above. Please report issues with this mod on [GitHub](https://github.com/Lambert-Aaron/comforts-sable-compat/issues), not to them.

License: MIT
