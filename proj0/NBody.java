public class NBody
{
	/** Given a file name, return a double corresponding to the radius of the universe in that file. */
	public static double readRadius(String path)
	{
		In in = new In(path);
		int n = in.readInt();
		double r = in.readDouble();
		return r;
	}

	/** Given a file name, return an array of Planets corresponding to the planets in the file.*/
	public static Planet[] readPlanets(String path)
	{
		In in = new In(path);
		int n = in.readInt();
		Planet[] planets = new Planet[n];
		double r = in.readDouble();
		//List<Planet> planets = new ArrayList<Planet>();

		for (int i = 0; i < n; i++)
		{
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			planets[i] = p;
		}
        return planets;
	}

	public static void main(String[] args)
	{
		// Collect all inputs
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//draw the background
		StdDraw.enableDoubleBuffering();
		String background = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		// StdDraw.picture(0, 0, background);
		// for (int i = 0; i < planets.length; i++)
		// {
		// 	planets[i].draw();
		// }

		for (int t = 0; t <= T; t += dt)
		{
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++)
			{
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++)
			{
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, background);
			for (int i = 0; i < planets.length; i++)
			{
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		/** print the final state of the universe. */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) 
		{
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
    			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
    			planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}