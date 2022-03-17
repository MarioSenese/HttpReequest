package mario.dev.HttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mario.dev.HttpRequest.json.Json;

/**
 *
 * @author Mario
 */
class HttpClientRequest {
    
    private String urlConnection; 

    public HttpClientRequest(String urlConnection) throws IOException, InterruptedException {
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
     *  @throws java.io.IOException
     *  @throws java.lang.InterruptedException
    */
        private void connection() throws IOException, InterruptedException {

            HttpClient client = HttpClient.newHttpClient();

            synchronousRequestGET(client, this.urlConnection);
            aynchronousRequestGET(client, this.urlConnection);

        }
    
    /**
     *  "synchronousRequestGET" function definition to synchronous request
    */
        private void synchronousRequestGET(HttpClient client, String uri) throws IOException, InterruptedException {

            //HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("accept", "application/json").build(); // GET is default
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("accept", "application/json").GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String body = response.body();

            if(statusCode == 200) {
                //System.out.format("%s%n", body);
                Json.getJSON(body);
            } else {
                System.err.format("%s%n", body);
            }

        }
        
    /**
     *  "aynchronousRequestGET" function definition to asynchronous request
    */
        private void aynchronousRequestGET(HttpClient client, String uri) {

            //HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("accept", "application/json").build(); // GET is default
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("accept", "application/json").GET().build();
            CompletableFuture<HttpResponse<String>> response = null;
            response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = 0;
            String body = "";
            try {

                statusCode = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::statusCode).get(5, TimeUnit.SECONDS);
                body = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
                if(statusCode == 200) {
                    //System.out.format("%s%n", body);
                    Json.getJSON(body);
                } else {
                    System.err.format("%s%n", body);
                }

            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                Logger.getLogger(HttpClientRequest.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

    
    
}
