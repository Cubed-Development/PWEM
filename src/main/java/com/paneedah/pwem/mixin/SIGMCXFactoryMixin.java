package com.paneedah.pwem.mixin;

import com.paneedah.mwc.items.guns.SIGMCXFactory;
import com.paneedah.pwem.JsonDataManager;
import com.paneedah.pwem.PWEM;
import com.paneedah.weaponlib.compatibility.RecoilParam;
import com.paneedah.weaponlib.config.BalancePackManager;
import org.json.JSONObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mixin(value = SIGMCXFactory.class, remap = false)
public class SIGMCXFactoryMixin {

    private static final String weaponName = SIGMCXFactory.class.getSimpleName().replace("Factory", "");
    private static final JsonDataManager weaponData = new JsonDataManager(new File(PWEM.CONFIG_DIR, weaponName+".json"));

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withSpawnEntityDamage(F)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private float modifyDamage(final float f) {
        return weaponData.getFloat("stats.damage");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withFireRate(F)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private float modifyFireRate(final float f) {
        return weaponData.getFloat("stats.fireRate");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withRecoil(F)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private float modifyRecoil(final float f) {
        return weaponData.getFloat("stats.recoil");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withSpawnEntityGravityVelocity(F)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private float modifyGravity(final float f) {
        return weaponData.getFloat("stats.gravity");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withZoom(F)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private float modifyZoom(final float f) {
        return weaponData.getFloat("stats.zoom");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withConfigGroup(Lcom/paneedah/weaponlib/config/BalancePackManager$GunConfigurationGroup;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private BalancePackManager.GunConfigurationGroup modifyConfigGroup(final BalancePackManager.GunConfigurationGroup cg) {
        return BalancePackManager.GunConfigurationGroup.valueOf(weaponData.getString("stats.group"));
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withMaxShots([I)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private int[] modifyMaxShots(final int[] i) {
        final JSONObject object = weaponData.getJsonObject("shooting");
        final List<Integer> shotList = new ArrayList<>();
        if (object.getBoolean("single")) shotList.add(1);
        if (object.has("burst") && object.getInt("burst") != 0) shotList.add(object.getInt("burst"));
        if (object.getBoolean("auto")) shotList.add(Integer.MAX_VALUE);
        return shotList.stream().mapToInt(Integer::intValue).toArray();
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withShootSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyShootSound(final String s) {
        return weaponData.getString("sound.shoot");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withSilencedShootSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifySilencedShootSound(final String s) {
        return weaponData.getString("sound.silencedShoot");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withReloadSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyReloadSound(final String s) {
        return weaponData.getString("sound.reload");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withUnloadSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyUnloadSound(final String s) {
        return weaponData.getString("sound.unload");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withEndOfShootSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyEndOfShootSound(final String s) {
        return weaponData.getString("sound.endOfShootSound");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withInspectSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyInspectSound(final String s) {
        return weaponData.getString("sound.inspect");
    }

    @ModifyArg(method = "createGun", at = @At(value = "INVOKE", target = "Lcom/paneedah/weaponlib/Weapon$Builder;withDrawSound(Ljava/lang/String;)Lcom/paneedah/weaponlib/Weapon$Builder;", remap = false), remap = false, require = 0)
    private String modifyDrawSound(final String s) {
        return weaponData.getString("sound.draw");
    }
}
