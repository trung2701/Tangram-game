/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Shine
 */
abstract class TanShape {
    private double cX,cY;
    private Shape shape;
    private int Size;  // Size set to 100 by default
    private AffineTransform translator = new AffineTransform();
    final double root2 = Math.sqrt(2);
    private Graphics2D g2d; 
    private Color a, chosenColor;
    
    public TanShape(double cX, double cY, int Size){
        this.Size = Size;
        a =  new Color(255, 153, 51);
        chosenColor = Color.black;
        
	double[] xOff = getXOff(); 
	double[] yOff = getYOff(); 

	double[] xpoints = new double[xOff.length];
	double[] ypoints = new double[yOff.length];

	for (int i=0; i<xOff.length; i++){
	    xpoints[i] = xOff[i] * Size;
	    ypoints[i] = yOff[i] * Size;
	}

	GeneralPath gp = new GeneralPath();
	float xp = new Double(xpoints[0]).floatValue();
	float yp = new Double(ypoints[0]).floatValue();
	gp.moveTo(xp,yp);
	for (int i=1; i < xpoints.length; i++){
	  xp = new Double(xpoints[i]).floatValue();
          yp = new Double(ypoints[i]).floatValue();
	    gp.lineTo(xp,yp);
	}
	gp.closePath();
	translator.setToTranslation(cX,cY);
	this.cX = cX;
	this.cY = cY;
	shape = gp.createTransformedShape(translator);
    }
    
    abstract double[] getXOff(); 
    abstract double[] getYOff();

    public void setSize(int s){
	Size = s;        
    }
    
    public Rectangle getBounds(){
        return shape.getBounds();
    }
    
    public int getSize(){
	return Size;
    }

    public double getX(){
	return cX;
    }

   public double getY(){
	return cY;
    }

    public void rotate(double theta){ // angle to rotate shape by (in radians)
	AffineTransform rotator = AffineTransform.getRotateInstance(theta,cX,cY);
	shape = rotator.createTransformedShape(shape);
    }
    
    public void flip(){  // mirror the shape across the Y-axis through its centre 
	AffineTransform origin = AffineTransform.getTranslateInstance(-cX,-cY);
	AffineTransform reset = AffineTransform.getTranslateInstance(cX,cY);
	AffineTransform flipper = AffineTransform.getScaleInstance(-1,1);
	flipper.concatenate(origin);
	reset.concatenate(flipper);
	shape = reset.createTransformedShape(shape);
    }

    public void move(double nX, double nY){  // move the shape's centre to these coordinates
        translator.setToTranslation(nX - cX, nY - cY);
        shape = translator.createTransformedShape(shape);
        cX = nX;
        cY = nY;
    }
    
    public boolean contains(double x, double y){  // tests whether the given point lies within the shape
	return shape.contains(x,y);
    }
    
    public void setColor(Color shapeColor){
        a = shapeColor;
    }        
    
    public Color getColor(){
        return a;
    }
    
    public void setChosenColor(Color chosenColor){
        this.chosenColor = chosenColor;
    }
        
    public boolean isChosen(){
        return (chosenColor == Color.BLACK ? true : false);
    }
    
    public void resetChosenColor(){
        chosenColor =  Color.BLACK;
    }
    
    public void drawShape(Graphics2D g2d){  // displays the shape in the given color with a black outline        
        g2d.setColor(a);
	g2d.fill(shape);
	g2d.setColor(chosenColor);
	g2d.draw(shape);
    }        
}

class SmallTriTan extends TanShape {

    public SmallTriTan(double cX, double cY, int Size){
	super(cX,cY, Size);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (6 * root2), -1/ (6 * root2), 2/ (6*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (6 * root2), 2/ (6 * root2), -1/ (6*root2) };
	return yOff;
    }
}

class MedTriTan extends TanShape {

    public MedTriTan(double cX, double cY, int Size){
	super(cX,cY,Size);
    }

    public double[] getXOff(){
        double[] xOff = { -1.0/6 , -1.0/6, 2.0/6 };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1.0/6 , 2.0/6, -1.0/6 };
	return yOff;
    }
}

class LargeTriTan extends TanShape {

    public LargeTriTan(double cX, double cY, int Size){
	super(cX,cY,Size);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (3 * root2), -1/ (3 * root2), 2/ (3*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (3 * root2), 2/ (3 * root2), -1/ (3*root2) };
	return yOff;
    }
}

class SquareTan extends TanShape {

    public SquareTan(double cX, double cY, int Size){
	super(cX,cY,Size);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (4 * root2), -1/ (4 * root2), 1/ (4*root2) , 1/(4*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (4 * root2), 1/ (4 * root2), 1/ (4*root2), -1/(4*root2) };
	return yOff;
    }
}

class ParallelogramTan extends TanShape {

    public ParallelogramTan(double cX, double cY, int Size){
	super(cX,cY,Size);
    }

    public double[] getXOff(){
        double[] xOff = { -3.0/8 , -1.0/8, 3.0/8 , 1.0/8 };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1.0/8, 1.0/8, 1.0/8, -1.0/8 };
	return yOff;
    }
}
