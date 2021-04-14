
interface AccountI{

	//@accessible \inv: \dl_account_fields;

	//@assignable \strictly_nothing;
	//@accessible \dl_account_fields;
	//@ensures \result == getBalance();
	int /*@ pure @*/ getBalance();


	//@assignable \dl_account_fields;
	//@accessible \dl_account_fields;
	//@ensures this.getBalance() == newBalance;
	void setBalance(int newBalance);
	
}