
public class Calculations {
	//Trajectory graphs (Output)
		//Vertical flight path
		//Horizontal variation
	//Flight time(s) (Output)
	//Elevation(m) (User Input)
	//Wind speed(km/h) (User Input)
	//Wind angle N/S/E/W(°) (User Input)
	//Muzzle velocity(m/s)(Based on User Input Caliber)
	//Range(m) (User Input)
	//Variation Left/Right of target (m) (Output)
	//How far to correct (°) (Output)
	//Angle N/S/E/W	(°) (Input)
	public static void calculate (double range,double velocity,double elevation)
	{
		@SuppressWarnings("resource")
		/*
		System.out.println("Wind Speed (km/h):");
		Double d=
		System.out.println("Wind Direction (°):");
		Double e=
		*/
		//horizontal velocity
		double v=Math.sqrt((velocity*velocity)+2*-9.81*elevation);
		//flight time
		double t=(range/velocity);
		System.out.println("Time to Target: " +t);
		//change in height
			//allows for confirmation of value utilized relative to input value
		double y=(0.5*-9.81*(t*t)+velocity*t);
		//firing angle
		double cos=(Math.acos((velocity*t)/range));
		double ang=90-((cos*180)/(Math.PI));
		System.out.println("firing angle" +ang);
	}
}
