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
        g.setColor(Color.white);
        g.drawRect(0, 0, 800, 800);
        g.setColor(Color.black);
        for(Point2D p: S){
            g.drawRect((int)p.x, (int)p.y, 1, 1);
        }
        //draw the points in the convex hull
        g.setColor(Color.red);
        for(int i = 0; i < convexHull.size()-1; i++){
            Point2D curPoint = convexHull.get(i);
            Point2D nextPoint = convexHull.get(i+1);
            g.drawLine((int)curPoint.x, (int)curPoint.y, (int)nextPoint.x, (int)nextPoint.y);
        }
        //draw a red line connecting the farthest pair
    }
    
    
    public void findConvexHull() {
        //code this
        //finds bottom half of the convex hull left to right
        S = MergeSort.mergeSort(S);
        convexHull.add(S.get(0));
        convexHull.add(S.get(1));
        for(int i = 2; i<S.size();i++){
            Point2D curPoint = S.get(i);
            int lastIndex = convexHull.size() - 1;
            boolean leftTurn = Point2D.checkLeftTurn(convexHull.get(lastIndex-1), convexHull.get(lastIndex), curPoint);
            while(leftTurn){
                convexHull.remove(lastIndex);
                lastIndex --;
                if(convexHull.size() < 2){
                    leftTurn = false;
                }
                else{
                    leftTurn = Point2D.checkLeftTurn(convexHull.get(lastIndex-1), convexHull.get(lastIndex), curPoint);
                }
            }
            convexHull.add(curPoint);
        }
        
        //find top half of convex hull right to left
        List<Point2D> convexHullTop = new ArrayList();
        convexHullTop.add(S.get(S.size()-1));
        convexHullTop.add(S.get(S.size()-2));
        for(int i = S.size()-3; i>=0;i--){
            Point2D curPoint = S.get(i);
            int lastIndex = convexHullTop.size() - 1;
            boolean leftTurn = Point2D.checkLeftTurn(convexHullTop.get(lastIndex-1), convexHullTop.get(lastIndex), curPoint);
            while(leftTurn){
                convexHullTop.remove(lastIndex);
                lastIndex --;
                if(convexHullTop.size() < 2){
                    leftTurn = false;
                }
                else{
                    leftTurn = Point2D.checkLeftTurn(convexHullTop.get(lastIndex-1), convexHullTop.get(lastIndex), curPoint);
                }
            }
            convexHullTop.add(curPoint);
        }
        
        //combine bottom half and top half
        convexHull.addAll(convexHullTop);
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
        
        fpf.setBackground(Color.white);
        fpf.setSize(800, 800);
        fpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fpf.makeRandomPoints();
        
        fpf.findConvexHull();
        
        //fpf.findFarthestPair_EfficientWay();
        
        fpf.setVisible(true); 
    }
}
