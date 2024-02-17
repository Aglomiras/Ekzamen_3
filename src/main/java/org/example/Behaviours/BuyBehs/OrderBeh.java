package org.example.Behaviours.BuyBehs;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderBeh extends OneShotBehaviour {
    @Override
    public void action() {
        log.info("Отправляю запрос!!! " + myAgent.getLocalName());
        ACLMessage message = new ACLMessage(ACLMessage.AGREE);
        message.setContent("book");
        message.addReceiver(new AID("SellAgent", false));
        myAgent.send(message);
    }
}
