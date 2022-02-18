import java.util.ArrayList;
public class Sort {
    public static void selectionSortWordList(ArrayList<String> words)
    {
        for(int i = 0; i < words.size()-1;i++)
        {
            int checkDex = i;
            for(int g = i+1; g <words.size();g++)
            {
                if(words.get(g).compareTo(words.get(checkDex)) < 0)
                {
                    checkDex = g;
                }
            }
            String t = words.get(i);
            words.set(i,words.get(checkDex));
            words.set(checkDex,t);
        }
    }

}
