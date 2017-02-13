
/**
 *
 * @created Jun 17, 2011
 * @author Pavel Danchenko
 */
public class FooBars {


    /**
     * @param args
     */
    public static void main(String[] args) {
        double cost = 1.0;
        double sum = 0.0;
        double cache = 200.0;
        int i = 0;
        while ( sum < cache ) {
            sum += (cost * 1.2);
            cost *=1.2;
            i++;
        }
        System.out.println(i);

        System.out.println(1* (1 - Math.pow(1.2, 20)/(1 - 1.2)));

        System.out.println(Math.log(41)/Math.log(1.2));
        System.exit(0);
    }

}
