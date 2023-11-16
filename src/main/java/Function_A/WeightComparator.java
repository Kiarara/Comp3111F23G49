package Function_A;

import java.util.Comparator;

class WeightComparator implements Comparator<Association> {
    public int compare(Association a, Association b) {
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