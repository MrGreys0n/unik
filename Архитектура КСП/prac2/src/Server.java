import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends SquareEquationSolverImpl {
    public Server() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            SquareEquationSolverImpl im = new SquareEquationSolverImpl();
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("Solver", im);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception" + e.toString());
        }
    }
}
