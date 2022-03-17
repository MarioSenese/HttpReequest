package mario.dev.HttpRequest;

import java.io.IOException;


/**
 * @author Mario
*/
public class HttpRequest {
    
    public static final String API_KEY = "cc2655d5455270553103"; //cc2655d5455270553103";
    public static final String URL_API = "https://www.balldontlie.io/api/v1/players"; //http://free.currconv.com/api/v7/currencies?apiKey=" + API_KEY;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException 
    */
        public static void main(String[] args) throws IOException, InterruptedException {

            // Use java.net.HttpURLConnection
                HttpURLConnectionRequest httpURLConnection = new HttpURLConnectionRequest(URL_API);

            // Use java.net.http.HttpClient;
                HttpClientRequest httpClient = new HttpClientRequest(URL_API);

            // Use Apache HttpClient library: org.apache.http.*
                ApacheHttpClient apacheHttpClient = new ApacheHttpClient(URL_API);

        }

}
