package no.kristiania.yahtzee;

//Production File

class Yahtzee {
    static int getScore(Category category, int[] rolls){
        int[] dieCount = diceCounter(rolls); //Tracks roll results: Index 1 = Dice with 1's, etc.

        switch (category){
            case ONES: return dieCount[1];
            case TWOS: return dieCount[2]*2;
            case THREES: return dieCount[3]*3;
            case FOURS: return dieCount[4]*4;
            case FIVES: return dieCount[5]*5;
            case SIXES: return dieCount[6]*6;
            case PAIR: return findOfKind(2,dieCount);
            case TWOPAIR: return findTwoOfKind(2,2, dieCount);
            case THREEOFKIND: return findOfKind(3,dieCount);
            case FOUROFKIND: return findOfKind(4,dieCount);
            case FULLHOUSE: return findTwoOfKind(2,3, dieCount) > 0 ? 25 : 0;
            case SMALLSTRAIGHT: return dieCount[1] == 1 && dieCount[2] == 1 && dieCount[3] == 1 && dieCount[4] == 1 && dieCount[5] == 1 ? 30 : 0;
            case LARGESTRAIGHT: return dieCount[2] == 1 && dieCount[3] == 1 && dieCount[4] == 1 && dieCount[5] == 1 && dieCount[6] == 1 ? 40 : 0;
            case CHANCE: return rolls[0] + rolls[1] + rolls[2] + rolls[3] + rolls[4];
            case YAHTZEE: return findOfKind(5,dieCount) > 0 ? 50 : 0;
            default:
                System.err.println("Error: No category was selected");
                return 0;
        }
    }

    //Method takes in a required amount of same roll die, if nothing is met gives 0.
    private static int findOfKind(int requiredDice, int[] dieCount){
        for(int eyes = 6; eyes > 0; eyes--){
            if(dieCount[eyes] >= requiredDice){
                return requiredDice*(eyes);
            }
        }
        return 0;
    }

    //Method takes in a required amount of same roll die TWICE, if both are not met it gives 0.
    private static int findTwoOfKind(int requiredRollsA, int requiredRollsB, int[] dieCount){
        for(int eyesA = 6; eyesA > 0; eyesA--){
            if(dieCount[eyesA] >= requiredRollsA){
                for(int eyesB = 6; eyesB > 0; eyesB--){
                    if(eyesB == eyesA) continue; //Skip checking the same value
                    if(dieCount[eyesB] >= requiredRollsB){
                        return (requiredRollsA*(eyesA)) + (requiredRollsB*(eyesB));
                    }
                }
            }
        }
        return 0;
    }

    //Method takes rolls and counts them, returns amount of select rolls as an array
    private static int[] diceCounter(int[] rolls){
        int[] dieCount = new int[]{0,0,0,0,0,0,0}; //PS: Index 0 is unused to reduce uncertainty
        for(int dice : rolls){
            dieCount[dice]++;
        }
        return dieCount;
    }
}
