class MinePump {
    
    //OK

    boolean pumpRunning;
    boolean systemActive;

    /*@
      @ requires !pumpRunning;
      @ ensures systemActive && !pumpRunning;
      @ */
    void startSystem() {
        systemActive = true;
    }
}