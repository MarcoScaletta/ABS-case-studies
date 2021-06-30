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



    //@ghost boolean checkAtL;
    
    /*@
      @invariant \disjoint(\dl_env_fields,checkAtL);
    
    /*@ 
      @ ensures checkAtL ==> pumpRunning;
      @ */
    void timeShift() {
          
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
      //@set checkAtL = systemActive && !pumpRunning && countProblems() == 0 && isHighWaterLevel();
      if(systemActive && !pumpRunning && isHighWaterLevel())
          activatePump();
    }

}


interface EnvironmentI {
    	
	//@accessible \inv: \dl_env_fields;

    /*@
      @ public normal_behavior
      @ ensures \result == isHighWaterSensorDry();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isHighWaterSensorDry();
}