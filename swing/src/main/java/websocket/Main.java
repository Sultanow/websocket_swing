package websocket;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Main {

	public static void main(String[] args) throws IOException, DeploymentException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://localhost:1337/";
		System.out.println("Connecting to " + uri);
		container.connectToServer(ClientEndpoint.class, URI.create(uri));
	}

	@javax.websocket.ClientEndpoint
	class ClientEndpoint {

		@OnOpen
		public void onOpen(Session session) {
			try {
				String name = "Duke";
				System.out.println("Sending message to endpoint: " + name);
				session.getBasicRemote().sendText(name);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		@OnMessage
		public void processMessage(String message) {
			System.out.println("Received message in client: " + message);

		}

		@OnError
		public void processError(Throwable t) {
			t.printStackTrace();
		}
	}
}
