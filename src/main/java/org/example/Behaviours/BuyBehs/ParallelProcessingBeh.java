package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelProcessingBeh extends ParallelBehaviour {
    private Behaviour wakeupBeh;
    private ProcessingBeh receiveBeh;

    public ParallelProcessingBeh() {
        super(WHEN_ANY);
    }

    @Override
    public void onStart() {
        receiveBeh = new ProcessingBeh();
        wakeupBeh = new WakerBehaviour(myAgent, 10000) {
            boolean wake = false;

            @Override
            protected void onWake() {
                wake = true;
                log.info("TIME IS UP");
            }

            @Override
            public int onEnd() {
                return wake ? 0 : 1;
            }
        };
        this.addSubBehaviour(receiveBeh);
        this.addSubBehaviour(wakeupBeh);
    }

    @Override
    public int onEnd() {
        if (wakeupBeh.done()) {
            if (receiveBeh.onEnd() == 1) {
                log.info("Принятие согласия!");
                return 1;
            } else if (receiveBeh.onEnd() == 2) {
                log.info("Обработка отказа...");
                return 2;
            } else {
                log.info("Отправка повторного запроса");
                return 0;
            }
        } else {
            if (receiveBeh.onEnd() == 1) {
                log.info("Принятие согласия!");
                return 1;
            } else if (receiveBeh.onEnd() == 2) {
                log.info("Обработка отказа...");
                return 2;
            } else {
                log.info("Отправка повторного запроса");
                return 0;
            }
        }
    }
}
