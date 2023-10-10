import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final int SERVER_PORT = 12345;
    private static final int BROADCAST_DELAY = 5000;

    private static final List<Socket> clients = new CopyOnWriteArrayList<>();
    private static final List<String> messages = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is up on port " + SERVER_PORT);

            // Создаем таймер для рассылки сообщений
            Timer timer = new Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendBroadcastMessages();
                }
            }, 0, BROADCAST_DELAY);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);

                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = reader.readLine()) != null) {
                messages.add(message);
                System.out.println("Received message from client: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clients.remove(clientSocket);
        }
    }

    private static void sendBroadcastMessages() {
        if (!messages.isEmpty()) {
            System.out.println("Sending message to clients...");
            String combinedMessage = String.join("\n", messages);
            for (Socket client : clients) {
                try {
                    PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                    writer.println(combinedMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            messages.clear();
        }
    }
}
