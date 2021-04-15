interface AccountI{

	/*@
	 @ requires amount >= 0;
	 @ ensures getBalance() >= amount <==> \result;
	 @*/
	boolean credit(int amount);

	/*@ ensures \result == getBalance();
	  @ assignable \strictly_nothing;
	  @*/
	int getBalance();
}

class Account implements AccountI{

	int balance;

	boolean credit(int amount) {
		return balance >= amount;
	}

	int getBalance(){
		return balance;
	}

}