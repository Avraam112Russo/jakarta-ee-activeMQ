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


import java.io.IOException;

@WebServlet(value = "/objectConsumer")
public class MessageObjectConsumer extends HttpServlet {
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
            resp.getWriter().write(session.createConsumer(queue).receive().getBody(EmployeeMessage.class).getName());
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
