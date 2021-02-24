class Account {

	final static int INTEREST_RATE = 2;

	int interest = 0; //added
	int balance;

	/*@ assignable \nothing;
	  @ ensures (balance >= 0 ==> \result >= 0) 
	  @   && (balance <= 0 ==> \result <= 0);
	  @*/
	/*@ pure helper @*/ int calculateInterest() {
		return balance * INTEREST_RATE / 36500;
	}

}