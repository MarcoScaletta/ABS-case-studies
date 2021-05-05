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


class Account {

	//@ invariant balance >= overdraftLimit();
	int balance = 0;
	
	//@ensures \result <= 0;
	int overdraftLimit(){
		return 0;
	}

	boolean update(int x) {
		int newBalance = balance + x;
		if (newBalance < overdraftLimit())
			return false;
		balance = newBalance;
		return true;
	}

}