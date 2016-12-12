/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farthestpairfinder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xum3131
 */
public class MergeSort {
    //ye olde mergesort
    public static List<Point2D> mergeSort(List<Point2D> a){
        int length = a.size();
        if(length == 1){
            return a;
        }
        
        List<Point2D> kappa = new ArrayList();
        for(Point2D p: a.subList(0, length/2)){
                kappa.add(p);
        }
        List<Point2D> kappa2 = new ArrayList();
        for(Point2D p: a.subList(length/2, length)){
                kappa2.add(p);
        }
        List<Point2D> half1 = mergeSort(kappa);
        List<Point2D> half2 = mergeSort(kappa2);
        
        return merge(half1,half2);
    }
    
    public static List<Point2D> merge(List<Point2D> a1,List<Point2D> a2){
        List<Point2D> result = new ArrayList();
        
        while(a1.size() > 0 && a2.size() > 0){
            if(a1.get(0).x == a2.get(0).x){
                if(a1.get(0).y < a2.get(0).y){
                    result.add(a1.get(0));
                    a1.remove(0);
                }
                else{
                    result.add(a2.get(0));
                    a2.remove(0);
                }
            }
            else if(a1.get(0).x < a2.get(0).x){
                result.add(a1.get(0));
                a1.remove(0);
            }
            else{
                result.add(a2.get(0));
                a2.remove(0);
            }
        }
        
        if(a1.isEmpty()){
            for(Point2D p: a2){
                result.add(p);
            }
        }
        else{
            for(Point2D p: a1){
                result.add(p);
            }
        }
        
        return result;
    }
            
}
