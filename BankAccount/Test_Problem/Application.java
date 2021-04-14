 class Application {

	//@invariant \invariant_for(account);
	AccountI account;

    //@requires \disjoint(this.account, \dl_account_fields);
	//@ensures \old(account.getBalance()) > account.getBalance();
	void provable_complex_old() {
        /*@ ae_constraint \disjoint(\dl_frame, this.account) 
            && \disjoint(\dl_frame, \dl_account_fields) ;
        @*/
        { ; }
            /*@
        @ assignable \dl_frame;
        @ exceptional_behavior requires false;
        @ break_behavior requires false;
        @ continue_behavior requires false;
        @ return_behavior requires false;
        @ normal_behavior ensures \invariant_for(this);
        @*/
        \abstract_statement Original;
        account.setBalance(account.getBalance() - 1);
	}


}


