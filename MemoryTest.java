import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class MemoryTest {
    public static int num = 0;
    public static int level = 1;
    public static void memory(int level) {
            int[] symbols = new int[level];
            Random newRand = new Random();
            for (int i = 0; i < level; i++) {
                int returner = newRand.nextInt(5)+1;
                symbols[i] = returner;
            }
            int setNum = newRand.nextInt(9)+1;
            symbols[symbols.length-1] = setNum;
            Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                     System.out.print(symbols[num]);
                     num++;
                     if (num >= level) {
                        System.out.println("\033[H\033[2J");
                         timer.cancel();
                         boolean checker = checker(symbols, setNum);
                         if (checker) {
                            System.out.println("Correct.");
                            boolean doAgain = doAgain(level);
                                if (doAgain) {
                                    num = 0;
                                    memory(level+1);
                                    } else if (!doAgain) {
                                    System.out.println("Thank you for testing your memory.");
                                    }
                         } else if (!checker) {
                             System.out.println("Incorrect. The correct order was " + Arrays.toString(symbols) + ".");
                         }   
                       }
                    }
                 }, 0, 1 * 1000);

    }
    public static boolean doAgain(int level) {
        Scanner nextLevel = new Scanner(System.in);
        System.out.println("Do you want to go to level " + (level+1) + "? (Yes or no): ");
        String response = nextLevel.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checker(int[] symbols, int setNum) {
        Scanner inOrder = new Scanner(System.in);
        System.out.println("What were the numbers in order? (Type " + setNum + " after you type everything else) (IF YOU WERE ON LEVEL 1, JUST TYPE " + setNum + "): ");
        String res = inOrder.nextLine();
        String[] resArr = res.split("");
        int[] newArrInt = new int[resArr.length];
        for (int i = 0; i < resArr.length; i++) {
            newArrInt[i] = Integer.parseInt(resArr[i]);
        }
            if (Arrays.equals(newArrInt, symbols)) {
                return true;
            } else {
                return false;
            }
    }
    public static void main(String[] args) {            
        memory(level);
    }
}
