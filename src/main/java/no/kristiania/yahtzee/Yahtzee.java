package no.kristiania.yahtzee;

public class Yahtzee {
    public static int getScore(Category category, int[] rolls){
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

    //Method takes in a required amount of same roll die, if nothing is met gives 0.
    private static int ofKind(int amount, int[] count){
        for(int i = 5; i >= 0; i--){
            if(count[i] >= amount){
                return amount*(i+1);
            }
        }
        return 0;
    }

    //Method takes in a required amount of same roll die TWICE, if both are not met it gives 0.
    private static int ofKindTwo(int amountA, int amountB, int[] count){
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

    //Method takes rolls and counts them, returns amount of select roles in array.
    private static int[] diceCounter(int[] rolls){
        int[] counter = new int[]{0,0,0,0,0,0}; //Six possible rolls (Array indexes - 1)
        for(int dice : rolls){
            counter[dice-1]++;
        }
        return counter;
    }
}
