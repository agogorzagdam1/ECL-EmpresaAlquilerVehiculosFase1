package alquileres.modelo;

/**
 * Un coche es un vehículo que añade un nº de plazas
 * 
 * El coste final de alquiler depende no solo del nº de días de alquiler 
 * sino también del nº de plazas. Si el nº de plazas es > 4 se añaden 5€ más por día
 */
public class Coche extends Vehiculo {
	private int nPlazas;
	
	/**
	 * 
	 * Constructor
	 */
	public Coche(int nPlazas, String matricula, String marca, String modelo,
	        double precioDia) {
		super(matricula, marca, modelo, precioDia);
		this.nPlazas = nPlazas;
	}

	public int getnPlazas() {
		return nPlazas;
	}

	
	public double calcularPrecioAlquiler(double cantDia) {
		if(this.nPlazas > 4) {
			super.setPrecioDia(precioDia + 5);
			
		}
		return super.calcularPrecioAlquiler(cantDia);
	}

	@Override
	public String toString() {
		return super.toString() + "|  Plazas: " + nPlazas 
		+ "\n----------------------------------------------------------------";
	}
	
	
}
