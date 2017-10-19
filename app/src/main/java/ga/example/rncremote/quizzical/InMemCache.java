package ga.example.rncremote.quizzical;

import java.util.HashMap;

/**
 * Created by rudraneel on 10/19/2017.
 */

public class InMemCache {

    private static HashMap<String, Object> memMap = new HashMap<>();


    public static Object getElementByKey(String key) {

        if (memMap.containsKey(key)) {
            return memMap.get(key);
        } else {
            return null;
        }


    }


}
