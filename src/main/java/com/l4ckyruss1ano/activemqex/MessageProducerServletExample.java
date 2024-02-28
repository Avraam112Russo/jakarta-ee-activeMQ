package com.l4ckyruss1ano.activemqex;

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
import java.util.Date;

@WebServlet(value = "/producer")
public class MessageProducerServletExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context = new InitialContext();


            //this setting we define in file tomcat/conf/tomee.xml
            ConnectionFactory connectionFactory =(ConnectionFactory) context.lookup("openejb:Resource/MyJmsConnectionFactory");
            Queue queue = (Queue) context.lookup("openejb:Resource/FooQueue");
            Connection connection = connectionFactory.createConnection();
            connection.start();


            Session session = connection.createSession();
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage( req.getParameter("message") + "\n Date: " + new Date());
            producer.send(message);

            connection.close();
        } catch (NamingException | JMSException e) {
           e.printStackTrace();
        }
    }
}
