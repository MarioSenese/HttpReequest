package mario.dev.HttpRequest.json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mario
 */
public class Json {
    
    /**
     *  Get JSON result from string.Use JSON-Java library
     *  @param success
    */
        public static void getJSON(String success) {

            JSONObject json = new JSONObject(success); // Get JSON from string
            JSONArray results = json.getJSONArray("data"); // if it is an array then we write "json.getJSONArray(key)"

            //Set<String> arrayKeySet = json.keySet();
            json.keys().forEachRemaining(key -> {
                Object value = json.get(key);
                handleValue(value);
            });

        }

    /**
     *  If it's an object, print the property and its value
     *  
     *  Es: 
     *      {
     *          "name"      :   "Mario", 
     *          "surname"   :   "Senese",
     *          "email"     :   "senese.mario90@gmail.com"
     *      }
    */
        private static void jsonObject(JSONObject jsonObject) {

            jsonObject.keys().forEachRemaining(key -> {

                /*if(jsonObject.get(key).equals(14)) {
                    System.out.println(jsonObject);
                }*/

                System.out.println(key + ":\t" + jsonObject.get(key));

            });

        }
    
    /**
     *  If it's not an object but an array then iterate the array and call handleValue function, 
     *  to get the properties and its values.
     *  
     *  Es: 
     *      {
     *        "users"    :   [
     * 
     *            {
     *               "name"      :   "Mario", 
     *               "surname"   :   "Senese",
     *               "email"     :   "senese.mario90@gmail.com"
     *            },
     *            {
     *               "name"      :   "Luca", 
     *               "surname"   :   "Rossi",
     *               "email"     :   "luca.rossi@live.com"
     *            },
     * 
     *            ..
     *            ..
     *            ..
     * 
     *            {...} 
     * 
     *          ]
     *      }
    */
        private static void jsonArray(JSONArray jsonArray) {

            jsonArray.iterator().forEachRemaining(element -> {
               handleValue(element);
            });

        }
    
    /**
     *  Fuction definiton for check if value is an object or an array
    */
        private static void handleValue(Object value) {

            if(value instanceof JSONObject) {
                jsonObject((JSONObject)value);
            } else if(value instanceof JSONArray) {
                jsonArray((JSONArray)value);
            } else {
                System.out.println(value);
            }    

        }
    
}
