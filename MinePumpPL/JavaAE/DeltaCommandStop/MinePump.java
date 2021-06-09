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
      @ ensures !pumpRunning && !systemActive;
      @ */
    void stopSystem() {
        if (pumpRunning) {
            deactivatePump();
        }
        systemActive = false;
    }
}