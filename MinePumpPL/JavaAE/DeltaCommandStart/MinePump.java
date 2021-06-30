class MinePump {
    
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