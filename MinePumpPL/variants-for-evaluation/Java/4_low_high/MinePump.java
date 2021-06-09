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
      @invariant \disjoint(\dl_env_fields,checkAtL);
      @*/
    
    /*@ 
      @ ensures checkAtL ==> pumpRunning;
      @ */
    void low_high_timeShift() {
          
      timeShift();
      //@set checkAtL = systemActive && !pumpRunning && countProblems() == 0 && isHighWaterLevel();
      if(systemActive && !pumpRunning && isHighWaterLevel())
          activatePump();
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

	// HIGH
    /*@
      @ public normal_behavior
      @ ensures \result == isHighWaterSensorDry();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isHighWaterSensorDry();
	//END HIGH
}

interface WaterLevel {

   public static final int low = 0;
   public static final int normal = 1;
   public static final int high = 2;

}