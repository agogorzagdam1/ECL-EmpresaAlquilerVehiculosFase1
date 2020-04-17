package alquileres.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * La clase guarda en una colección List (un ArrayList) la flota de vehículos
 * que una agencia de alquiler posee
 * 
 * Los vehículos se modelan como un interface List que se instanciará como una
 * colección concreta ArrayList
 */
public class AgenciaAlquiler {
	private String nombre; // el nombre de la agencia
	private List<Vehiculo> flota; // la lista de vehículos

	/**
	 * Constructor
	 * 
	 * @param nombre el nombre de la agencia
	 */
	public AgenciaAlquiler(String nombre) {
		this.nombre = nombre;
		this.flota = new ArrayList<>();
	}

	/**
	 * añade un nuevo vehículo solo si no existe
	 * 
	 */
	public void addVehiculo(Vehiculo o) {
		if(!flota.contains(o)){
			flota.add(o);
		}
	}

	/**
	 * Extrae los datos de una línea, crea y devuelve el vehículo
	 * correspondiente
	 * 
	 * Formato de la línea:
	 * C,matricula,marca,modelo,precio,plazas para coches
	 * F,matricula,marca,modelo,precio,volumen para furgonetas
	 * 
	 * 
	 * Asumimos todos los datos correctos. Puede haber espacios antes y después
	 * de cada dato
	 */
	private Vehiculo obtenerVehiculo(String vehiculo) {

		String[] info = vehiculo.split(",");
		String[] datos = new String[info.length];
		for(int i = 0; i < info.length; i++){
			datos[i] = info[i].trim();
		}
		if(datos[0].equalsIgnoreCase("C")){
			return new Coche(Integer.parseInt(datos[5]), datos[1], datos[2], datos[3],
					Double.parseDouble(datos[4]));
		}else{
			return new Furgoneta(Double.parseDouble(datos[5]), datos[1], datos[2], datos[3],
					Double.parseDouble(datos[4]));
		}
	}

	/**
	 * La clase Utilidades nos devuelve un array con las líneas de datos
	 * de la flota de vehículos  
	 */
	public void cargarFlota() {
		String[] datos = Utilidades.obtenerLineasDatos();
		String error = null;
		try {
			for (String linea : datos) {
				error = linea;
				Vehiculo vehiculo = obtenerVehiculo(linea);
				this.addVehiculo(vehiculo);

			}
		}
		catch (Exception e) {
			System.out.println(error);
		}

	}

	/**
	 * Representación textual de la agencia
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Veh�culos en alquiler de la agencia " + nombre);
		sb.append("\nTotal veh�culos " + flota.size() + "\n" + "\n");
		for (Vehiculo vehiculo: flota) {
			sb.append(vehiculo.toString() + "\n" + 
					"\n------------------------------------------------------\n");
		}
		return sb.toString();
	}

	/**
	 * Busca todos los coches de la agencia
	 * Devuelve un String con esta información y lo que
	 * costaría alquilar cada coche el nº de días indicado * 
	 *  
	 */
	public String buscarCoches(int dias) {

		StringBuilder sb = new StringBuilder();
		for(Vehiculo vehiculo: flota){
			if(vehiculo instanceof Coche){
				sb.append(vehiculo.toString() + "\nCoste alquiler " + dias + " d�as: " + 
						vehiculo.calcularPrecioAlquiler(dias) + "�" + "\n" + 
						"\n------------------------------------------------------\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Obtiene y devuelve una lista de coches con más de 4 plazas ordenada por
	 * matrícula - Hay que usar un iterador
	 * 
	 */
	public List<Coche> cochesOrdenadosMatricula() {

		Iterator<Vehiculo> it = flota.iterator();
		List<Coche> ordenados = new ArrayList<>();
		while(it.hasNext()){
			Vehiculo aux = it.next();
			if(aux instanceof Coche && ((Coche) aux).getnPlazas() > 4){
				ordenados.add((Coche) aux);
			}
		}
		Collections.sort(ordenados);
		return ordenados;
	}

	/**
	 * Devuelve la relación de todas las furgonetas ordenadas de
	 * mayor a menor volumen de carga
	 * 
	 */
	public List<Furgoneta> furgonetasOrdenadasPorVolumen() {
		List<Furgoneta> ordenados = new ArrayList<>();
		for (Vehiculo vehiculo: flota) {
			if(vehiculo instanceof Furgoneta) {
				ordenados.add((Furgoneta) vehiculo);
			}
		}
		Collections.sort(ordenados, new Comparator<Furgoneta>()
		{
		public int compare(Furgoneta f1, Furgoneta f2){
			return Double.compare(f1.getVolumen(), f2.getVolumen());
			}
		});
		return ordenados;

	}

	/**
	 * Genera y devuelve un map con las marcas (importa el orden) de todos los
	 * vehículos que hay en la agencia como claves y un conjunto (importa el orden) 
	 * de los modelos en cada marca como valor asociado
	 */
	public Map<String, Set<String>> marcasConModelos() {
		
		//No s� hacerlo.
		return null;
	}

}
