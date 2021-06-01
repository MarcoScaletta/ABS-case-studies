
class BankAccount {
    int balance;
    int withdrawnTotal;  
    int feeOverLimit;
    int limit;
    //@ghost int balanceAtL;

    /*@ public normal_behavior
      @requires \disjoint(x,\dl_frame);
      @ensures (x < 0 && withdrawnTotal > limit) ==> (balance == balanceAtL - feeOverLimit);
      @*/

    public void update(int x) {
        /*@ ae_constraint 
                \disjoint(feeOverLimit,\dl_frame) &&
                \disjoint(feeOverLimit,\dl_footprint) &&
                \disjoint(limit,\dl_frame) &&
                \disjoint(limit,\dl_footprint) &&
                \disjoint(x,\dl_frame);
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
        //@ set balanceAtL = balance;
        if(x < 0 && withdrawnTotal > limit)
            balance -= feeOverLimit;
    }
}