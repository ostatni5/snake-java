package agh.ostatni5.snake.my;

import java.util.Random;

public class MyArrays {
    private static Random random = new Random();
    public static void shuffleArray(int[] array)
    {
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = random.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }
    public static int [] add(int[] array,int[] array2){
        int[] array3 = new int[array.length];
        for (int i = 0; i <array.length ; i++) {
            array3[i]=array[i]+array2[i];
        }
        return array3;
    }
    public static int getIndexOfMax(int [] array)
    {
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt;
    }
}

