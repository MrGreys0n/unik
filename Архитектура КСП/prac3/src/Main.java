public class Main {
    public static void main(String[] args) {
        // Запуск сервера в отдельном потоке
        // Thread serverThread = new Thread(Server::startBroadcasting);
        // serverThread.start();
        //Server.startServer();

        // Запуск клиентов
        Client.main(args);
    }
}
