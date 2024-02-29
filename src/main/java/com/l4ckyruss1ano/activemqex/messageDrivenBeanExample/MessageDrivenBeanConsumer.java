package com.l4ckyruss1ano.activemqex.messageDrivenBeanExample;

import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenBean;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

// async handling message
@MessageDriven(mappedName = "FooQueue")
public class MessageDrivenBeanConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            for (int i = 1; i <=10; i++){
                Thread.sleep(1000);
                System.out.println(i);
            }
            System.out.println("Async received message: "+message.getBody(String.class));
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
