package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ProcessingBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private boolean flag = false;
    private int count = 0;

    @Override
    public void onStart() {
        messageTemplate = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CFP), MessageTemplate.MatchPerformative(ACLMessage.PROXY));
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive != null) {
            if (receive.getPerformative() == ACLMessage.CFP) {
                count = 1;
                flag = true;
            } else {
                count = 2;
                flag = true;
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return flag;
    }

    @Override
    public int onEnd() {
        return count;
    }
}
