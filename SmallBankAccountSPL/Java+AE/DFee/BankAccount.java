public class BankAccount{

    public int balance;
    public int fee;

    //@ghost int balanceAtL;
    //@ghost int oldX;

    /*@
      @public normal_behavior
      @ensures (oldX < 0) ==> (balance == balanceAtL - fee); 
     */
    public void update(int x){
        //@set oldX = x;

        /*@ae_constraint 
                \disjoint(this.fee,\dl_frame) &&
                \disjoint(this.fee,\dl_footprint) &&
                \disjoint(x,\dl_frame)&&
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
          //@set balanceAtL = balance;
          if(x < 0)
            this.balance = this.balance - this.fee;
      }
}