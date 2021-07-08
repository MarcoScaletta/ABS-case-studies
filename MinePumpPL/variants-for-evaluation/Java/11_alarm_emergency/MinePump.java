class MinePump {

    //OK 
    /*@
      @invariant \invariant_for(env) 
      @       && \disjoint(\dl_env_fields,systemActive) 
      @       && \disjoint(\dl_env_fields,pumpRunning) 
      @       && \disjoint(\dl_env_fields,checkAtL) 
      @       && \disjoint(\dl_env_fields,env);
      @
      @*/

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

	//@ghost boolean checkAtL;

	/*@ 
      @ public normal_behavior
      @ ensures \result == env.isEmergency() && \result == isEmergency();
      @ assignable \strictly_nothing;
      @ */
    boolean isEmergency() {
        return env.isEmergency();
    }



    /*@ 
      @ ensures checkAtL ==> !pumpRunning;
      @ */
    void alarm_emergency_timeShift() {
        alarm_timeShift();
        //@set checkAtL = systemActive && pumpRunning && isEmergency();
        if(systemActive && pumpRunning && isEmergency())
            deactivatePump();
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


	// EMERGENCY
    /*@
      @ public normal_behavior
      @ ensures \result == isEmergency();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isEmergency();
	//END EMERGENCY  
}


interface WaterLevel {

   public static final int low = 0;
   public static final int normal = 1;
   public static final int high = 2;

}