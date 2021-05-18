class MinePump {

  //TO BE FIXED

  //@invariant \invariant_for(env) && \disjoint(\dl_env_fields, pumpRunning) && \disjoint(\dl_env_fields, systemActive) && \disjoint(\dl_env_fields, env);

	boolean pumpRunning = false;

	boolean systemActive = true;

    int tmp;


	EnvironmentI env;

	public MinePump(EnvironmentI env) {
		this.env = env;
	}

    //remodelled
    /*@
      @ public normal_behavior
      @ ensures  (pumpRunning ==>
      @          (\old(env.getWaterLevel()) == WaterLevel.high) ==> (env.getWaterLevel() == WaterLevel.normal) 
      @       && (\old(env.getWaterLevel()) == WaterLevel.normal) ==> (env.getWaterLevel() == WaterLevel.low));
      @*/
	void timeShift() {
		if (pumpRunning)
			env.lowerWaterLevel();
	}

    /*@
      @ public normal_behavior
      @ ensures ((this.countProblems() == 0) ==> pumpRunning);
      @ */
	void activatePump() {
        if(countProblems() == 0)
            pumpRunning = true;
	}

    /*@
      @ public normal_behavior
      @ ensures !pumpRunning;
      @ */
	void deactivatePump() {
		pumpRunning = false;
	}

    //needed for remodelling
    /*@
      @ public normal_behavior
      @ ensures \result >= 0 && \result == countProblems();
      @ assignable \strictly_nothing;
      @ */
    int countProblems(){
        return 0;
    }

    /*@
      @ public normal_behavior
      @ ensures \result == env.isMethaneLevelCritical();
      @ */
	boolean isMethaneAlarm() {
		return env.isMethaneLevelCritical();
	}

}