package com.l4ckyruss1ano.activemqex.messageDrivenBeanExample;

import jakarta.annotation.Resource;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/prod")
public class MyMessageProducer extends HttpServlet {

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
            TextMessage textMessage = session.createTextMessage(req.getParameter("msg"));
            session.createProducer(queue).send(textMessage);
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
