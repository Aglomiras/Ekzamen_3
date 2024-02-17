package org.example.Behaviours.SellBehs;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ExpectationBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    @Override
    public void onStart() {
        messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive!=null) {
            int count = randomCount();

            if (count == 0) {
                log.info("Согласие с предложением " + myAgent.getLocalName());
                approvalMsg(receive.getSender());
            } else if (count == 1) {
                log.info("Игнорирование запроса " + myAgent.getLocalName());
            } else {
                log.info("Отказ от предложения " + myAgent.getLocalName());
                rejectionMsg(receive.getSender());
            }
        }else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }

    public int randomCount(){
        Random random = new Random();
        return random.nextInt(3);
    }

    public void approvalMsg(AID aid){
        ACLMessage message = new ACLMessage(ACLMessage.CFP);
        message.setContent("Согласен");
        message.addReceiver(aid);
        myAgent.send(message);
    }

    public void rejectionMsg(AID aid) {
        ACLMessage message = new ACLMessage(ACLMessage.PROXY);
        message.setContent("Отказываюсь");
        message.addReceiver(aid);
        myAgent.send(message);
    }
}