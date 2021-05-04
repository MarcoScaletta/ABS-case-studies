class Application {
	
	//@invariant \invariant_for(account);
	AccountI account; //added to access the field

  //Verified
	/*@requires \disjoint(this.account, \dl_account_fields) && \disjoint(this, \dl_account_fields);
	  @ensures account.getWithdraw() == 0;
	  @*/
	void nextDay() {
		/*@ae_constraint 
				\disjoint(this.account,\dl_frame);
		@*/
		{;}

    /*@
      @ assignable \dl_frame;
      @ accessible  \dl_footprint;
      @ exceptional_behavior requires false;
      @ break_behavior requires false;
      @ continue_behavior requires false;
      @ return_behavior requires false;
      @ normal_behavior ensures account != null && \invariant_for(account); // invariant
      @*/
    \abstract_statement Original;
		account.setWithdraw(0);
	}

}