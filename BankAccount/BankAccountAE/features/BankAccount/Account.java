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

	final static int OVERDRAFT_LIMIT = 0;

	//@ invariant balance >= OVERDRAFT_LIMIT;
	int balance = 0;
	
	/*@
	 @ ensures (!\result ==> balance == \old(balance)) 
	 @   && (\result ==> balance == \old(balance) + x); 
	 @*/
	boolean update(int x) {
		int newBalance = balance + x;
		if (newBalance < OVERDRAFT_LIMIT)
			return false;
		balance = newBalance;
		return true;
	}

}