class Application {
	
	AccountI account; //added to access the field

  //TO BE VERIFIED
	/*
	 @ ensures account.getWithdraw() == 0;
	 @*/
	void nextDay() {

        /*@
          @ assignable \dl_frame;
          @ accessible  \dl_footprint;
          @ exceptional_behavior requires false;
          @ break_behavior requires false;
          @ continue_behavior requires false;
          @ return_behavior requires false;
          @ normal_behavior ensures account != null; // invariant
          @*/
        \abstract_statement Original;
		account.setWithdraw(0);
	}

}