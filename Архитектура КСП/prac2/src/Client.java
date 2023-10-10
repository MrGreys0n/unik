import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            SquareRootSolver solver = (SquareRootSolver) registry.lookup("Solver");
            double[] a = solver.solveSquareEquation(1,-3, 2);
            for (int i = 0; i < a.length; i++){
                System.out.println(a[i]);
            }
        } catch (Exception e) {
            System.err.println("Client exception" + e.toString());
            e.printStackTrace();
        }
    }
}