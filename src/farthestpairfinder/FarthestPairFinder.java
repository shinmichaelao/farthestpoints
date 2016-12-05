package farthestpairfinder;

import javax.swing.JFrame;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class FarthestPairFinder extends JFrame {

     int pointSize = 12;
     int numPoints = 50;
     
     List<Point2D> S = new ArrayList();; //the set S
     Point2D[] farthestPair = new Point2D[ 2 ]; //the two points of the farthest pair
     
     List<Point2D> convexHull = new ArrayList(); //the vertices of the convex hull of S
     
     Color convexHullColour = Color.white;
     Color genericColour = Color.yellow;

     
    //fills S with random points
    public void makeRandomPoints() {
        Random rand = new Random();
 
        for (int i = 0; i < numPoints; i++) {
            int x = 50 + rand.nextInt(500);
            int y = 50 + rand.nextInt(500);
            S.add(new Point2D( x, y ));            
        }        
    }

    
    public void paint(Graphics g) {        
        //draw the points in S
        for(Point2D p: S){
            g.drawRect((int)p.x, (int)p.y, 1, 1);
        }
        //draw the points in the convex hull
        
        //draw a red line connecting the farthest pair
    }
    
    
    public void findConvexHull() {
        //code this
        //you'll need to make use of the Vector class to help calculate angles in 2D
        S = MergeSort.mergeSort(S);
    }
    
    public void findFarthestPair_EfficientWay() {
        //code this
        //must make use of the convex hull, which will have been calculated by the time this method is called
    }
    
    public void findFarthestPair_BruteForceWay() {
        //code this just for fun, to see how many more distance calculations and comparisons it does than the efficient way
    }
    
   
    public static void main(String[] args) {

        //no changes are needed in main().  Just code the blank methods above.
        
        FarthestPairFinder fpf = new FarthestPairFinder();
        
        fpf.setBackground(Color.BLACK);
        fpf.setSize(800, 800);
        fpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fpf.makeRandomPoints();
        
        fpf.findConvexHull();
        
        fpf.findFarthestPair_EfficientWay();
        
        fpf.setVisible(true); 
    }
}
