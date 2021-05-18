

interface EnvironmentI {

	
	//@accessible \inv: \dl_env_fields;

	//TO_BE_FINISHED

	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == WaterLevel.high) ==> (this.getWaterLevel() == WaterLevel.normal) && 
	  @ 	    (\old(this.getWaterLevel()) == WaterLevel.normal) ==> (this.getWaterLevel() == WaterLevel.low);
	  @ assignable \dl_env_fields;
	  @ */
	void lowerWaterLevel();



	/*@ 
	  @ public normal_behavior
	  @ ensures (\old(this.getWaterLevel()) == WaterLevel.low) ==> (this.getWaterLevel() == WaterLevel.normal) &&
	  @ 	    (\old(this.getWaterLevel()) == WaterLevel.normal) ==> (this.getWaterLevel() == WaterLevel.high); 
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
	  @ ensures \result == getWaterLevel();
	  @ assignable \strictly_nothing;
	  @ */
	int getWaterLevel();

}

public class Environment implements EnvironmentI{

	//@invariant waterLevel >=0 && waterLevel <=2 && \subset(waterLevel,\dl_env_fields);

	int waterLevel = WaterLevel.normal;

	boolean methaneLevelCritical = false;

	void lowerWaterLevel() {
        if(waterLevel == WaterLevel.high)
			waterLevel = WaterLevel.normal;
        else if(waterLevel == WaterLevel.normal)
			waterLevel = WaterLevel.low;
	}

	void waterRise() {
        if(waterLevel == WaterLevel.low)
			waterLevel = WaterLevel.normal;
        else if(waterLevel == WaterLevel.normal)
			waterLevel = WaterLevel.high;
	}

	void changeMethaneLevel() {
		methaneLevelCritical = !methaneLevelCritical;
	}

	boolean isMethaneLevelCritical() {
		return methaneLevelCritical;
	}

	int getWaterLevel() {
		return waterLevel;
	}
}

interface WaterLevel {

   public static final int low = 0;
   public static final int normal = 1;
   public static final int high = 2;

}