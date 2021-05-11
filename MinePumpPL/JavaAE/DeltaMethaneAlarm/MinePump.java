class MinePump {

    //OK 

    //@invariant \invariant_for(env);

    boolean systemActive;
    boolean pumpRunning;
    EnvironmentI env;



    /*@ public normal_behavior
      @ ensures \result == env.isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
      @ */
	boolean isMethaneAlarm() {
		return env.isMethaneLevelCritical();
	}

    /*@
      @ ensures !pumpRunning;
      @ */
	void deactivatePump() {
		pumpRunning = false;
	}

        /*@
          @ ensures (\old(systemActive) && \old(pumpRunning) && \old(this.isMethaneAlarm())) ==> !pumpRunning;
          @ */
    void timeShift() {
        if (systemActive && pumpRunning && isMethaneAlarm()) {
            deactivatePump();
        } else {
            /*@
              @ exceptional_behavior requires false;
              @ break_behavior requires false;
              @ continue_behavior requires false;
              @ return_behavior requires false;
              @ normal_behavior ensures \invariant_for(this);
              @*/
            \abstract_statement Original;
        }
    }

}

interface EnvironmentI {
	/*@
	  @ public normal_behavior
	  @ ensures \result == isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
	  @ */
	boolean isMethaneLevelCritical();
}