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
     Point2D[] farthestPair2 = new Point2D[ 2 ];
     
     List<Point2D> convexHull = new ArrayList(); //the vertices of the convex hull of S
     List<Point2D> convexHullTop = new ArrayList();
     List<Point2D> convexHullBot = new ArrayList();
     Color convexHullColour = Color.blue;
     Color genericColour = Color.black;

     
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
        g.drawLine((int)farthestPair2[0].x, (int)farthestPair2[0].y, (int)farthestPair2[1].x, (int)farthestPair2[1].y);
    }
    
    
    public void findConvexHull() {
        //code this
        //finds bottom half of the convex hull left to right
        S = MergeSort.mergeSort(S);
        for(Point2D curPoint: S){
            while(convexHullBot.size()>=2 && Point2D.checkLeftTurn(convexHullBot.get(convexHullBot.size()-2), convexHullBot.get(convexHullBot.size()-1), curPoint)){
                convexHullBot.remove(convexHullBot.size()-1);
            }
            convexHullBot.add(curPoint);
        }

        //find top half of convex hull right to left
        for(int i = S.size()-1; i >=0 ; i--){
            Point2D curPoint = S.get(i);
            while(convexHullTop.size()>=2 && Point2D.checkLeftTurn(convexHullTop.get(convexHullTop.size()-2), convexHullTop.get(convexHullTop.size()-1), curPoint)){
                convexHullTop.remove(convexHullTop.size()-1);
            }
            convexHullTop.add(curPoint);
        }

        convexHull.addAll(convexHullBot);
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
        //antipodal pair
        List<Point2D[]> pairs = new ArrayList();
        int i = 0;
        int j = 0;
        while(i < convexHullTop.size()-1 || j < convexHullBot.size()-1){
            Point2D[] curPair = {convexHullTop.get(i),convexHullBot.get(j)};
            pairs.add(curPair);
            if(i == convexHullTop.size()-1){
                j++;
            }
            else if(j == convexHullBot.size() - 1){
                i++;
            }
            else if( (convexHullTop.get(i+1).y - convexHullTop.get(i).y) * (convexHullBot.get(j).x - convexHullBot.get(j+1).x) > (convexHullTop.get(i+1).x - convexHullTop.get(i).x) * (convexHullBot.get(j).y - convexHullBot.get(j+1).y)){
                i++;
            }
            else{
                j++;
            }
        }
        
        double greatestD = 0;
        for(Point2D[] pair: pairs){
            double curD = Point2D.getDistance(pair[0],pair[1]);
            if(curD > greatestD){
                farthestPair = pair;
                greatestD = curD;
            }
        }

    }
    
    public void findFarthestPair_BruteForceWay() {
        //code this just for fun, to see how many more distance calculations and comparisons it does than the efficient way
        double greatestD = 0;
        for(Point2D p1: convexHullTop){
            for(Point2D p2: convexHullBot){
                double curD = Point2D.getDistance(p1,p2);
            if(curD > greatestD){
                farthestPair2[1] = p1;
                farthestPair2[0] = p2;
                greatestD = curD;
            }
            }
        }
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
        fpf.findFarthestPair_BruteForceWay();
        fpf.setVisible(true); 
    }
}
