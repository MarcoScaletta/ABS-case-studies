interface AccountI{
	
	/*@ 
	  @ public normal_behavior
	  @ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ getBalance();

	
	/*@
	  @ public normal_behavior
	  @ ensures (!\result ==> getBalance() == \old(getBalance())) 
	  @       && (\result ==> getBalance() == \old(getBalance()) + x); 
	  @*/
	boolean update(int x);
}


class Account implements AccountI {

	//@ invariant balance >= overdraftLimit();
	int balance = 0;
	
	/*@ 
	  @ public normal_behavior
	  @ ensures \result <= 0 && \result==overdraftLimit();
	  @ assignable \strictly_nothing;
	  @*/
	int overdraftLimit(){
		return 0;
	}

	
	/*@ 
	  @ public normal_behavior
	  @ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @*/
	int getBalance(){
		return this.balance;
	}

	boolean update(int x) {
		int newBalance = balance + x;
		if (newBalance < overdraftLimit())
			return false;
		balance = newBalance;
		return true;
	}

}