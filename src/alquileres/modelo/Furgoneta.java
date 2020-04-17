package alquileres.modelo;

/**
 * Una furgoneta es un vehículo que añade la característica del volumen de carga
 * (valor de tipo double)
 * 
 * El coste de alquiler de una furgoneta no solo depende del nº de días de
 * alquiler
 * Tendrá un incremento que dependerá de su volumen de carga: hasta 5 m3
 * exclusive ( metros cúbicos) de volumen el incremento sobre el precio
 * final será de 10€, entre 5 y 10 (inclusive) el incremento sobre el precio
 * final será de 15€, si volumen > 10 el incremento sobre el precio final será de
 * 25€
 * 
 */
public class Furgoneta extends Vehiculo{
	private double volumen;

	public Furgoneta(double volumen, String matricula, String marca, String modelo,
	        double precioDia) {
		super(matricula, marca, modelo, precioDia);
		this.volumen = volumen;
	}

	public double getVolumen() {
		return volumen;
	}
	
	public double calcularPrecioAlquiler(double cantDia) {
		if(this.volumen < 5){
			super.setPrecioDia(precioDia + 10);
		}
		if(this.volumen >= 5 &&  this.volumen <= 10){
			super.setPrecioDia(precioDia + 15);
		}
		if(this.volumen > 10){
			super.setPrecioDia(precioDia + 25);
		}
		return super.calcularPrecioAlquiler(cantDia);
	}

	@Override
	public String toString() {
		return super.toString() + "|  Volumen: " + this.volumen + "(m3)" 
	+ "\n----------------------------------------------------------------";
	}
	
}
