public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[numberOfPlanets];
		for (int i = 0;i < numberOfPlanets;i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyvel = in.readDouble();
			double mass = in.readDouble();
			String imgName = in.readString();
			Planet p = new Planet(xxPos,yyPos,xxVel,yyvel,mass,imgName);
			allPlanets[i] = p;
		}
		return allPlanets;
	}


	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet[] planets = readPlanets(fileName);
		int n = planets.length;

		StdDraw.enableDoubleBuffering();// call it once to avoid flckering
		String backgroundImage = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,backgroundImage);
			for (Planet p:planets){
				String imgPath = "images/" + p.imgFileName;
				StdDraw.picture(p.xxPos,p.yyPos,imgPath);
				StdDraw.show();
			}
		StdDraw.show();


		double time = 0;
		while (time  <= T) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			//update xForces and yForces
			for (int i =1;i<n;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (Planet p:planets) {
				double xForce = p.calcNetForceExertedByX(planets);
				double yForce = p.calcNetForceExertedByY(planets);
				p.update(dt,xForce,yForce);
			}


			StdDraw.picture(0,0,backgroundImage);
			for (Planet p:planets){
				String imgPath = "images/" + p.imgFileName;
				StdDraw.picture(p.xxPos,p.yyPos,imgPath);
				StdDraw.show();
			}
			StdDraw.show();
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
   					StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

		
		
		
		

		
		


	}
}