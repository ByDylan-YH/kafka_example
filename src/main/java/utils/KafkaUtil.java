package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;


class KafkaUtil {
    private static Properties properties = new Properties();

    public static void main(String[] args) {
        String name = "producer";
        System.out.println(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        Properties jsonProperties = KafkaUtil.getJsonProperties(name);
        getConfProperties();
    }

    //解析Json文件
    private static Properties getJsonProperties(String name) {
        Gson gson = new Gson();
        JsonReader reader;
        JsonObject jsonObject;
        try {
            reader = new JsonReader(new FileReader("D:\\WorkSpace\\ideaProject\\kafka_example\\doc\\kafka.json"));
            jsonObject = gson.fromJson(reader, JsonObject.class);
            for (Map.Entry<String, JsonElement> entry : jsonObject.getAsJsonObject(name).entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
                properties.put(entry.getKey(), entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static void getConfProperties() {
        try {
            properties.load(KafkaUtil.class.getResourceAsStream("/conf.properties"));
            System.out.println(properties.getProperty("topic"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}