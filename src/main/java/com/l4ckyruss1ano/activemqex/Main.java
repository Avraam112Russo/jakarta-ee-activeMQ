package com.l4ckyruss1ano.activemqex;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = BrokerFactory.createBroker("broker:(tcp://localhost:61616)");
        brokerService.start();
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("customerQueue");
//            Topic topic = session.createTopic("customerTopic");
            Message message = session.createTextMessage("Message was sent at: " + new Date());
            MessageProducer producer = session.createProducer(queue);
            producer.send(message);
            MessageConsumer messageConsumer = session.createConsumer(queue);
            connection.start();
            TextMessage textMessage =(TextMessage) messageConsumer.receive();
            System.out.println(textMessage.getText());
            Thread.sleep(500);
            session.close();
        }finally {
            if (connection!=null){
                connection.close();
            }
        }

        brokerService.stop();
    }
}
