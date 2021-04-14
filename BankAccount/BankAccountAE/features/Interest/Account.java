
interface AccountI{

	//@accessible \inv: \dl_account_fields;

	/*@
	  @ ensures (getBalance() >= 0 ==> \result >= 0) 
	  @   && (getBalance() <= 0 ==> \result <= 0) && \result==calculateInterest();
	  @ assignable \strictly_nothing;
	  @ accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ calculateInterest();
	
	
	/*@ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @ accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ getBalance();
	
	/*@ public normal_behavior 
	  @ ensures \invariant_for(this);// \invariant_for(this) && getBalance() == newBalance;
	  @ assignable \dl_account_fields;
	  @*/
	void setBalance(int newBalance);

	/*@ensures \result == getInterest();
	  @assignable \strictly_nothing;
	  @accessible \dl_account_fields;
	  @*/
	int /*@ pure @*/ getInterest();

	/*@ requires this.\inv;
	  @ ensures this.getInterest() == newInterest  && this.\inv;
	  @ assignable \dl_account_fields;
	  @*/
	void setInterest(int newInterest);
}


final class Account implements AccountI{

	final static int INTEREST_RATE = 2;


	private final AccountI a;
	// @requires \invariant_for(a);
	//@ensures true;	
	void testMethod(){
		a.setBalance(0);
	}

	int interest = 0; //added
	int balance;

	int calculateInterest() {
		return balance * INTEREST_RATE / 36500;
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