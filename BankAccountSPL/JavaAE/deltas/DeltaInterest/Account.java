
interface AccountI{

	//@accessible \inv: \dl_account_fields;

	/*@
	  @ public normal_behavior 
	  @ ensures (this.getBalance() >= 0 ==> \result >= 0) 
	  @      && (this.getBalance() <= 0 ==> \result <= 0) 
	  @      && \result==calculateInterest();
	  @ assignable \strictly_nothing;
	  @ accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ calculateInterest();
	
	
	/*@ 
	  @ public normal_behavior 
	  @ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @ accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ getBalance();
	
	/*@ public normal_behavior 
	  @ ensures \invariant_for(this) && getBalance() == newBalance;
	  @ assignable \dl_account_fields;
	  @*/
	void setBalance(int newBalance);

	/*@ 
	  @ public normal_behavior 
	  @ ensures \result == getInterest();
	  @ assignable \strictly_nothing;
	  @ accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ getInterest();

	
	/*@ 
	  @ public normal_behavior 
	  @ requires this.\inv;
	  @ ensures this.getInterest() == newInterest  && this.\inv;
	  @ assignable \dl_account_fields;
	  @*/
	void setInterest(int newInterest);
}


final class Account implements AccountI{

	//@invariant \subset(this.interest,\dl_account_fields) && \subset(this.balance, \dl_account_fields);

	final static int INTEREST_RATE = 2 / 36500;

	int interest = 0; //added
	int balance;

	int calculateInterest() {
		return this.balance * INTEREST_RATE;
	}

	int getBalance() {
		return this.balance;
	}
	
	void setBalance(int newBalance) {
		this.balance = newBalance;
	}

	int getInterest() {
		return this.interest;
	}

	void setInterest(int newInterest) {
		this.interest = newInterest;
	}

}