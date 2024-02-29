package com.l4ckyruss1ano.activemqex.messageWithObject;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/objectProducer")
public class MessageProducerObject extends HttpServlet {
    @Resource(lookup = "openejb:Resource/MyJmsConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "openejb:Resource/FooQueue")
    private Queue queue;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession();
            ObjectMessage message = session.createObjectMessage();
            message.setObject(new EmployeeMessage("Ruslan"));
            session.createProducer(queue).send(message);
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
}
