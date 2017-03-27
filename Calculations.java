
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
	public static void main (String[] args)
	{
		@SuppressWarnings("resource")
		java.util.Scanner input = new java.util.Scanner (System.in);
		System.out.println("Range (m):");
		Double a=Double.parseDouble(input.nextLine());
		System.out.println("Muzzle Velocity (m/s):");
		Double b=Double.parseDouble(input.nextLine());
		System.out.println("Elevation (m):");
		Double c=Double.parseDouble(input.nextLine());	
		System.out.println("Wind Speed (km/h):");
		Double d=Double.parseDouble(input.nextLine());
		System.out.println("Wind Direction (°):");
		Double e=Double.parseDouble(input.nextLine());
		//horizontal velocity
		double v=Math.sqrt((b*b)+2*-9.81*c);
		//flight time
		double t=((v-b)/-9.81);
		System.out.println("Time to Target: " +t);
		//change in height
			//allows for confirmation of value utilized relative to input value
		double y=(0.5*-9.81*(t*t)+b*t);
		//firing angle
		double cos=(Math.acos((b*t)/a));
		double ang=(cos*180);
		double fang=(ang/(Math.PI));
		double ffang=(90-fang);
		System.out.println("firing angle" +ffang);	
	}
}
