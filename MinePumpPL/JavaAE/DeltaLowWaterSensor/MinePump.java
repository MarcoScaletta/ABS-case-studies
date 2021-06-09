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


    // comment from here to try the expensive version of timeShift (expensive_timeShift)

    //@ghost boolean checkAtL; //ghost variables used in cheaper_timeShift
    
    /*@
      @invariant \disjoint(\dl_env_fields,checkAtL); //invariant for ghost variable used in cheaper_timeShift
      @*/
    
    /*@ 
      @ ensures checkAtL ==> !pumpRunning;
      @ */
    void timeShift() { //cheap version, nodes 9.899
          
      /*@ae_constraint 
          \disjoint(this.env,\dl_frame);
      @*/
      {;}

      /*@
      @ assignable \dl_frame;
      @ accessible  \dl_footprint;
      @ exceptional_behavior requires false;
      @ break_behavior requires false;
      @ continue_behavior requires false;
      @ return_behavior requires false;
      @ normal_behavior ensures \invariant_for(this);
      @*/
      \abstract_statement Original;
      //@set checkAtL = systemActive && pumpRunning && isLowWaterLevel();
      if(systemActive && pumpRunning && isLowWaterLevel())
          deactivatePump();
    }

}


interface EnvironmentI {
    	
	//@accessible \inv: \dl_env_fields;

    /*@
      @ public normal_behavior
      @ ensures \result == isLowWaterSensorDry();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isLowWaterSensorDry();
}