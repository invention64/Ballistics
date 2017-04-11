
public class Calculations {
	//Trajectory graphs (Output)
		//Vertical flight path
		//Horizontal variation
	//Flight time(s) (Output)
	//Elevation(m) (User Input)
	//Wind speed(km/h) (User Input)
	//Wind angle N/S/E/W(?) (User Input)
	//Muzzle velocity(m/s)(Based on User Input Caliber)
	//Range(m) (User Input)
	//Variation Left/Right of target (m) (Output)
	//How far to correct (?) (Output)
	//Angle N/S/E/W	(?) (Input)
	public static double[] calculate (double velocity,double range,double elevation, double wspeed, double wangle, double compangle)
	{
		@SuppressWarnings("resource")
		//horizontal velocity
		double wdiff=0;
		double windage=0;
		double v = (Math.pow(velocity,2))-((2.0*9.81*elevation));
		double v2= Math.sqrt(v);
		//flight time
		double t=range/(velocity-(velocity-v2));
		//change in height
			//allows for confirmation of value utilized relative to input value
		double y=(0.5*-9.81*(t*t)+velocity*t);
		//firing angle
		double cos=(Math.acos(range/(velocity*t)));
		double ang=(cos*180);
		double fang=(ang/(Math.PI));
		//windage
		double diff=wangle-compangle;
		if(diff==90 || compangle-wangle==90) {
			windage = wspeed/t;
		}
		if(diff<90){
			wdiff=(wangle-compangle);
		}
		if(diff>90 && diff<180){
			wdiff=180-(wangle-compangle);
		}
		if(diff>180 && diff<270){
			wdiff=(wangle-compangle)-180;
		}
		if(diff>270 && diff<360) {
			wdiff=360-(wangle-compangle);
		}
		if(diff!=90 && diff!=0){
			windage=wspeed*Math.sin(wdiff);
		}
		double output[] = {t,fang,wdiff,windage};
		return output;
	}
}
