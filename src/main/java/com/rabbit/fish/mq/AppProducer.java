package com.rabbit.fish.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


/**
 *
 * @author lijianli
 * @date 2017/12/26
 */
public class AppProducer {
    private static final String URL="tcp://10.60.201.146:61616";
    private static final String QUEUENAME="queue_test";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);

        Connection connection=connectionFactory.createConnection();

        connection.start();

        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination destination= session.createQueue(QUEUENAME);

        MessageProducer producer=session.createProducer(destination);

        for (int i=0;i<100;i++){
            TextMessage textMessage=session.createTextMessage("test"+i);
            producer.send(textMessage);
            System.out.println("send message:"+textMessage.getText());
        }

        connection.close();
    }
}
