interface AccountI{

	/*@ public normal_behavior
	  @ ensures \result == getWithdraw();
	  @ assignable \strictly_nothing;
	  @*/
	int getWithdraw();
	
	/*@ public normal_behavior
	  @ requires newWithdraw >= this.getDailyLimit(); //precondition necessary for the invariant
	  @ ensures getWithdraw() == newWithdraw;
	  @ assignable \dl_account_fields;
	  @*/
	void setWithdraw(int newWithdraw);

	/*@ public normal_behavior 
	  @ ensures \result <= -1000;
	  @ assignable \strictly_nothing;
	  @ accessible \nothing;
	  @*/
	int getDailyLimit();
}

class Account implements AccountI{

	//@invariant withdraw >= getDailyLimit() && \subset(this.withdraw, \dl_account_fields);

	int withdraw = 0;
	
	int getWithdraw(){
		return withdraw;
	}
	
	void setWithdraw(int newWithdraw){
		withdraw = newWithdraw;
	}
	
	int getDailyLimit(){
		return -1000;
	}

	// PROVED
	/*@
	 @ ensures (\result ==> withdraw <= \old(withdraw)) && (!\result ==> withdraw == \old(withdraw));
	 @*/
	boolean update(int x, boolean a) {
		
		int newWithdraw = withdraw;	
		/*@ae_constraint 
				\disjoint(this.withdraw,\dl_frame) &&
				\disjoint(this.withdraw,\dl_footprint) &&
				\disjoint(a,\dl_frame) &&
				\disjoint(a,\dl_footprint) &&
				\disjoint(x,\dl_frame) &&
				\disjoint(newWithdraw,\dl_frame) &&
				\disjoint(newWithdraw,\dl_footprint);
		@*/
		{;}

		if (x < 0)  {
			newWithdraw += x;
			if (newWithdraw < getDailyLimit()) 
				return false;
		}
		
		boolean originalResult;
        /*@
          @ assignable \hasTo(originalResult);
          @ accessible  \dl_footprint;
          @ exceptional_behavior requires false;
          @ break_behavior requires false;
          @ continue_behavior requires false;
          @ return_behavior requires false;
          @ normal_behavior ensures true;
          @*/
		\abstract_statement Original;
		
		if (!originalResult)
			return false;
		withdraw = newWithdraw;
		return true;
	}

		
}