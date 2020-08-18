package utils;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

//获取集群所有Topic
class GetAllTopic {

    public static void main(String[] args) {
//        System.setProperty("java.security.auth.login.config", "D:\\WorkSpace\\ideaProject\\kafkamonitor\\src\\resource\\kafka_client_jaas.conf");
        String brokerList = "192.168.1.201:9092,192.168.1.211:9092,192.168.1.212:9092";
        String groupId = "bingo";
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        // 自动提交位移
        props.put("enable.auto.commit", "true");
        // consumer向zookeeper提交offset的频率，单位是秒
        props.put("auto.commit.interval.ms", "1000");
        // zookeeper会话超时时间
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        System.out.println(consumer.listTopics());
        int count = 0;
        for (String key : consumer.listTopics().keySet()) {
            System.out.println(key);
            count++;
        }
        System.out.println("共Topic: " + count);

    }
}