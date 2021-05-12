class MinePump {
    // OK
    
    //@invariant \invariant_for(env);


    EnvironmentI env;

    // this method is core.MinePump.countProblems;
    /*@
      @ ensures \result >= 0;
      @ */
    int countProblemsLiskovPrevious(){ 
        return 0;
    }

    // proven using Liskov Behavioral Principle
    /*@
      @ ensures isMethaneAlarm() ==> \result >= 1;
      @ */
    int countProblems(){
        int count = countProblemsLiskovPrevious();
        if(isMethaneAlarm())
            count = count + 1;
        return count;
    }


    /*@
      @ public normal_behavior
      @ ensures \result == env.isMethaneLevelCritical();
      @ */
    boolean isMethaneAlarm() {
        return env.isMethaneLevelCritical();
	}


}



interface EnvironmentI {

	/*@
	  @ public normal_behavior
	  @ ensures \result == isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
	  @ */
	boolean isMethaneLevelCritical();

}