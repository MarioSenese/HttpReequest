package mario.dev.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import mario.dev.HttpRequest.json.Json;

/**
 *
 * @author Mario
 */
class HttpURLConnectionRequest {
    
    private String urlConnection;

    public HttpURLConnectionRequest(String urlConnection) throws IOException {
        this.urlConnection = urlConnection;
        connection(this.urlConnection);
    }
    
    public String getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(String urlConnection) {
        this.urlConnection = urlConnection;
    }
    
    /**
     *  "connection" function definition to request the connection with url api and get its response
     *  @param urlConnection the string url
     *  @throws java.io.IOException
    */
        private void connection(String urlConnection) throws IOException {

            URL url = new URL(urlConnection);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection = (connection == null) ? ( (HttpsURLConnection) url.openConnection() ) : ( connection );

            connection.setRequestMethod("GET"); // Method: PUT, GET or POST
            connection.setRequestProperty("accept", "application/json");

            int responseCode = connection.getResponseCode();

            String success, error;
            if(responseCode == HttpURLConnection.HTTP_OK) {

                success = responseSuccess(connection);
                //System.out.format("%s%n", success);
                Json.getJSON(success);

            } else {

                error = responseError(connection);
                System.err.format("%s%n", error);

            }

        }
    
    /**
     *  Get Success response
     *  @param connection
     *  @throws java.io.IOException
    */
        private static String responseSuccess(HttpURLConnection connection) throws IOException {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return response(in);

        }
    
    /**
     *  Get Error response
     *  @param connection
     *  @throws java.io.IOException
    */
        private static String responseError(HttpURLConnection connection) throws IOException {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            return response(in);

        }
    
    /**
     *  Get response
    */
        private static String response(BufferedReader in) throws IOException {

            String inputLine = ""; 
            StringBuilder response = new StringBuilder();

            while( (inputLine = in.readLine()) != null ) {

                response.append(inputLine);

            }

            in.close();

            return response.toString();

        }
    
}
