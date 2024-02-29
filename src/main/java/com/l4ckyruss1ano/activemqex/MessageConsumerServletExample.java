package com.l4ckyruss1ano.activemqex;

import jakarta.annotation.Resource;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(value = "/consumer")
public class MessageConsumerServletExample extends HttpServlet {
    @Resource(lookup = "openejb:Resource/MyJmsConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "openejb:Resource/FooQueue")
    private Queue queue;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Context context = null;
        try {
            //this setting we define in file tomcat/conf/tomee.xml
//            context = new InitialContext();
//            ConnectionFactory connectionFactory =(ConnectionFactory) context.lookup("openejb:Resource/MyJmsConnectionFactory");
//            Queue queue = (Queue) context.lookup("openejb:Resource/FooQueue");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession();
            MessageConsumer messageConsumer = session.createConsumer(queue);
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            System.out.println("Consumer received message: " + textMessage.getText());
//            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
