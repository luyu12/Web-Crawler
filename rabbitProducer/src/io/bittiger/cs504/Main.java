package io.bittiger.cs504;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
public class Main {
    private static final String EXCHANGE_NAME = "e_topic_logs";

    private static void topic_producer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

        String routingKeyInfo = "5.info";
        String messageInfo = "info msg2: hello cs504";

        channel.basicPublish(EXCHANGE_NAME, routingKeyInfo, null, messageInfo.getBytes());
        System.out.println(" [x] Sent '" + routingKeyInfo + "':'" + messageInfo + "'");


        String routingKeyError = "136.77.102.2.error";
        String messageError = "error msg2: server crash";

        channel.basicPublish(EXCHANGE_NAME, routingKeyError, null, messageError.getBytes());
        System.out.println(" [x] Sent '" + routingKeyError + "':'" + messageError + "'");

        String routingKeyWarn = "*.warn.127.0.0.1";
        String messageWarn = "warn msg2: CPU usuage too high";

        channel.basicPublish(EXCHANGE_NAME, routingKeyWarn, null, messageWarn.getBytes());
        System.out.println(" [x] Sent '" + routingKeyWarn + "':'" + messageWarn + "'");

        String routingKeyWarn2 = "localhost.warn";
        String messageWarn2 = "warn msg2: out of memory";

        channel.basicPublish(EXCHANGE_NAME, routingKeyWarn2, null, messageWarn2.getBytes());
        System.out.println(" [x] Sent '" + routingKeyWarn2 + "':'" + messageWarn2 + "'");


        connection.close();
    }
    public static void main(String[] args) throws Exception {
        topic_producer();
    }
}
