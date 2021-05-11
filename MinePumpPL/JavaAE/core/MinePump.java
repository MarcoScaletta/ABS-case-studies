class MinePump {

	boolean pumpRunning = false;

	boolean systemActive = true;


	EnvironmentI env;

	public MinePump(EnvironmentI env) {
		super();
		this.env = env;
	}

    //remodelled
    /*@
      @ ensures pumpRunning ==> (env.higher(\old(env.getWaterLevel()), Environment.WaterLevel.low) ==> (env.higher(\old(env.getWaterLevel()), env.getWaterLevel())));
      @*/
	void timeShift() {
		if (pumpRunning)
			env.lowerWaterLevel();
	}

    /*@
      @ ensures (countProblems() == 0) ==> pumpRunning;
      @ */
	void activatePump() {
        if(countProblems() == 0)
            pumpRunning = true;
	}

    /*@
      @ ensures !pumpRunning;
      @ */
	void deactivatePump() {
		pumpRunning = false;
	}

    //needed for remodelling
    /*@
      @ ensures \result >= 0;
      @ */
    int countProblems(){
        return 0;
    }

    /*@
      @ ensures \result == env.isMethaneLevelCritical();
      @ */
	boolean isMethaneAlarm() {
		return env.isMethaneLevelCritical();
	}

	EnvironmentI getEnv() {
		return env;
	}
}