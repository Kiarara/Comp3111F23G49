package Function_A;

import java.util.Comparator;

/**
 * This is the weight comparator for the two associations, this class
 * is useful for sorting the association list by weights
 */
class WeightComparator implements Comparator<Association> {
    /**
     * This function compares two associations by weight
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return 1 if the first object has a larger weight,
     * return 0 if the weights are the same,
     * return -1 if the second object has a larger weight
     */
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