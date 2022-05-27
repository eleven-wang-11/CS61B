public class Planet {
	public double xxPos;
    public double yyPos;
    public double xxVel;   
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final static double G =6.67e-11; //final means it cannot be changed
      //contructor
    public Planet(double xP, double yP, double xV,
      				double yV, double m, String img) {
      	xxPos = xP;
      	yyPos = yP;
      	xxVel = xV;
      	yyVel = yV;
      	mass = m;
      	imgFileName = img;
      }
      //return an identical Planet obeject (i.e a copy)
    public Planet(Planet p){
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;
      	
     }

     public double calcDistance(Planet p){
     	double xDif = (p.xxPos - this.xxPos)*(p.xxPos - this.xxPos);
     	double yDif = (p.yyPos-this.yyPos) * (p.yyPos-this.yyPos);
     	return Math.sqrt(xDif + yDif);

     }
     public double calcForceExertedBy(Planet p){
     	
     	return G*this.mass*p.mass / Math.pow(this.calcDistance(p),2);
     }
      
     public double calcForceExertedByX(Planet p){
     	double dx = p.xxPos - this.xxPos;
     	return calcForceExertedBy(p)*dx/calcDistance(p);
     }
     public double calcForceExertedByY(Planet p) {
     	double dy = p.yyPos - this.yyPos;
     	return calcForceExertedBy(p)*dy/calcDistance(p);
     }

     public double calcNetForceExertedByX(Planet[] planets) {
     	double force=0;
     	for (int i =0;i < planets.length;i++) {
     		if (!this.equals(planets[i])){
     			force += calcForceExertedByX(planets[i]);
     		}
     		
     	}
     	return force;
     }
     public double calcNetForceExertedByY(Planet[] planets) {
     	double force=0;
     	for (int i =0;i < planets.length;i++) {
     		if (!this.equals(planets[i])){
     			force += calcForceExertedByY(planets[i]);
     		}
     	}
     	return force;
     }

     public void update(double dt, double xForce, double yForce){
     	double aX = xForce/mass;
     	double aY = yForce/mass;
     	xxVel = xxVel + aX*dt;
     	yyVel = yyVel + aY*dt;
     	xxPos = xxPos + xxVel*dt;
     	yyPos = yyPos + yyVel*dt;

     }
  
}