 class Application {

	//@invariant \invariant_for(account);
	AccountI account;
  private int oldGetBalance;

  //@requires \disjoint(this.oldGetBalance, \dl_account_fields);
	//@ensures \old(account.getBalance()) == account.getBalance();
	void unprovable_regular_old() {
    /*@ ae_constraint \disjoint(\dl_frame, this.account) 
        && \disjoint(\dl_frame, this) 
        && \disjoint(\dl_frame, this.oldGetBalance)
        && \disjoint(\dl_frame, \dl_account_fields) ;
    @*/
    { ; }
		/*@
      @ assignable \dl_frame;
      @ exceptional_behavior requires false;
      @ break_behavior requires false;
      @ continue_behavior requires false;
      @ return_behavior requires false;
      @ normal_behavior ensures \invariant_for(this);
      @*/
    \abstract_statement Original;
	}


  //@requires \disjoint(this.oldGetBalance, \dl_account_fields);
	//@ensures oldGetBalance == account.getBalance();
	void unprovable_workaround() {
    oldGetBalance = account.getBalance(); 
    /*@ ae_constraint \disjoint(\dl_frame, this.account) 
        && \disjoint(\dl_frame, this) 
        && \disjoint(\dl_frame, this.oldGetBalance)
        && \disjoint(\dl_frame, \dl_account_fields) ;
    @*/
    { ; }
		/*@
      @ assignable \dl_frame;
      @ exceptional_behavior requires false;
      @ break_behavior requires false;
      @ continue_behavior requires false;
      @ return_behavior requires false;
      @ normal_behavior ensures \invariant_for(this);
      @*/
    \abstract_statement Original;
	}

}


