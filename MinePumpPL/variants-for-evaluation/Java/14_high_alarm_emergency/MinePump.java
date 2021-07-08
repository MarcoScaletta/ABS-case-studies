class MinePump {
    //to be finished
    

    

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
      @ ensures \result >= 0 && \result == countProblems();
      @ assignable \strictly_nothing;
      @ */
    int countProblems(){
        return 0;
    }

    /*@
      @ public normal_behavior
      @ ensures (\old(countProblems()) == 0) ==> pumpRunning;
      @ assignable pumpRunning;
      @ */
	void activatePump() {
        if(countProblems() == 0)
            pumpRunning = true;
	}


    /*@
      @ public normal_behavior
      @ ensures \result == !env.isHighWaterSensorDry() && \result == isHighWaterLevel();
      @ assignable \strictly_nothing;
      @ */
    boolean isHighWaterLevel() {
        return !env.isHighWaterSensorDry();
    }


    /*@
      @ public normal_behavior
      @ ensures !pumpRunning;
      @ assignable pumpRunning;
      @ */
	void deactivatePump() {
        pumpRunning = false;
	}


    //@ghost boolean checkAtL;
    
    /*@
      @invariant \disjoint(\dl_env_fields,checkAtL);
      @*/
    
    /*@ 
      @ ensures checkAtL ==> pumpRunning;
      @ */
    void high_timeShift() {
          
        timeShift();
      //@set checkAtL = systemActive && !pumpRunning && countProblems() == 0 && isHighWaterLevel();
      if(systemActive && !pumpRunning && isHighWaterLevel())
          activatePump();
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
    void high_alarm_timeShift() {
        if (systemActive && pumpRunning && isMethaneAlarm()) {
            deactivatePump();
        } else {
          high_timeShift();
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
    void high_alarm_emergency_timeShift() {
        high_alarm_timeShift();
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

	// HIGH
    /*@
      @ public normal_behavior
      @ ensures \result == isHighWaterSensorDry();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isHighWaterSensorDry();
	//END HIGH

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