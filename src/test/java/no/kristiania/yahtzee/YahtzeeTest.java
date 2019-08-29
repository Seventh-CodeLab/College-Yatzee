package no.kristiania.yahtzee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Test File

class YahtzeeTest {
    @Test
    void shouldCalculateForOnes(){
        assertEquals(0, Yahtzee.getScore(Category.ONES, new int[]{2,3,4,5,6}));
        assertEquals(3, Yahtzee.getScore(Category.ONES, new int[]{2,1,4,1,1}));
        assertEquals(5, Yahtzee.getScore(Category.ONES, new int[]{1,1,1,1,1}));
    }
    @Test
    void shouldCalculateForTwos(){
        assertEquals(2, Yahtzee.getScore(Category.TWOS, new int[]{2,4,6,1,1}));
        assertEquals(6, Yahtzee.getScore(Category.TWOS, new int[]{2,4,2,2,1}));
    }
    @Test
    void shouldCalculateForThrees(){
        assertEquals(3, Yahtzee.getScore(Category.THREES, new int[]{3,4,6,1,1}));
        assertEquals(0, Yahtzee.getScore(Category.THREES, new int[]{6,4,6,1,1}));
        assertEquals(12, Yahtzee.getScore(Category.THREES, new int[]{3,4,3,3,3}));
    }
    @Test
    void shouldCalculateForFours(){
        assertEquals(4, Yahtzee.getScore(Category.FOURS, new int[]{2,4,6,1,1}));
        assertEquals(8, Yahtzee.getScore(Category.FOURS, new int[]{2,4,2,4,1}));
    }
    @Test
    void shouldCalculateForFives(){
        assertEquals(5, Yahtzee.getScore(Category.FIVES, new int[]{2,4,5,1,1}));
        assertEquals(15, Yahtzee.getScore(Category.FIVES, new int[]{5,5,2,2,5}));
    }
    @Test
    void shouldCalculateForSixes(){
        assertEquals(6, Yahtzee.getScore(Category.SIXES, new int[]{2,4,6,1,1}));
        assertEquals(30, Yahtzee.getScore(Category.SIXES, new int[]{6,6,6,6,6}));
    }

    @Test
    void shouldCalculateForPairs(){
        assertEquals(6, Yahtzee.getScore(Category.PAIR, new int[]{2,4,6,3,3}));
        assertEquals(10, Yahtzee.getScore(Category.PAIR, new int[]{1,1,5,5,6}));
        assertEquals(12, Yahtzee.getScore(Category.PAIR, new int[]{3,2,2,6,6}));
    }

    @Test
    void shouldCalculateForTwoPairs(){
        assertEquals(6, Yahtzee.getScore(Category.TWOPAIR, new int[]{1,1,2,2,3}));
        assertEquals(0, Yahtzee.getScore(Category.TWOPAIR, new int[]{1,2,2,2,3}));
        assertEquals(16, Yahtzee.getScore(Category.TWOPAIR, new int[]{2,2,6,6,6}));
    }

    @Test
    void shouldCalculateForThreeOfKind(){
        assertEquals(3, Yahtzee.getScore(Category.THREEOFKIND, new int[]{1,1,1,2,3}));
        assertEquals(6, Yahtzee.getScore(Category.THREEOFKIND, new int[]{2,2,2,2,3}));
    }

    @Test
    void shouldCalculateForFourOfKind(){
        assertEquals(0, Yahtzee.getScore(Category.FOUROFKIND, new int[]{1,1,1,2,3}));
        assertEquals(8, Yahtzee.getScore(Category.FOUROFKIND, new int[]{2,2,2,2,3}));
    }

    @Test
    void shouldCalculateForFullHouse(){
        assertEquals(25, Yahtzee.getScore(Category.FULLHOUSE, new int[]{1,1,1,2,2}));
        assertEquals(0, Yahtzee.getScore(Category.FULLHOUSE, new int[]{6,2,2,2,3}));
    }

    @Test
    void shouldCalculateForSmallStraight(){
        assertEquals(30, Yahtzee.getScore(Category.SMALLSTRAIGHT, new int[]{3,2,1,4,5}));
        assertEquals(0, Yahtzee.getScore(Category.SMALLSTRAIGHT, new int[]{2,4,3,5,6}));
    }

    @Test
    void shouldCalculateForLargeStraight(){
        assertEquals(40, Yahtzee.getScore(Category.LARGESTRAIGHT, new int[]{2,4,5,6,3}));
        assertEquals(0, Yahtzee.getScore(Category.LARGESTRAIGHT, new int[]{2,1,5,5,3}));
    }

    @Test
    void shouldCalculateForChance(){
        assertEquals(15, Yahtzee.getScore(Category.CHANCE, new int[]{1,4,2,6,2}));
        assertEquals(18, Yahtzee.getScore(Category.CHANCE, new int[]{4,4,2,2,6}));
    }

    @Test
    void shouldCalculateForYahtzee(){
        assertEquals(50, Yahtzee.getScore(Category.YAHTZEE, new int[]{1,1,1,1,1}));
        assertEquals(0, Yahtzee.getScore(Category.YAHTZEE, new int[]{2,2,2,2,3}));
    }

}
