import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SerwerUslug extends Remote {
	Object[] pobierzListeUslug() throws RemoteException;
	Usluga pobierzUsluge(Object serviceKey) throws RemoteException;
}
