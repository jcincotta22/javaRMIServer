import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends ImplHello  {
    public Server() {}
    public static void main(String[] args) throws Exception {
        try {
            ArrayList<String> textRequested = new ArrayList<>();
            textRequested.add("example1");
            textRequested.add("example2");


            // Instantiating the implementation class
            ImplHello obj = new ImplHello();

            obj.setText(textRequested);

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
