 class Application {

	//@invariant \invariant_for(account) && \disjoint(field, \dl_account_fields);
	AccountI account; // added to access the field
	int field = 0;

	//cannot be proven
	// /*@
	//  @ ensures (account.getBalance() >= 0 ==> account.getInterest() >= \old(account.getInterest())) 
	//  @   && (account.getBalance() <= 0 ==> account.getInterest() <= \old(account.getInterest()));
	//  @*/

	//@requires \disjoint(\dl_account_fields, field) ;
	//@ requires \disjoint(\dl_AccountI_frame, this.account);
	// //@requires \disjoint(\dl_account_inv_footprint, field);
	//@ensures \old(account.getInterest()) ==  field;
	void nextDay() {
		//the problem is that we do not require that ghostOldGetInterest is not referred in the \invariant_for(account)
		field = account.getInterest();
		/*@ae_constraint 
				\disjoint(\dl_account_fields,\dl_frame) &&
				\disjoint(this.field,\dl_frame) &&
				\disjoint(this,\dl_frame) &&
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
		// account.setInterest(0);
		// account.setInterest(account.getInterest());
		//account.setInterest(account.calculateInterest() + account.getInterest());
	}

	// remodeled
	// PROVED
	/*@
	 @ ensures account.getBalance() == \old(account).getBalance() + \old(account).getInterest()
	 @   && account.getInterest() == 0;
	 @*/
	void nextYear() {
		account.setBalance(account.getBalance() + account.getInterest());
		account.setInterest(0);
	}

}