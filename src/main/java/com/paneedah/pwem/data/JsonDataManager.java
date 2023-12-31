package com.paneedah.pwem.data;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URL;

public class JsonDataManager {
    File file;
    JSONObject root;

    public JsonDataManager(final File file) {
        this.file = file;
        try {
            if (!file.exists()) {
                final URL url = JsonDataManager.class.getClassLoader().getResource("configs/"+file.getName());
                if (url == null) throw new NullPointerException("Resource is null.");
                FileUtils.copyURLToFile(url, file);
            }
            root = new JSONObject(new JSONTokener(new FileReader(file)));
            new ConfigurationVerifier(this).verify();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void save() {
        try {
            FileUtils.writeStringToFile(file, root.toString(4), "UTF-8");
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void reload() {
        try {
            root = new JSONObject(new JSONTokener(new FileReader(file)));
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void set(final String path, final Object value) {
        final String[] split = path.split("\\.");
        JSONObject current = root;
        try {
            for (int i = 0; i < split.length - 1; i++)
                current = current.getJSONObject(split[i]);
        } catch (final JSONException ex) {
            current = root;
            for (int i = 0; i < split.length - 1; i++) {
                try {
                    current = current.getJSONObject(split[i]);
                } catch (final JSONException ex2) {
                    current.put(split[i], new JSONObject());
                    current = current.getJSONObject(split[i]);
                }
            }
        }
        try {
            current.put(split[split.length - 1], value);
        } catch (final JSONException ex) {
            ex.printStackTrace();
        }
        save();
    }

    public Object get(final String path) {
        try {
            final String[] split = path.split("\\.");
            JSONObject current = root;
            for (int i = 0; i < split.length - 1; i++)
                current = current.getJSONObject(split[i]);
            return current.get(split[split.length - 1]);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public JSONObject getJsonObject(final String path) {
        try {
            return (JSONObject)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray getJSONArray(final String path) {
        try {
            return (JSONArray)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getString(final String path) {
        try {
            return get(path).toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getInt(final String path) {
        try {
            final Object obj = get(path);
            if (obj instanceof Double) return ((Double)obj).intValue();
            if (obj instanceof Integer) return (Integer)obj;
            if (obj instanceof Long) return ((Long)obj).intValue();
            if (obj instanceof BigDecimal) return ((BigDecimal)obj).intValue();
            if (obj instanceof Float) return ((Float)obj).intValue();
            throw new IllegalStateException("Object is not a supported value (Double, Integer, Long, BigDecimal, Float).");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public boolean getBoolean(final String path) {
        try {
            final Object obj = get(path);
            if (obj instanceof Boolean) return (Boolean)obj;
            if (obj instanceof String) return Boolean.parseBoolean((String)obj);
            throw new IllegalStateException("Object is not a supported value (Boolean, String).");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public double getDouble(final String path) {
        try {
            final Object obj = get(path);
            if (obj instanceof Double) return (Double)obj;
            if (obj instanceof Integer) return ((Integer)obj).doubleValue();
            if (obj instanceof Long) return ((Long)obj).doubleValue();
            if (obj instanceof BigDecimal) return ((BigDecimal)obj).doubleValue();
            if (obj instanceof Float) return ((Float)obj).doubleValue();
            throw new IllegalStateException("Object is not a supported value (Double, Integer, Long, BigDecimal, Float).");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0.00D;
        }
    }

    public long getLong(final String path) {
        try {
            final Object obj = get(path);
            if (obj instanceof Double) return ((Double)obj).longValue();
            if (obj instanceof Integer) return ((Integer)obj).longValue();
            if (obj instanceof Long) return (Long)obj;
            if (obj instanceof BigDecimal) return ((BigDecimal)obj).longValue();
            if (obj instanceof Float) return ((Float)obj).longValue();
            throw new IllegalStateException("Object is not a supported value (Double, Integer, Long, BigDecimal, Float).");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

    public float getFloat(final String path) {
        try {
            final Object obj = get(path);
            if (obj instanceof Double) return ((Double)obj).floatValue();
            if (obj instanceof Integer) return ((Integer)obj).floatValue();
            if (obj instanceof Long) return ((Long)obj).floatValue();
            if (obj instanceof BigDecimal) return ((BigDecimal)obj).floatValue();
            if (obj instanceof Float) return (Float)obj;
            throw new IllegalStateException("Object is not a supported value (Double, Integer, Long, BigDecimal, Float).");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0F;
        }
    }

    public boolean has(final String path) {
        try {
            return get(path) != null;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
