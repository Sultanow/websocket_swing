package websocket;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Main {

	// We used CountDownLatch to make sure that main thread does not exit after
	// executing the code. The main thread waits till the time latch decrements the
	// counter in onClose() method.
	private static CountDownLatch latch;

	private static JFrame frame = new JFrame("Ahlehkro");
	private static JTextArea textArea = new JTextArea();
	
	public static void main(String[] args) throws IOException, DeploymentException {
		initUI();
		
		latch = new CountDownLatch(1);
		
		String uri = "ws://localhost:1337/";
		System.out.println("Connecting to " + uri);

		// open websocket
		final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(URI.create(uri));
		//clientEndPoint.sendMessage("{''}");
	}

	private static void initUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.add(new JLabel("ALLEGRO - Java Swing RichClient"));
		panel.add(new JList<String>(new String[]{"BG 17627","BG 89796"}));
		textArea.setPreferredSize(new Dimension(200,400));
		textArea.setBorder(BorderFactory.createEtchedBorder());
		panel.add(textArea);
		panel.add(new JButton("Anordnen"));
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	@javax.websocket.ClientEndpoint
	public static class WebsocketClientEndpoint {

		Session userSession = null;

		public WebsocketClientEndpoint(URI endpointURI) {
			try {
				WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				container.connectToServer(this, endpointURI);
				latch.await();
			} catch (DeploymentException | IOException | InterruptedException e) {
	            throw new RuntimeException(e);
	        }
		}

		/**
		 * Callback hook for Connection open events.
		 *
		 * @param userSession the userSession which is opened.
		 */
		@OnOpen
		public void onOpen(Session userSession) {
			System.out.println("opening websocket");
			this.userSession = userSession;
		}

		/**
		 * Callback hook for Connection close events.
		 *
		 * @param userSession the userSession which is getting closed.
		 * @param reason      the reason for connection close
		 */
		@OnClose
		public void onClose(Session userSession, CloseReason reason) {
			System.out.println("closing websocket");
			this.userSession = null;
			latch.countDown();
		}

		/**
		 * Callback hook for Message Events. This method will be invoked when a client
		 * send a message.
		 *
		 * @param message The text message
		 */
		@OnMessage
		public void onMessage(String message) {
			JsonParserFactory jsonParserFactory = Json.createParserFactory(null);
			JsonParser jsonParser = jsonParserFactory.createParser(new StringReader(message));
			boolean data = false;
			while(jsonParser.hasNext()) {
				Event e = jsonParser.next();
				if (Event.KEY_NAME.equals(e) && "data".equals(jsonParser.getString())) {
					data = true;
				}
				if (data && Event.VALUE_STRING.equals(e)) {
					System.out.println();
					textArea.setText(jsonParser.getString());
				}
			}
		}

		public void sendMessage(String message) {
			this.userSession.getAsyncRemote().sendText(message);
		}
	}
}
