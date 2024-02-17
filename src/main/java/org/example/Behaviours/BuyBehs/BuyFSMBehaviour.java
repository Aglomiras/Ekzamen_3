package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.FSMBehaviour;

public class BuyFSMBehaviour extends FSMBehaviour {
    private final String
            FIRST_STATE = "firstState",
            PROC = "processing",
            SUCCESS = "success",
            FAIL = "fail",
            RESTART = "restart";


    @Override
    public void onStart() {
        this.registerFirstState(new OrderBeh(), FIRST_STATE);
        this.registerState(new ParallelProcessingBeh(), PROC);
        this.registerLastState(new BuyFSMBehaviour(), RESTART);
        this.registerLastState(new Fail(), FAIL);
        this.registerLastState(new Success(), SUCCESS);

        this.registerDefaultTransition(FIRST_STATE, PROC);
        this.registerTransition(PROC, SUCCESS, 1);
        this.registerTransition(PROC, FAIL, 2);
        this.registerTransition(PROC, RESTART, 0);
    }
}
