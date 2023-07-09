<h1 align="center">Panda's Weapon Editor Mod</h1>

<h5 align="center">An open-source weapon editor for Modern Warfare: Cubed!</h5>

<p align="center">
  <a href="https://modrinth.com/mod/pwem">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg">
  </a>

  <a href="https://www.curseforge.com/minecraft/mc-mods/pwem">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg">
  </a>

  <a href="https://patreon.com/ModernWarfareCubed">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/patreon-plural_vector.svg"/>
  </a>

  <a href="https://discord.gg/k5WPk93K7b">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-plural_vector.svg">
  </a>
</p>

<p align="center">
  <a href="https://adoptium.net/temurin/releases/?version=8">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/java8_vector.svg"/>
  </a>

  <a href="https://gradle.org/">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/gradle_vector.svg"/>
  </a>

  <a href="http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html">
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/forge_vector.svg"/>
  </a>
</p>

<br>

<h2 align="center">What is this mod?</h2>

Panda's Weapon Editor Mod is an open-source weapon editor for Modern Warfare: Cubed. It allows you to modify existing weapons from [Modern Warfare Cubed](https://github.com/Paneedah/Modern-Warfare-Cubed/) (a free and open-source fork of Vic's Modern Warfare - check it out if you're not currently using it!).

- It will create a new folder in the `config/mwc` directory called `pwem` (Panda's Weapon Editor Mod) where all of your custom weapons will be stored.
- Each weapon will have its own `.json` file, which you can edit to your liking.

<br>

<h2 align="center">Weapon Configuration</h2>

Here's a default configuration file for the **AACHoneyBadger**, and I will walk you through how to make your changes!
- Please note that there will be a lot more detail provided on our Wiki page for this GitHub repository. If you have additional questions, contact us on Discord (linked above).

```json
{
    "stats": {
        "damage": 5.4,
        "fireRate": 0.8,
        "recoil": 3.0,
        "gravity": 0.0118,
        "zoom": 0.9,
        "group": "RIFLES"
    },
    "shooting": {
        "single": true,
        "burst": 0,
        "auto": true
    },
    "recoil": {
        "weaponPower": 15.0,
        "muzzleClimbDivisor": 15.75,
        "stockLength": 50.0,
        "prnr": 0.4,
        "prnsr": 0.3125,
        "weaponRotX": 0.0,
        "weaponRotY": 0.0,
        "adsSimilarity": 1.0
    },
    "sound": {
        "shoot": "aac_honeybadger",
        "silencedShoot": "aac_honeybadger",
        "reload": "honeybadger_reload",
        "unload": "honeybadger_unload",
        "endOfShootSound": "gun_click",
        "inspect": "m4a1_inspection",
        "draw": "m4_draw"
    }
}
```

### Stats
- `damage` - The amount of damage the weapon will do.
- `fireRate` - The rate of fire for the weapon.
- `recoil` - The amount of recoil the weapon will have.
- `gravity` - The amount of gravity the bullet will have while in the air (the higher the number, the more gravity).
- `zoom` - The amount of zoom the weapon will have when aiming down sights.
- `group` - The group the weapon will be in. This is shown in the gun's tooltip.

### Shooting
- `single` - Whether or not the weapon can shoot in single fire mode.
- `burst` - The amount of bullets the weapon will shoot in burst fire mode (0 to disable).
- `auto` - Whether or not the weapon can shoot in automatic fire mode.

### Recoil
- `weaponPower` - The power of the weapon.
- `muzzleClimbDivisor` - The muzzle climb divisor.
- `stockLength` - "Stock Length".
- `prnr` - Recovery rate from initial shot.
- `prnsr` - Recovery rate @ "stock".
- `weaponRotX` - Weapon rotation X.
- `weaponRotY` - Weapon rotation Y.
- `adsSimilarity` - ADS Similarity divisor.

### Sound
- `shoot` - The sound the weapon will make when shooting.
- `silencedShoot` - The sound the weapon will make when shooting with a silencer.
- `reload` - The sound the weapon will make when reloading.
- `unload` - The sound the weapon will make when unloading.
- `endOfShootSound` - The sound the weapon will make when it's out of ammo.
- `inspect` - The sound the weapon will make when inspecting.
- `draw` - The sound the weapon will make when drawing.

<br>

## Client & Server Sided!
This mod is both client and server sided, meaning that you can use it on your server and clients will be able to join without having to install the mod themselves.
- Ensure you provide the `config/mwc/pwem` folder to your clients so that they can see the custom weapon statistics.

<br>

<h2 align="center">How do I contribute?</h2>

<br>

Anyone and everyone is welcome to contribute and help out with the project! If you're interested in building the project, the instructions are below.

To contribute, please read the [contributing guide](https://github.com/Paneedah/Modern-Warfare-Cubed/blob/master/CONTRIBUTING.md).

<br>

