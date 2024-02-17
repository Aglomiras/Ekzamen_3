package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Success extends OneShotBehaviour {
    @Override
    public void action() {
        log.info("Вот это сделка так сделка!");
    }
}
