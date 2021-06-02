public class BankAccount{ 

    public int withdrawnTotal;

    /*@public normal_behavior
      @requires \disjoint(x,\dl_frame);
      @ensures (x < 0) ==> (this.withdrawnTotal == \old(this.withdrawnTotal) - x);
      @*/
    public void update(int x){
        if(x < 0)
        this.withdrawnTotal = this.withdrawnTotal - x;
        /*@ae_constraint 
                \disjoint(this.withdrawnTotal,\dl_frame) &&
                \disjoint(this.withdrawnTotal,\dl_footprint) &&
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
    }
}