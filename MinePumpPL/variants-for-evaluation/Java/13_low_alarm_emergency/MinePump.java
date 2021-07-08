class MinePump {


    

    /*@
      @invariant \invariant_for(env) 
      @       && \disjoint(\dl_env_fields,systemActive) 
      @       && \disjoint(\dl_env_fields,pumpRunning) 
      @       && \disjoint(\dl_env_fields,env);
      @
      @*/


    boolean systemActive;
    boolean pumpRunning;
    EnvironmentI env;



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
      @ ensures !pumpRunning;
      @ assignable pumpRunning;
      @ */
	void deactivatePump() {
        pumpRunning = false;
	}


    /*@
      @ public normal_behavior
      @ ensures \result == !env.isLowWaterSensorDry() && \result == isLowWaterLevel();
      @ assignable \strictly_nothing;
      @ */
    boolean isLowWaterLevel() {
        return !env.isLowWaterSensorDry();
    }



    //@ghost boolean checkAtL;
    
    /*@
      @invariant \disjoint(\dl_env_fields,checkAtL);
      @*/
    
    /*@ 
      @ ensures checkAtL ==> !pumpRunning;
      @ */
    void low_timeShift() {
        timeShift();
        //@set checkAtL = systemActive && pumpRunning && isLowWaterLevel();
        if(systemActive && pumpRunning && isLowWaterLevel())
            deactivatePump();
    }

    /*@ public normal_behavior
      @ ensures \result == env.isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
      @ */
    boolean isMethaneAlarm() {
      return env.isMethaneLevelCritical();
    }


    /*@
      @ ensures (\old(systemActive) && \old(pumpRunning) && \old(this.isMethaneAlarm())) ==> !pumpRunning;
      @ */
    void low_alarm_timeShift() {
        if (systemActive && pumpRunning && isMethaneAlarm()) {
            deactivatePump();
        } else {
          low_timeShift();
        }
    }

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
    void low_alarm_emergency_timeShift() {
        low_alarm_timeShift();
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

	// LOW
    /*@
      @ public normal_behavior
      @ ensures \result == isLowWaterSensorDry();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isLowWaterSensorDry();
	//END LOW

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