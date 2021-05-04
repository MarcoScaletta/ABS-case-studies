interface AccountI{
	
	/*@ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @*/
	int /*@ pure @*/ getBalance();

	
	/*@
	 @ ensures (!\result ==> getBalance() == \old(getBalance())) 
	 @   && (\result ==> getBalance() == \old(getBalance()) + x); 
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

	/*@
	 @ ensures (!\result ==> balance == \old(balance)) 
	 @   && (\result ==> balance == \old(balance) + x); 
	 @*/
	boolean update(int x) {
		int newBalance = balance + x;
		if (newBalance < overdraftLimit())
			return false;
		balance = newBalance;
		return true;
	}

}