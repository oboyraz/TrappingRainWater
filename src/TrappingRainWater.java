import java.util.ArrayList;
import java.util.List;

public class TrappingRainWater {
    /*
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

    to see question => https://leetcode.com/problems/trapping-rain-water/
     */
    //static int[] height = {1,0,0,1,2,5,6,0,6};
    //static int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
    //static int[] height = {5,5,1,7,1,1,5,2,7,6};
    static int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
    //static int[] height = {10,6,7,7,10};
    //static int[] height = {5,4,1,2};
    //static int[] height = {1,0,0,2};
    //static int[] height = {9,8,2,1,6};
    static int waterVolume = 0;
    public static void main(String[] args) {
        boolean nextNumberCheck;
        for (int i = 0; i < height.length-1; i++) {
            List<Integer> controlList = new ArrayList<>();
            nextNumberCheck = true;
            if (height[i]<height[i+1]){
                nextNumberCheck = false;
            }
            if (nextNumberCheck){
                int sayi = height[i];
                controlList = EqualorGreaterNumberCheck(sayi,i);
                if (controlList.size()==3){
                    waterVolume = calculateWaterVolume(controlList.get(0), controlList.get(1), controlList.get(2));
                    i=(controlList.get(2)-1);
                }
            }
        }
        System.out.println("Total water volume: " + waterVolume);
    }
    private static int calculateWaterVolume(Integer NumbertoCheck, Integer IndexofNumberstoCheck, Integer indexofEqualorGreaterNumber) {
        int totalWaterVolume = 0;
        for (int i = IndexofNumberstoCheck+1; i < indexofEqualorGreaterNumber; i++) {
            totalWaterVolume=(NumbertoCheck-height[i]);
            waterVolume+=totalWaterVolume;
        }
        return waterVolume;
    }
    public static List<Integer> EqualorGreaterNumberCheck(int NumbertoCheck, int IndexofNumberstoCheck) {
        List<Integer> outputs = new ArrayList<>();
        boolean control = false;
        if (IndexofNumberstoCheck == (height.length-1)){
            System.out.println("Total water volume: " + waterVolume);
            System.exit(-1);
        }
        if (NumbertoCheck==height[IndexofNumberstoCheck+1]){
            outputs = EqualorGreaterNumberCheck(height[IndexofNumberstoCheck+1],(IndexofNumberstoCheck+1));
        }
        else{
            for (int j = IndexofNumberstoCheck+1; j < height.length; j++) {
                control = false;
                if (NumbertoCheck<=height[j]){
                    outputs.add(NumbertoCheck);
                    outputs.add(IndexofNumberstoCheck);
                    outputs.add(j);
                    break;
                } else if (NumbertoCheck>height[j]) {
                    control = true;
                }
            }
            if (control){
                NumbertoCheck--;
                outputs = EqualorGreaterNumberCheck(NumbertoCheck,IndexofNumberstoCheck);
            }
        }
        return outputs;
    }
}