package farthestpairfinder;

import javax.swing.JFrame;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class FarthestPairFinder extends JFrame {

     int pointSize = 12;
     int numPoints = 100;
     
     List<Point2D> S = new ArrayList();; //the set S
     Point2D[] farthestPair = new Point2D[ 2 ]; //the two points of the farthest pair
     
     List<Point2D> convexHull = new ArrayList(); //the vertices of the convex hull of S
     List<Point2D> convexHullTop = new ArrayList();
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
        g.setColor(genericColour);
        for(Point2D p: S){
            g.drawRect((int)p.x, (int)p.y, 1, 1);
        }
        //draw the points in the convex hull
        g.setColor(convexHullColour);
        for(int i = 0; i < convexHull.size(); i++){
            Point2D curPoint = convexHull.get(i);
            g.drawRect((int)curPoint.x, (int)curPoint.y, 1 ,1);
            Point2D nextPoint = convexHull.get((i+1)%convexHull.size());
            g.drawLine((int)curPoint.x, (int)curPoint.y, (int)nextPoint.x, (int)nextPoint.y);
        }
        //draw a red line connecting the farthest pair
        g.setColor(Color.red);
        g.drawLine((int)farthestPair[0].x, (int)farthestPair[0].y, (int)farthestPair[1].x, (int)farthestPair[1].y);
    }
    
    
    public void findConvexHull() {
        //code this
        //finds bottom half of the convex hull left to right
        S = MergeSort.mergeSort(S);
        for(Point2D curPoint: S){
            while(convexHull.size()>=2 && Point2D.cross(convexHull.get(convexHull.size()-2),convexHull.get(convexHull.size()-1),curPoint) >= 0){
                convexHull.remove(convexHull.size()-1);
            }
            convexHull.add(curPoint);
        }
        
        //find top half of convex hull right to left
        //List<Point2D> convexHullTop = new ArrayList();
        for(int i = S.size()-1; i>=0;i--){
            Point2D curPoint = S.get(i);
            while(convexHullTop.size()>=2 && Point2D.cross(convexHullTop.get(convexHullTop.size()-2),convexHullTop.get(convexHullTop.size()-1),curPoint) >= 0){
                convexHullTop.remove(convexHullTop.size()-1);
            }
            convexHullTop.add(curPoint);
        }
        
        //extra point lol
        convexHull.remove(convexHull.size()-1);
        //combine bottom half and top half
        convexHull.addAll(convexHullTop);
        //extra point from putting them together
        convexHull.remove(convexHull.size()-1);
    }
    
    public void findFarthestPair_EfficientWay() {
        //code this
        //must make use of the convex hull, which will have been calculated by the time this method is called
        //lol doesnt work
        int oppSide = convexHull.size()/2;
        double farthest = 0;
        if(convexHull.size()%2 == 0){
            for(int i = 0; i < oppSide; i++){
                Point2D curPoint = convexHull.get(i);
                Point2D oppPoint = convexHull.get(i+oppSide);
                double distance = Point2D.getDistance(curPoint,oppPoint);
                if(distance > farthest){
                    farthest = distance;
                    farthestPair[0] = curPoint;
                    farthestPair[1] = oppPoint;
                }
            }
        }
        else{
            for(int i = 0; i < oppSide; i++){
                for(int j = 0; j < 2; j++){
                    Point2D curPoint = convexHull.get(i);
                    Point2D oppPoint = convexHull.get((i+j+oppSide)%convexHull.size());
                    double distance = Point2D.getDistance(curPoint,oppPoint);
                    if(distance > farthest){
                        farthest = distance;
                        farthestPair[0] = curPoint;
                        farthestPair[1] = oppPoint;
                    }
                }
            }
        }
    }
    
    public void findFarthestPair_BruteForceWay() {
        //code this just for fun, to see how many more distance calculations and comparisons it does than the efficient way
    }
    
   
    public static void main(String[] args) {

        //no changes are needed in main().  Just code the blank methods above.
        
        FarthestPairFinder fpf = new FarthestPairFinder();
        fpf.setBackground(Color.black);
        fpf.setSize(800, 800);
        fpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fpf.makeRandomPoints();
        
        fpf.findConvexHull();
        
        fpf.findFarthestPair_EfficientWay();
        
        fpf.setVisible(true); 
    }
}
