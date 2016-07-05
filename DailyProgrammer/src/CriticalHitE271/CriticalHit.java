package CriticalHitE271;

/**
 * Created by Bruno on 19/06/2016.
 * Link to challenge: https://www.reddit.com/r/dailyprogrammer/comments/4nvrnx/20160613_challenge_271_easy_critical_hit/
 */
public class CriticalHit {
    public static void main(String[] args) {
        System.out.println(calculateChance(8,20));
    }

    /**
     * Calculates percentage of rolling h or h+ value
     * @param sides how many sides our dice has
     * @param health how much health does our enemy have
     * @return returns chance in decimal form
     */
    private static double calculateChance(int sides, int health) {
        if (health > sides)
            return (1.0d/sides) * calculateChance(sides, health-sides);
        else {
            int equilibrium = 0; //Where does
            for (int i = 1; i <= sides; i++) {
                if (i == health) {
                    equilibrium = i;
                    break;
                }
            }
            return ((sides - equilibrium + 1)/(double)sides);
        }
    }
}
