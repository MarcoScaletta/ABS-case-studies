class Application {
	
	//@invariant \invariant_for(account) && \disjoint(this.account, \dl_account_fields);
	AccountI account; //added to access the field

  
	/*@
	  @ public normal_behavior
	  @ ensures account.getWithdraw() == 0;
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