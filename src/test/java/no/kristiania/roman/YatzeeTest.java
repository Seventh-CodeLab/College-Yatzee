package no.kristiania.roman;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzeeTest {

    enum Category{
        ONES, TWOS, THREES, FOURS, FIVES, SIXES, PAIR, TWOPAIR, THREEOFKIND, FOUROFKIND, FULLHOUSE, SMALLSTRAIGHT, LARGESTRAIGHT, CHANCE, YAHTZEE
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

    @Test
    void shouldCalculateForPairs(){
        assertEquals(6, getScore(Category.PAIR, new int[]{2,4,6,3,3}));
        assertEquals(10, getScore(Category.PAIR, new int[]{1,1,5,5,6}));
        assertEquals(12, getScore(Category.PAIR, new int[]{3,2,2,6,6}));
    }

    @Test
    void shouldCalculateForTwoPairs(){
        assertEquals(6,getScore(Category.TWOPAIR, new int[]{1,1,2,2,3}));
        assertEquals(0,getScore(Category.TWOPAIR, new int[]{1,2,2,2,3}));
        assertEquals(16,getScore(Category.TWOPAIR, new int[]{2,2,6,6,6}));
    }

    @Test
    void shouldCalculateForThreeOfKind(){
        assertEquals(3,getScore(Category.THREEOFKIND, new int[]{1,1,1,2,3}));
        assertEquals(6,getScore(Category.THREEOFKIND, new int[]{2,2,2,2,3}));
    }

    @Test
    void shouldCalculateForFourOfKind(){
        assertEquals(0,getScore(Category.FOUROFKIND, new int[]{1,1,1,2,3}));
        assertEquals(8,getScore(Category.FOUROFKIND, new int[]{2,2,2,2,3}));
    }

    @Test
    void shouldCalculateForFullHouse(){
        assertEquals(25,getScore(Category.FULLHOUSE, new int[]{1,1,1,2,2}));
        assertEquals(0,getScore(Category.FULLHOUSE, new int[]{6,2,2,2,3}));
    }

    @Test
    void shouldCalculateForSmallStraight(){
        assertEquals(30,getScore(Category.SMALLSTRAIGHT, new int[]{3,2,1,4,5}));
        assertEquals(0,getScore(Category.SMALLSTRAIGHT, new int[]{2,4,3,5,6}));
    }

    @Test
    void shouldCalculateForLargeStraight(){
        assertEquals(40,getScore(Category.LARGESTRAIGHT, new int[]{2,4,5,6,3}));
        assertEquals(0,getScore(Category.LARGESTRAIGHT, new int[]{2,1,5,5,3}));
    }

    @Test
    void shouldCalculateForChance(){
        assertEquals(15,getScore(Category.CHANCE, new int[]{1,4,2,6,2}));
        assertEquals(18,getScore(Category.CHANCE, new int[]{4,4,2,2,6}));
    }

    @Test
    void shouldCalculateForYahtzee(){
        assertEquals(50,getScore(Category.YAHTZEE, new int[]{1,1,1,1,1}));
        assertEquals(0,getScore(Category.YAHTZEE, new int[]{2,2,2,2,3}));
    }

    public int getScore(Category category, int[] rolls){
        int[] count = diceCounter(rolls); //Keeps track of how many of each dice are thrown. Die value = index + 1

        switch (category){
            case ONES: return count[0];
            case TWOS: return count[1]*2;
            case THREES: return count[2]*3;
            case FOURS: return count[3]*4;
            case FIVES: return count[4]*5;
            case SIXES: return count[5]*6;
            case PAIR: return ofKind(2,count);
            case TWOPAIR: return ofKindTwo(2,2, count);
            case THREEOFKIND: return ofKind(3,count);
            case FOUROFKIND: return ofKind(4,count);
            case FULLHOUSE: return ofKindTwo(2,3, count) > 0 ? 25 : 0;
            case SMALLSTRAIGHT: return count[0] == 1 && count[1] == 1 && count[2] == 1 && count[3] == 1 && count[4] == 1 ? 30 : 0;
            case LARGESTRAIGHT: return count[1] == 1 && count[2] == 1 && count[3] == 1 && count[4] == 1 && count[5] == 1 ? 40 : 0;
            case CHANCE: return rolls[0] + rolls[1] + rolls[2] + rolls[3] + rolls[4];
            case YAHTZEE: return ofKind(5,count) > 0 ? 50 : 0;
            default:
                System.err.println("Error: No category was selected");
                return 0;
        }
    }

    //Method takes in a required amount of same roll die TWICE, if both are not met it gives 0.
    private int ofKindTwo(int amountA, int amountB, int[] count){
        for(int i = 5; i >= 0; i--){
            if(count[i] >= amountA){
                for(int j = 5; j >= 0; j--){
                    if(j == i) continue; //Skip checking the same value
                    if(count[j] >= amountB){
                        return (amountA*(i+1)) + (amountB*(j+1));
                    }
                }
            }
        }
        return 0;
    }

    //Method takes in a required amount of same roll die, if nothing is met gives 0.
    private int ofKind(int amount, int[] count){
        for(int i = 5; i >= 0; i--){
            if(count[i] >= amount){
                return amount*(i+1);
            }
        }
        return 0;
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
