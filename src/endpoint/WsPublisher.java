package endpoint;
import ws.AppController;

import javax.xml.ws.Endpoint;

public class WsPublisher {

    public static void main(String[] args) throws Exception {
        Endpoint.publish("http://localhost:8080/ws/CarSoap", new AppController());
        System.out.println("Server is running...");
    }
}
