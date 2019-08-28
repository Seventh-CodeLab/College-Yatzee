package no.kristiania.roman;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzeeTest {

    enum Category{
        ONES, TWOS, THREES, FOURS, FIVES, SIXES, PAIR, TWOPAIR, THREEOFKIND, FOUROFKIND, FULLHOUSE, SMALLSTRAIGHT, LARGESTRAIGHT, CHANCE, YATZY
    }

    @Test
    void shouldCalculateForOnes(){
        assertEquals(0, getScore(Category.ONES, new int[]{2,3,4,5,6}));
        assertEquals(3, getScore(Category.ONES, new int[]{2,1,4,1,1}));
        assertEquals(5, getScore(Category.ONES, new int[]{1,1,1,1,1}));
    }
    @Test
    void shouldCalculateForTwos(){
        assertEquals(2, getScore(Category.TWOS, new int[]{2,4,6,1,1}));
        assertEquals(6, getScore(Category.TWOS, new int[]{2,4,2,2,1}));
    }
    @Test
    void shouldCalculateForThrees(){
        assertEquals(3, getScore(Category.THREES, new int[]{3,4,6,1,1}));
        assertEquals(0, getScore(Category.THREES, new int[]{6,4,6,1,1}));
        assertEquals(12, getScore(Category.THREES, new int[]{3,4,3,3,3}));
    }
    @Test
    void shouldCalculateForFours(){
        assertEquals(4, getScore(Category.FOURS, new int[]{2,4,6,1,1}));
        assertEquals(8, getScore(Category.FOURS, new int[]{2,4,2,4,1}));
    }
    @Test
    void shouldCalculateForFives(){
        assertEquals(5, getScore(Category.FIVES, new int[]{2,4,5,1,1}));
        assertEquals(15, getScore(Category.FIVES, new int[]{5,5,2,2,5}));
    }
    @Test
    void shouldCalculateForSixes(){
        assertEquals(6, getScore(Category.SIXES, new int[]{2,4,6,1,1}));
        assertEquals(30, getScore(Category.SIXES, new int[]{6,6,6,6,6}));
    }

    public int getScore(Category category, int[] rolls){
        int score = 0;
        int[] count = diceCounter(rolls); //Keeps track of how many die per value

        switch (category){
            case ONES: return count[0];
            case TWOS: return count[1]*2;
            case THREES: return count[2]*3;
            case FOURS: return count[3]*4;
            case FIVES: return count[4]*5;
            case SIXES: return count[5]*6;
            case PAIR:

            default:
                System.err.println("Error: No category was selected");
                break;
        }

        return score;
    }

    //Method takes rolls and counts them, returns amount of select roles in array.
    private int[] diceCounter(int[] rolls){
        int[] counter = new int[]{0,0,0,0,0,0}; //Six possible rolls (Array indexes - 1)
        for(int dice : rolls){
            counter[dice-1]++;
        }
        return counter;
    }
}
