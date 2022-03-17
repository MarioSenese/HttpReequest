package mario.dev.HttpRequest;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mario.dev.HttpRequest.json.Json;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mario
 */
class ApacheHttpClient {
    
    private String urlConnection;

    public ApacheHttpClient(String urlConnection) {
        this.urlConnection = urlConnection;
        connection();
    }

    public String getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(String urlConnection) {
        this.urlConnection = urlConnection;
    }
    
    /**
     *  "connection" function definition to request the connection with url api and get its response
    */
        private void connection() {

            HttpClient client;
            HttpResponse response;

            try {

                client = HttpClientBuilder.create().build(); // Instantiating a client that can make HTTP calls

                /**
                 * Create GET request
                */

                    HttpGet request = new HttpGet(this.urlConnection); // Create HttpGet request
                    request.addHeader("accept", "application/json");

                /**
                 *  Create POST request
                */

                    // HttpPost request = new HttpPost(this.urlConnection); // Create HttpPost request
                    // StringEntity params = new StringEntity("{ ... }"); // add params
                    // request.addHeader("content-type", "text/plain");
                    // request.setEntity(params);

                /**
                 *  Create response
                */

                    response = client.execute(request);

                /**
                 *  Result response
                */

                    if(response.getStatusLine().getStatusCode() == 200) {

                        HttpEntity entity = response.getEntity();
                        String content = EntityUtils.toString(entity);
                        Json.getJSON(content);

                    } else {

                        System.err.format("%s%n", response.getEntity());

                    }

            } catch(UnknownHostException ex) {
                Logger.getLogger(ApacheHttpClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } catch(IOException ex) {
                Logger.getLogger(ApacheHttpClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    
}
