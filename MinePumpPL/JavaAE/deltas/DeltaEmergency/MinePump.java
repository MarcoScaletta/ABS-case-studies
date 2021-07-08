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
      @ ensures !pumpRunning;
      @ assignable pumpRunning;
      @ */
	void deactivatePump() {
        pumpRunning = false;
	}


    /*@
      @ public normal_behavior
      @ ensures \result == env.isEmergency() && \result == isEmergency();
      @ assignable \strictly_nothing;
      @ */
    boolean isEmergency() {
        return env.isEmergency();
    }

    //@ghost boolean checkAtL;
    
    /*@
      @invariant \disjoint(\dl_env_fields,checkAtL);
      @*/
    
    /*@ 
      @ ensures checkAtL ==> !pumpRunning;
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
      //@set checkAtL = systemActive && pumpRunning && isEmergency();
      if(systemActive && pumpRunning && isEmergency())
          deactivatePump();
    }

}


interface EnvironmentI {
    	
	//@accessible \inv: \dl_env_fields;

    /*@
      @ public normal_behavior
      @ ensures \result == isEmergency();
      @ assignable \strictly_nothing;
      @ accessible \dl_env_fields;
      @ */
    boolean isEmergency();
}