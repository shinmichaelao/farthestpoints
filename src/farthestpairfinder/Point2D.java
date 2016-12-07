package farthestpairfinder;

import java.awt.*;

public class Point2D {
    
    double x, y;
    boolean visited; //might need this in the convex hull finding algorithm
    Color color;
    
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.color = Color.yellow;
    }
    
    public static double getDistance(Point2D p1, Point2D p2){
        return Math.sqrt(Math.pow(p1.x-p2.x,2) + Math.pow(p1.y-p2.y,2));
    }
    
    public static double getSlope(Point2D p1, Point2D p2){
        return (p2.y - p1.y)/(p2.x - p1.x);
    }
    
    public static boolean checkLeftTurn(Point2D center, Point2D p1, Point2D p2){
        double slope1 = getSlope(center, p1);
        double slope2 = getSlope(center, p2);
        if(slope1 <= slope2){
            return true;
        }
        else{
            return false;
        }
    }
    //Returns the vector that stretches between this and other.
    public Vector subtract( Point2D other ) {
        return new Vector( this.x - other.x, this.y - other.y);
    }
}
