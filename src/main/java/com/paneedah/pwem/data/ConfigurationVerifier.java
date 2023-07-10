package com.paneedah.pwem.data;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

import static com.paneedah.pwem.data.ModReference.LOG;

public class ConfigurationVerifier {

    private final JsonDataManager dataManager;

    public ConfigurationVerifier(final JsonDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void verify() {
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configs/"+dataManager.file.getName());
        if (inputStream == null) {
            LOG.error("Could not find default file: " + dataManager.file.getName());
            return;
        }

        String json;
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            json = new String(bytes);
        } catch (Exception e) {
            LOG.error("Could not read default file: " + dataManager.file.getName());
            return;
        }

        final JSONObject originalRoot = new JSONObject(new JSONTokener(json));

        final JSONObject originalStats = originalRoot.getJSONObject("stats");
        final JSONObject stats = dataManager.has("stats") ? dataManager.getJsonObject("stats") : null;
        if (stats == null || stats.isEmpty()) {
            dataManager.set("stats", originalStats);
            LOG.info("Added missing category: stats to " + dataManager.file.getName());
        } else {
            for (String key : originalStats.keySet()) {
                if (!dataManager.has("stats." + key)) {
                    final Object originalStat = originalStats.get(key);
                    dataManager.set("stats." + key, originalStat);
                    LOG.info("Added missing stats key: stats." + key + " to " + dataManager.file.getName());
                }
            }
        }

        final JSONObject originalShooting = originalRoot.getJSONObject("shooting");
        final JSONObject shooting = dataManager.has("shooting") ? dataManager.getJsonObject("shooting") : null;
        if (shooting == null || shooting.isEmpty()) {
            dataManager.set("shooting", originalShooting);
            LOG.info("Added missing category: shooting to " + dataManager.file.getName());
        } else {
            for (String key : originalShooting.keySet()) {
                if (!dataManager.has("shooting." + key)) {
                    final Object originalStat = originalShooting.get(key);
                    dataManager.set("shooting." + key, originalStat);
                    LOG.info("Added missing shooting key: shooting." + key + " to " + dataManager.file.getName());
                }
            }
        }

        final JSONObject originalSound = originalRoot.getJSONObject("sound");
        final JSONObject sound = dataManager.has("sound") ? dataManager.getJsonObject("sound") : null;
        if (sound == null || sound.isEmpty()) {
            dataManager.set("sound", originalSound);
            LOG.info("Added missing category: sound to " + dataManager.file.getName());
        } else {
            for (String key : originalSound.keySet()) {
                if (!dataManager.has("sound." + key)) {
                    final Object originalStat = originalSound.get(key);
                    dataManager.set("sound." + key, originalStat);
                    LOG.info("Added missing sound key: sound." + key + " to " + dataManager.file.getName());
                }
            }
        }
    }
}
