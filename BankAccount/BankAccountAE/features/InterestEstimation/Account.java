
interface AccountI {

	/*@
	  @ public normal_behavior 
	  @ ensures (getBalance() >= 0 ==> \result >= 0) 
	  @      && (getBalance() <= 0 ==> \result <= 0) 
	  @      && \result==calculateInterest();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ calculateInterest();

	
	/*@ 
	  @ public normal_behavior 
	  @ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ getBalance();

	/*@
	  @ public normal_behavior
	  @ requires daysLeft >= 0;
	  @ ensures calculateInterest() >= 0 ==> \result >= getInterest();
	  @*/
	/*@ pure @*/ int estimatedInterest(int daysLeft);
	
	/*@ 
	  @ public normal_behavior 
	  @ ensures \result == getInterest();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ getInterest();
}


class Account implements AccountI{

	int interest;
	int balance;
	final static int INTEREST_RATE = 2;

	/*@ pure @*/ int estimatedInterest(int daysLeft) {
		return interest + daysLeft * calculateInterest();
	}

	int calculateInterest() {
		return balance * INTEREST_RATE / 36500;
	}
	
	int getBalance() {
		return this.balance;
	}
	
	int getInterest() {
		return this.interest;
	}
	
}