package alquileres.modelo;

/**
 * Representa a un vehículo en alquiler
 * De esta clase no se crearán instancias
 * 
 * De un vehículo se conoce su matrícula, marca, modelo y el precio a pagar por
 * día de alquiler
 * 
 * Para todo vehículo se puede calcular su coste de alquiler que depende del nº
 * de días que se alquile (llamaremos a esta operación calcularPrecioAlquiler() )
 * 
 * Dos vehículos pueden compararse por su matrícula (es su orden natural)
 * 
 * Dos vehículos son iguales si además de pertenecer a la misma clase tienen la
 * misma matrícula
 * 
 */
public class Vehiculo implements Comparable<Vehiculo>{
	private String matricula;
	private String marca;
	private String modelo;
	protected double precioDia;

	/**
	 * Constructor
	 */
	public Vehiculo(String matricula, String marca, String modelo,
	        double precioDia) {
		this.matricula = matricula.toUpperCase();
		this.marca = marca.toUpperCase();
		this.modelo = modelo.toUpperCase();
		this.precioDia = precioDia;

	}
	

	public String getMatricula() {
		return matricula;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public double getPrecioDia() {
		return precioDia;
	}


	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}


	/**
	 * 
	 */
	public double calcularPrecioAlquiler(double cantDia) {
		return this.precioDia * cantDia;
		
	}

	/**
	 * Redefinición de hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		return matricula.hashCode() * 13;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Vehiculo o) {
		if(this.matricula.equalsIgnoreCase(o.matricula)){
			return 0;
		}
		if(this.matricula.compareTo(o.matricula) > 0){
			return 1;
		}
		return -1;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (obj == this){
			return true;
		}
		if (this.getClass() != obj.getClass()){
			return false;
		}
		Vehiculo o = (Vehiculo) obj;
		return this.matricula.equalsIgnoreCase(o.matricula);
	}


	@Override
	public String toString() {
		String clase = this.getClass().getName();
		String vehiculo = String.format("%s \nMatricula: %s ANV  |  Marca: %s   |  Modelo:  %s \nPrecio d�a alquiler: %f�  ",
						  clase, this.matricula, this.marca, this.modelo, this.precioDia);
		return vehiculo;
	}
}