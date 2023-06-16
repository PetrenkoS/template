package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoad {

    private static final String CONFIG_PROPERTIES = "config";
    private static final String USER_PROPERTIES = "user";
    private static PropertyLoad instance = new PropertyLoad();
    private Properties configMap = new Properties();
    private Properties generalMap = new Properties();


    private PropertyLoad() {
        init();
    }

    public static PropertyLoad getInstance() {
        if (instance == null)
            instance = new PropertyLoad();
        return instance;
    }

    private void init() {
        loadPropertiesFromClassPath(configMap, CONFIG_PROPERTIES);
        loadPropertiesFromClassPath(configMap, USER_PROPERTIES);
        generalMap.putAll(configMap);
    }

    public String getProperty(String key) {
        String result = System.getProperty(key);
        try {
            if (result == null || result.equals(""))
                result = (String) generalMap.get(key);
        } catch (NullPointerException ignored) {
            System.out.println("Property \"" + key + "\" is missing");
            System.out.println("Using default property.");
        }
        return result;
    }

    private void loadPropertiesFromClassPath(Properties properties, String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream resourceStream = classLoader.getResourceAsStream(fileName + ".properties");

            if (resourceStream != null)
                properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
