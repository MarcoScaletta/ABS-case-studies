class Account {


	boolean test_field = false;

	/*@
		@ requires this.test_field == false;
		@ ensures this.test_field == false;
		@*/
	void test() { 

	/*@ ae_constraint
		@     \disjoint(this.test_field,\dl_frame);
		@*/
		{ ; }
		
		/*@
			@ assignable \dl_frame;
			@ exceptional_behavior requires false;
			@ break_behavior requires false;
			@ continue_behavior requires false;
			@ return_behavior requires false;
			@ normal_behavior ensures true;
			@*/
		\abstract_statement Original;

	}

}
