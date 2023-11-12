package board;

import java.util.Comparator;

public class WeightComparator implements Comparator<edge> {
        public int compare(edge a, edge b) {
            if(a.weight>b.weight) {
                return 1;
            }
            else if(a.weight==b.weight){
                return 0;
            }
            else{
                return -1;
            }
        }
}
