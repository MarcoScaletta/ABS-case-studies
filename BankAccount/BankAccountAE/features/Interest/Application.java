 class Application {
	Account account; // added to access the field
	
	//cannot be proven
	/*@
	 @ ensures (account.balance >= 0 ==> account.interest >= \old(account.interest)) 
	 @   && (account.balance <= 0 ==> account.interest <= \old(account.interest));
	 @*/
	void nextDay() {
		/*@ae_constraint 
				\disjoint(this.account,\dl_frame) &&
				\disjoint(this.account.balance,\dl_frame) &&
				\disjoint(this.account.interest,\dl_frame) &&
				\disjoint(this.account.interest,\dl_footprint); // new field
		@*/
		{;}
		/*@
          @ assignable \dl_frame;
          @ accessible  \dl_footprint;
          @ exceptional_behavior requires false;
          @ break_behavior requires false;
          @ continue_behavior requires false;
          @ return_behavior requires false;
          @ normal_behavior ensures true; // invariant
          @*/
        \abstract_statement Original;
		account.interest = account.calculateInterest() + account.interest;
	}

	// remodeled
	// PROVED
	/*@
	 @ ensures account.balance == \old(account.balance) + \old(account.interest) 
	 @   && account.interest == 0;
	 @*/
	void nextYear() {
		account.balance += account.interest;
		account.interest = 0;
	}

}