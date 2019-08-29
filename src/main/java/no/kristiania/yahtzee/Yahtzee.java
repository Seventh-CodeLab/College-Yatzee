package no.kristiania.yahtzee;

//Production File

class Yahtzee {
    static int getScore(Category category, int[] rolls){
        int[] dieFrequency = diceCounter(rolls); //Tracks roll results: Index 1 = Dice with 1's, etc.

        switch (category){
            case ONES: return dieFrequency[1];
            case TWOS: return dieFrequency[2]*2;
            case THREES: return dieFrequency[3]*3;
            case FOURS: return dieFrequency[4]*4;
            case FIVES: return dieFrequency[5]*5;
            case SIXES: return dieFrequency[6]*6;
            case PAIR: return findOfKind(2,dieFrequency);
            case TWOPAIR: return findTwoOfKind(2,2, dieFrequency);
            case THREEOFKIND: return findOfKind(3,dieFrequency);
            case FOUROFKIND: return findOfKind(4,dieFrequency);
            case FULLHOUSE: return findTwoOfKind(2,3, dieFrequency) > 0 ? 25 : 0;
            case SMALLSTRAIGHT: return isSmallStraight(dieFrequency) ? 30 : 0;
            case LARGESTRAIGHT: return isLargeStraight(dieFrequency) ? 40 : 0;
            case CHANCE: return sumRolls(rolls);
            case YAHTZEE: return findOfKind(5,dieFrequency) > 0 ? 50 : 0;
            default:
                System.err.println("Error: No category was selected");
                return 0;
        }
    }

    /* //This code block can be used to generate random rolls
    static int[] rollDie(){
        int[] rolls = new int[5];
        for(int roll = 0; roll <= 5; roll++){
            rolls[roll] = (int)(Math.random() * 6) + 1;
        }
        return rolls;
    }
    */

    //Method takes in a required amount of same roll die, if nothing is met gives 0.
    private static int findOfKind(int requiredDice, int[] dieFrequency){
        for(int dieValue = 6; dieValue > 0; dieValue--){
            if(dieFrequency[dieValue] >= requiredDice){
                return requiredDice*(dieValue);
            }
        }
        return 0;
    }

    //Method takes in a required amount of same roll die TWICE, if both are not met it gives 0.
    private static int findTwoOfKind(int requiredRollsA, int requiredRollsB, int[] dieCount){
        for(int dieValueA = 6; dieValueA > 0; dieValueA--){
            if(dieCount[dieValueA] >= requiredRollsA){
                for(int dieValueB = 6; dieValueB > 0; dieValueB--){
                    if(dieValueB == dieValueA) continue; //Skip checking the same value
                    if(dieCount[dieValueB] >= requiredRollsB){
                        return (requiredRollsA*(dieValueA)) + (requiredRollsB*(dieValueB));
                    }
                }
            }
        }
        return 0;
    }

    //Checks if frequencies match prerequisites for a Small Straight (1,2,3,4,5);
    private static boolean isSmallStraight(int[] dieFrequency){
        for(int dieValue = 1; dieValue <= 5; dieValue++){
            if(dieFrequency[dieValue] != 1) return false;
        }
        return true;
    }

    //Checks if frequencies match prerequisites for a Large Straight (2,3,4,5,6);
    private static boolean isLargeStraight(int[] dieFrequency){
        for(int dieValue = 2; dieValue <= 6; dieValue++){
            if(dieFrequency[dieValue] != 1) return false;
        }
        return true;
    }

    //Summarizes Rolls: Used for chance
    private static int sumRolls(int[] rolls){
        int total = 0;
        for(int dieValue : rolls){
            total += dieValue;
        }
        return total;
    }

    //Method takes rolls and counts them, returns amount of select rolls as an array
    private static int[] diceCounter(int[] rolls){
        int[] dieFrequency = new int[]{0,0,0,0,0,0,0}; //PS: Index 0 is unused to reduce uncertainty
        for(int dice : rolls){
            dieFrequency[dice]++;
        }
        return dieFrequency;
    }
}
