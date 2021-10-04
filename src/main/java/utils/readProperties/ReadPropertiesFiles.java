package utils.readProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadPropertiesFiles {

    //*********Constructor*********
    private ReadPropertiesFiles() {
    }

    //*********Declarations*********
    private static final String propFileName = "Config.properties";
    private static Properties properties;
    private static final HashMap<String, String> defaultValues = new HashMap<String, String>() {
        {
            put(ConfigConstants.waitSingletonTimeSec, ConfigConstants.waitSingletonTimeSecValue);
            put(ConfigConstants.basePageMaxRetries, ConfigConstants.basePageMaxRetriesValue);
        }
    };

    //*********Methods*********
    public static String getValue(String key) {
        if (properties == null) {
            properties = initPropFromFile();
        }
        return getValueFromMatchingPropSource(key);
    }

    private static String getValueFromMatchingPropSource(String key) {
        final String propValue = properties.getProperty(key);
        if (propValue == null || propValue.isEmpty()) {
            return defaultValues.get(key);
        }
        return propValue;
    }

    private static Properties initPropFromFile() throws UncheckedIOException {
        Properties prop = new Properties();
        try {
            InputStream inputStream = ReadPropertiesFiles.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream==null){
                throw new IOException("config file not found");
            }
            prop.load(inputStream);
        } catch (IOException e) {
            final IOException error = new IOException("property config file " + propFileName + " not found in classpath\n" + e);
            throw new UncheckedIOException(error);
        }
        return prop;
    }

}
