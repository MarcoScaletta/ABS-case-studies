
class BankAccount {
    int log;
    //@ghost int oldX;

    /*@
      @ ensures ((oldX > 0) ==> (log == \old(log)*10 + 1)) && ((oldX < 0) ==> (log == \old(log)*10 + 2));
      @*/

    public void update(int x) {
      //@set oldX = x;

      if(x > 0)
          log = log*10 + 1;
      if(x < 0)
          log = log*10 + 2;

        /*@ ae_constraint 
                \disjoint(x,\dl_frame)&&
                \disjoint(log,\dl_frame)&&
                \disjoint(log,\dl_footprint)&&
                \disjoint(oldX,\dl_frame);
          @*/
        {;}
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
    }
}