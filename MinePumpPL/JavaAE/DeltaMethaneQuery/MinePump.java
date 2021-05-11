class MinePump {
    // OK

    // this method is core.MinePump.countProblems;
    /*@
      @ ensures \result >= 0;
      @ */
    int countProblemsLiskovPrevious(){ 
        return 0;
    }

    // proven using Liskov Behavioral Principle
    /*@
      @ ensures \result >= 1;
      @ */
    int countProblems(){
        return countProblemsLiskovPrevious() + 1;
    }


}