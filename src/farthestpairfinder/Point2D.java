package farthestpairfinder;

import java.awt.*;

public class Point2D {
    
    double x, y;
    boolean visited; //might need this in the convex hull finding algorithm
    Color color;
    
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.color = Color.yellow;
    }
    
    public static double getSlope(Point2D p1, Point2D p2){
        return (p1.y - p2.y)/(p1.x - p2.x);
    }
    
    public static boolean checkLeftTurn(Point2D center, Point2D p1, Point2D p2){
        double slope1 = getSlope(center, p1);
        double slope2 = getSlope(center, p2);
        if(slope1 < slope2){
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
