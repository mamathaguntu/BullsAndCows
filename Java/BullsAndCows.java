package BullsAndCows.Java;

import java.util.ArrayList;
import java.util.HashMap;

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        String  res = "";
        int     num_bulls = 0, 
                num_cows  = 0, 
                old_val   = 0, 
                new_val   = 0;
        char[]  ch1 = secret.toCharArray();
        char[]  ch2 = guess.toCharArray();
        HashMap<Character, Integer> hmap = new HashMap<Character, Integer>();
        ArrayList<Character> num_cowsList = new ArrayList<Character>();
        
        //Check for Bulls in the guess code 
        for (int i = 0; i < ch2.length; i++)
        {
            if (ch1[i] == ch2[i])
            {
                num_bulls++;
            }
            else
            {
                if (hmap.size() != 0 && hmap.containsKey(ch1[i]))
                {
                    old_val = hmap.get(ch1[i]);
                    new_val = old_val + 1;
                    hmap.replace(ch1[i], old_val, new_val);
                }
                else
                {
                    hmap.put(ch1[i], 1);
                }
                num_cowsList.add(ch2[i]);
            }
        }
        // Check for Cows
        for (int i = 0; i < num_cowsList.size(); i++)
        {
            char key = num_cowsList.get(i);
            
            if (hmap.size() != 0 && hmap.containsKey(key) && hmap.get(key) > 0)
            {
                old_val = hmap.get(key);
                new_val = old_val - 1;
                hmap.replace(key, old_val, new_val);
                num_cows++;
            }
        }
        
        res = String.format("%dA%dB", num_bulls, num_cows);
        return res;
    }

    public static void main(String[] args) {
        String secret = "1122", guess = "1222";
        BullsAndCows bc = new BullsAndCows();
        String res = bc.getHint(secret, guess);
        System.out.println(res);
    }
    
}
