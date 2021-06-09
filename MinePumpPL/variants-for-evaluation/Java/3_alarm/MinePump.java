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
      @ ensures (\old(systemActive) && \old(pumpRunning) && \old(this.isMethaneAlarm())) ==> !pumpRunning;
      @ */
    void alarm_timeShift() {
        if (systemActive && pumpRunning && isMethaneAlarm()) {
            deactivatePump();
        } else {
          timeShift();
        }
    }

}

interface EnvironmentI {
	
	//@accessible \inv: \dl_env_fields;

	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == WaterLevel.high) ==> (this.getWaterLevel() == WaterLevel.normal) && 
	  @ 	    (\old(this.getWaterLevel()) == WaterLevel.normal) ==> (this.getWaterLevel() == WaterLevel.low);
	  @ assignable \dl_env_fields;
	  @ */
	void lowerWaterLevel();



	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == WaterLevel.low) ==> (this.getWaterLevel() == WaterLevel.normal) &&
	  @ 	    (\old(this.getWaterLevel()) == WaterLevel.normal) ==> (this.getWaterLevel() == WaterLevel.high); 
	  @ */
	void waterRise();

	/*@
	  @ public normal_behavior
	  @ ensures this.isMethaneLevelCritical() == !\old(this.isMethaneLevelCritical());
	  @ */
	void changeMethaneLevel();

	/*@
	  @ public normal_behavior
	  @ ensures \result == isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
	  @ */
	boolean isMethaneLevelCritical();

	/*@
	  @ public normal_behavior
	  @ ensures \result == getWaterLevel();
	  @ assignable \strictly_nothing;
	  @ */
	int getWaterLevel();
	/*@
	  @ public normal_behavior
	  @ ensures \result == isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
	  @ */
	boolean isMethaneLevelCritical();
}


interface WaterLevel {

   public static final int low = 0;
   public static final int normal = 1;
   public static final int high = 2;

}