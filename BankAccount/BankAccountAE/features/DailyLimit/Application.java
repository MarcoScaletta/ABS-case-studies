class Application {
	
	int withdraw = 0;
	
	Account account;

	/*@
	 @ ensures account.withdraw == 0;
	 @*/
	void nextDay() {

        /*@
          @ assignable \dl_frame;
          @ accessible  \dl_footprint;
          @ exceptional_behavior requires false;
          @ break_behavior requires false;
          @ continue_behavior requires false;
          @ return_behavior requires false;
          @ normal_behavior ensures true;
          @*/
        \abstract_statement Original;
		account.withdraw = 0;
	}

}