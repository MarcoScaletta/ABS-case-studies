

interface EnvironmentI {

	//TO_BE_FINISHED

	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == Environment.WaterLevel.high) ==> (this.getWaterLevel() == Environment.WaterLevel.normal) || 
	  @ 	    (\old(this.getWaterLevel()) == Environment.WaterLevel.normal) ==> (this.getWaterLevel() == Environment.WaterLevel.low); 
	  @ */
	void lowerWaterLevel();



	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == Environment.WaterLevel.normal) ==> (this.getWaterLevel() == Environment.WaterLevel.high) || 
	  @ 	    (\old(this.getWaterLevel()) == Environment.WaterLevel.low) ==> (this.getWaterLevel() == Environment.WaterLevel.normal); 
	  @ */
	void waterRise();

	/*@
	  @ public normal_behavior
	  @ ensures this.isMethaneLevelCritical() == !\old(this.isMethaneLevelCritical());
	  @ */
	void changeMethaneLevel();

	/*@
	  @ public normal_behavior
	  @ ensures \result == isMethaneLevelCritical();
	  @ assignable \strictly_nothing;
	  @ */
	boolean isMethaneLevelCritical();

	/*@
	  @ public normal_behavior
	  @ ensures \result <==> (waterLevel1 != waterLevel2 && (waterLevel1 == Environment.WaterLevel.high || waterLevel2 == Environment.WaterLevel.low));
	  @ */
	boolean higher(Environment.WaterLevel waterLevel1, Environment.WaterLevel waterLevel2);

	/*@
	  @ public normal_behavior
	  @ ensures \result == this.getWaterLevel();
	  @ assignable \strictly_nothing;
	  @ */
	Environment.WaterLevel getWaterLevel();

}

public class Environment implements EnvironmentI{

	WaterLevel waterLevel = WaterLevel.normal;

	boolean methaneLevelCritical = false;

	void lowerWaterLevel() {
        if(getWaterLevel() == WaterLevel.high)
			setWaterLevel(WaterLevel.normal);
        else if(getWaterLevel() == WaterLevel.normal)
			setWaterLevel(WaterLevel.low);
	}

	void waterRise() {
        if(getWaterLevel() == WaterLevel.low)
			setWaterLevel(WaterLevel.normal);
        else if(getWaterLevel() == WaterLevel.normal)
			setWaterLevel(WaterLevel.high);
	}

	void changeMethaneLevel() {
		methaneLevelCritical = !methaneLevelCritical;
	}

	boolean isMethaneLevelCritical() {
		return methaneLevelCritical;
	}

	boolean higher(WaterLevel waterLevel1, WaterLevel waterLevel2){
        return waterLevel1 != waterLevel2 && (waterLevel1 == WaterLevel.high || waterLevel2 == WaterLevel.low);
    }

	/*@
	  @ public normal_behavior
	  @ ensures \result == waterLevel;
	  @ */
	WaterLevel getWaterLevel() {
		return waterLevel;
	}

	/*@
	  @ public normal_behavior
	  @ ensures getWaterLevel() == newWaterLevel;
	  @ */
	WaterLevel setWaterLevel(WaterLevel newWaterLevel) {
		waterLevel = newWaterLevel;
	}

	public static enum WaterLevel {
		low, normal, high;
	}

}