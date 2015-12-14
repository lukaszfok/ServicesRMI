import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class ServerUslugImp extends UnicastRemoteObject implements SerwerUslug{
	
	HashMap listaUslug;
	
	public ServerUslugImp() throws RemoteException {
		konfigurujUslugi();
	}
	private void konfigurujUslugi(){
		
		listaUslug = new HashMap();
		listaUslug.put("Usługa gry w kości", new GraWKosciUsluga());
		listaUslug.put("Usługa dni tygodnia", new DzienTygodniaUsluga());
		listaUslug.put("Usługa wizualizacji muzyki", new MiniMuzykaUsluga());
		}
	
	public Object[] pobierzListeUslug(){
		
		System.out.println("zdalne");
		return listaUslug.keySet().toArray();
		}
	
	public Usluga pobierzUsluge(Object kluczUsl) throws RemoteException {
		Usluga usluga = (Usluga) listaUslug.get(kluczUsl);
		return usluga;
		}
	
	
	public static void main(String[] args) {
		try{
			Naming.rebind("SerwerUslug", new ServerUslugImp());
			}catch(Exception ex){   }
			System.out.println("Zdalna Usługa uruchomiona");
			}

}
