package com.paneedah.pwem;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URL;

import static com.paneedah.pwem.ModReference.LOG;

@SuppressWarnings("unused")
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

    public JSONObject getRoot() {
        return root;
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
            return (String)get(path);
        } catch (final Exception ex) {
            LOG.error("An error occurred while parsing the String from the path: "+path+". Returning as string value.");
            try {
                return get(path).toString();
            } catch (final JSONException ex1) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public int getInt(final String path) {
        try {
            return (int)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public boolean getBoolean(final String path) {
        try {
            return (Boolean)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public double getDouble(final String path) {
        try {
            return (double)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0.00D;
        }
    }

    public long getLong(final String path) {
        try {
            return (long)get(path);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

    public float getFloat(final String path) {
        try {
            return ((BigDecimal)get(path)).floatValue();
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

    public boolean isEmpty(final String path) {
        return root.isEmpty();
    }
}
