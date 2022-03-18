package Chapter_7_Programming_Challenges;

public class RainFall 
{
	private double rainfall[];
	private int year;
	private double avg;
	private double max;
	private double min;
	
	public RainFall(int yr)
	{
		rainfall = new double[12];
		year = yr;
		avg = 0;
		max = 0;
		min = 0;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public double getMonth(int i)
	{
		return rainfall[i];
	}

	public void setMonth(int i, double amt)
	{
		rainfall[i] = amt;
	}
}
