interface AccountI{


	/*@ ensures \result == getWithdraw();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ getWithdraw();
	
	/*@ public normal_behavior 
	  @ ensures \invariant_for(this) && getWithdraw() == newWithdraw;
	  @*/
	void setWithdraw(int newWithdraw);
}

class Account implements AccountI{

	int DAILY_LIMIT = -1000;
	
	//@ invariant withdraw >= DAILY_LIMIT;
	int withdraw = 0;

	
	int /*@ pure @*/ getWithdraw(){
		return withdraw;
	}

	
	void setWithdraw(int newWithdraw){
		withdraw = newWithdraw;
	}

	// PROVED
	/*@
	 @ ensures (\result ==> withdraw <= \old(withdraw)) && (!\result ==> withdraw == \old(withdraw));
	 @*/
	boolean update(int x, boolean a) {
		
		int newWithdraw = withdraw;	
		/*@ae_constraint 
				\disjoint(this.DAILY_LIMIT,\dl_frame) &&
				\disjoint(this.DAILY_LIMIT,\dl_footprint) &&
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
			if (newWithdraw < DAILY_LIMIT) 
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