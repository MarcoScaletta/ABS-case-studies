class MinePump {
    
    //OK

    boolean pumpRunning;
    boolean systemActive;

    /*@
      @ ensures !pumpRunning;
      @ */
	void deactivatePump() {
		pumpRunning = false;
	}


    /*@
      @ requires !pumpRunning;
      @ ensures !pumpRunning && !systemActive;
      @ */
    void stopSystem() {
        if (pumpRunning) {
            deactivatePump();
        }
        systemActive = false;
    }
}