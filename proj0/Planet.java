public class Planet
{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	/** Initialize an identical Planet object as Planet p. */
	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculate the distance between two planets. */
	public double calcDistance(Planet p1)
	{
		double r = Math.sqrt((this.xxPos - p1.xxPos) * (this.xxPos - p1.xxPos) + (this.yyPos - p1.yyPos) * (this.yyPos - p1.yyPos));
		return r;
	}

	/** Calculate the Force between two planets. */
	public double calcForceExertedBy(Planet p1)
	{
		double G = 6.67E-11;
		double r = calcDistance(p1);
		double F = G * this.mass * p1.mass / (r * r);
		return F;
	}

	/** Calculate the Force of X axis between two planets. */
	public double calcForceExertedByX(Planet p1)
	{
		double G = 6.67E-11;

		double r = calcDistance(p1);
		double Fx = G * this.mass * p1.mass * (p1.xxPos - this.xxPos) / (r * r * r);

		return Fx;
	}

	/** Calculate the Force of Y axis between two planets. */
	public double calcForceExertedByY(Planet p1)
	{
		double G = 6.67E-11;
		double r = calcDistance(p1);
		double Fy = G * this.mass * p1.mass * (p1.yyPos - this.yyPos) / (r * r * r);

		return Fy;
	}

	/** Calculate the net Force of X axis among all planets. */
	public double calcNetForceExertedByX(Planet[] planets)
	{
		double Fx = 0;
		for (int i = 0; i < planets.length; i++)
		{
			if (!this.equals(planets[i]))
			{
				Fx += this.calcForceExertedByX(planets[i]);
			}
		}
		return Fx;
	}

	/** Calculate the Force of Y axis among all planets. */
	public double calcNetForceExertedByY(Planet[] planets)
	{
		double Fy = 0;
		for (int i = 0; i < planets.length; i++)
		{
			if (!this.equals(planets[i]))
			{
				Fy += this.calcForceExertedByY(planets[i]);
			}
		}
		return Fy;
	}


	/** Update a planet's position. */
	public void update(double dt, double fX, double fY)
	{
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	/** Draw one planet. */
	public void draw()
	{
		String imgpath = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imgpath);
	}
}