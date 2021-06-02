
class BankAccount {

    int log;
    int balance;

    //@ghost int logAtL;
    //@ghost int balanceAtL;

    /*@
      @ ensures ((balanceAtL >= 0) ==> (log == logAtL*10 + 3)) && ((balanceAtL < 0) ==> (log == logAtL*10 + 4));
      @*/

    public void update(int x) {
        /*@
          @ assignable \dl_frame;
          @ accessible  \dl_footprint;
          @ exceptional_behavior requires false;
          @ break_behavior requires false;
          @ continue_behavior requires false;
          @ return_behavior requires false;
          @ normal_behavior ensures true;
          @*/
        \abstract_statement Original;
      //@set logAtL = log;
      //@set balanceAtL = balance;


        if(balance >= 0)
            log = log*10 + 3;
        if(balance < 0)
            log = log*10 + 4;
    }
}