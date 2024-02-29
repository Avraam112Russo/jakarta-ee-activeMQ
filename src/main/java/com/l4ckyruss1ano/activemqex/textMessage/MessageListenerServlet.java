//package com.l4ckyruss1ano.activemqex.textMessage;
//
//import jakarta.annotation.Resource;
//import jakarta.inject.Inject;
//import jakarta.jms.*;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(value = "/listener")
//public class MessageListenerServlet extends HttpServlet {
//    @Resource(lookup = "openejb:Resource/MyJmsConnectionFactory")
//    private ConnectionFactory connectionFactory;
//    @Resource(lookup = "openejb:Resource/FooQueue")
//    private Queue queue;
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Session session = null;
//        try {
//            Connection connection = connectionFactory.createConnection();
//            connection.start();
//            session = connection.createSession();
//            MessageConsumer messageConsumer = session.createConsumer(queue);
//            messageConsumer.setMessageListener((msg) -> {
//                try {
//                    //async handling message
//                    System.out.println("msg listener sent new msg: " + msg.getBody(String.class));
//                } catch (JMSException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//
//
//        } catch (JMSException e) {
//            throw new RuntimeException(e);
//        }
//    }

//}
