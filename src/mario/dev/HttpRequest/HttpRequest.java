package mario.dev.HttpRequest;

import java.io.IOException;


/**
 * @author Mario
*/
public class HttpRequest {
    
    public static final String API_KEY = "[API_KEY]";
    public static final String URL_API = "[URL_API] + [API_KEY]";
    
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
