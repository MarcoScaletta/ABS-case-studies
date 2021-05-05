 class Application {

	/*@invariant   \invariant_for(account) 
	  @			&& \disjoint(this.account, \dl_account_fields)
	  @			&& \disjoint(this.getInterestAtOriginal, \dl_account_fields)
	  @			&& \disjoint(this.getBalanceAtOriginal, \dl_account_fields);
	  @
	  @*/
	AccountI account;

	//@ghost int getInterestAtOriginal;
	//@ghost int getBalanceAtOriginal;

	// remodelled to make it fulfill COBP

    /*@ 
	  @ public normal_behavior
	  @ ensures (getBalanceAtOriginal >= 0 ==> account.getInterest() >= this.getInterestAtOriginal) 
	  @      && (getBalanceAtOriginal <= 0 ==> account.getInterest() <= this.getInterestAtOriginal);
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
          @ normal_behavior ensures \invariant_for(this);
          @*/
        \abstract_statement Original;
		//@set getInterestAtOriginal=account.getInterest();
		//@set getBalanceAtOriginal=account.getBalance();
		account.setInterest(account.calculateInterest() + account.getInterest());
	}


	// remodeled
	/*@
	  @ public normal_behavior
	  @ ensures account.getBalance() == \old(account).getBalance() + \old(account).getInterest() 
	  @      && account.getInterest() == 0;
	  @*/
	void nextYear() {
		account.setBalance(account.getBalance() + account.getInterest());
		account.setInterest(0);
	}

}