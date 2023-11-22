package main;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import main.Entidades.Cliente;
import main.Entidades.Especialidad;
import main.Entidades.Estado;
import main.Entidades.Incidente;
import main.Entidades.Tecnico;
import main.Entidades.ServicioContratado;
import javax.persistence.EntityTransaction;


public class IntegradorGrupo5Main {

	public static EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");
        EntityManager manager = factory.createEntityManager();

        return manager;
    }
	
	public static void main(String[] args) {
		                
        int opcionprincipal = 0;
		Scanner teclado = new Scanner (System.in);
		
		System.out.println ("** Bienvenido al sistema de gestión de Incidentes. **");
		
		do 
		{
			System.out.println ("Por favor seleccione el sector correspondiente");
			System.out.println ("1 - Sector Comercial");
			System.out.println ("2 - Sector RRHH");
			System.out.println ("3 - Sector Mesa Ayuda");
			System.out.println ("4 - Sector Tecnico");
			System.out.println ("5 - Sector Cliente");
			System.out.println ("6 - Sector Informes");
			System.out.println ("7 - Salir del sistema");
		
			opcionprincipal = teclado.nextInt();
				
			switch (opcionprincipal) { 
				case 1: //SECTOR COMERCIAL
					int opcioncomercial = 0;
					do
					{
						System.out.println ("** Bienvenido al sector comercial. **");
						System.out.println ("Que desea realizar:");
						System.out.println ("1 - Alta de Cliente");
						System.out.println ("2 - Modificacion de Cliente");
						System.out.println ("3 - Baja de Cliente");
						System.out.println ("4 - Voler al menu principal");
						
						opcioncomercial = teclado.nextInt();
						
						switch (opcioncomercial) {
						case 1:
							EntityManager em = getEntityManager();
					        EntityTransaction tx = em.getTransaction();
					        tx.begin();
					        ServicioContratado servicio= ServicioContratado.PACK_BASICO;
					        Cliente cliente = new Cliente(0 ,"F", "Calle Secundaria", 987654321, "maria@example.com", 1234567890, 1, servicio);
					        Cliente cliente2 = new Cliente(1, "P", "Calle Prueba", 1234456, "prueba@example.com", 1234, 2, servicio);
					        cliente2.altaCliente();        
					        em.persist(cliente);
					        em.persist(cliente2);
					        tx.commit();
							break;
						case 2:
							System.out.println ("Por favor Ingrese el id de Cliente a modificar");
							//Chequear que el id exista
							//Consultar que dato quiere modificar y armar otro menu con swicth llamando a setter 
							break;
						case 3:
							System.out.println ("Por favor Ingrese el id de Cliente a dar de baja");
							//Chequear que el id exista y se podria chequear que no tenga incidentes pendientes
							//Cambiar estado
							break;
						case 4:
							break;
						default:
							System.out.println ("La opción ingresada no es valida por favor ingrese una opción valida.");
						}
					} while (opcioncomercial != 4);
					break;
				case 2:	//SECTOR TECNICO
					int opcionRRHH = 0;
					do
					{
						System.out.println ("Bienvenido al sector RRHH. Que desea realizar:");
						System.out.println ("1 - Alta de Tecnico");
						System.out.println ("2 - Modificacion de Tecnico");
						System.out.println ("3 - Baja de Tecnico");
						System.out.println ("4 - Voler al menu principal");
						
						opcionRRHH = teclado.nextInt();
						
						switch (opcionRRHH) {
						case 1:
							//Constructor tecnico 
							//Tecnico tecnico = new Tecnico(null, null, null, null, null, null);
							break;
						case 2:
							System.out.println ("Por favor Ingrese el id de Tecnico a modificar");
							//Chequear que el id exista
							//Consultar que dato quiere modificar y armar otro menu con swicth llamando a setter 
							break;
						case 3:
							System.out.println ("Por favor Ingrese el id de Tecnico a dar de baja");
							//Chequear que el id exista y se podria chequear que no tenga incidentes pendientes
							//Cambiar estado
							break;
						case 4:
							break;
						default:
							System.out.println ("La opción ingresada no es valida por favor ingrese una opción valida.");
						}
					} while (opcionRRHH != 4);
					break;
				case 3: //SECTOR MESA DE AYUDA
					int opcionMesaAyuda = 0;
					do
					{
						System.out.println ("Bienvenido al sector de Mesa de Ayuda. Que desea realizar:");
						System.out.println ("1 - Consultar Cliente");
						System.out.println ("2 - Cargar Incidente");
						System.out.println ("3 - Voler al menu principal");
						
						opcionMesaAyuda = teclado.nextInt();
						
						switch (opcionMesaAyuda) {
						case 1:
							//Consultar si existe el cliente y mostrar el servicio contrado
							//Sino existe derivar al sector comercial
							break;
						case 2:
							Incidente incidente = new Incidente (null, null, null, null, null, null, null);
							//Chequear que el id exista
							//Consultar que dato quiere modificar y armar otro menu con swicth llamando a setter 
							break;
						case 3:
							break;
						default:
							System.out.println ("La opción ingresada no es valida por favor ingrese una opción valida.");
						}
					} while (opcionMesaAyuda != 3);
					break;
				case 4: //SECTOR TECNICO
				case 5: //SECTOR CLIENTE
				case 6: //SECTOR INFORME
				case 7: //SALIR DEL SISTEMA
					System.out.println ("Muchas gracias por su visita");
					teclado.close();
					break;
				default:
					System.out.println ("La opción ingresada no es valida por favor ingrese una opción valida.");
			}
		} while (opcionprincipal != 7);	
		//List<Especialidad> especialidad = new ArrayList<>();
        //especialidad.add(Especialidad.GOOGLE);
        //Tecnico tecnico = new Tecnico (1,"Tecnico1","Prueba",1234,"tecnico1@gmail.com",1,especialidad);
		//List<Tecnico> tecnicos = new ArrayList<>();
		//tecnicos.add(tecnico);
		//em.persist(tecnico);
		//Estado estado = Estado.EN_CURSO;
		//Incidente incidente = new Incidente ("1","Prueba1","1 hora","Prueba",cliente, tecnicos,estado);
		//em.persist(incidente);
       
	}
}
