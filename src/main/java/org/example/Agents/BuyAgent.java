package org.example.Agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.example.Behaviours.BuyBehs.BuyFSMBehaviour;

@Slf4j
public class BuyAgent extends Agent {
    @Override
    protected void setup() {
        log.info(this.getLocalName() + " родился!");
        this.addBehaviour(new BuyFSMBehaviour());
    }
}
