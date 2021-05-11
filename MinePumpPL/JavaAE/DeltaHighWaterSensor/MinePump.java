class MinePump {
    //to be finished
    
    //@invariant \invariant_for(env);

    boolean systemActive;
    boolean pumpRunning;
    EnvironmentI env;

    //@ghost boolean systemActiveAtL;
    //@ghost boolean pumpRunningAtL;

    //needed for remodelling

    /*@
      @ public normal_behavior
      @ ensures \result == 0;
      @ assignable \strictly_nothing;
      @ */
    int countProblems(){
        return 0;
    }

    /*@
      @ ensures (countProblems() == 0) ==> pumpRunning;
      @ */
	void activatePump() {
        if(countProblems() == 0)
            pumpRunning = true;
	}


    /*@
      @ public normal_behavior
      @ ensures \result == !env.isHighWaterSensorDry();
      @ assignable \strictly_nothing;
      @ */
    boolean isHighWaterLevel() {
        return !env.isHighWaterSensorDry();
    }

    /*@
      @ ensures systemActive && !pumpRunning && isHighWaterLevel() 
      @           ==> ((countProblems() == 0) ==> pumpRunning);
      @ */
    void timeShift() {
        //original();
        if(systemActive && !pumpRunning && isHighWaterLevel())
            activatePump();
    }
}


interface EnvironmentI {
    /*@
      @ public normal_behavior
      @ ensures \result == isHighWaterSensorDry();
      @ assignable \strictly_nothing;
      @ */
    boolean isHighWaterSensorDry();
}