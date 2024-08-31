package ar.edu.info.oo2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;

/**
 *
 * @author frank
 */
public class Mamifero {

	private String identificador;
	private String especie;
	private LocalDate fechaNacimiento;
	private Mamifero padre;
	private Mamifero madre;

	public Mamifero(String identificador) {
		this.identificador = identificador;
	}

	public Mamifero() {
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Mamifero getPadre() {
		return padre;
	}

	public void setPadre(Mamifero padre) {
		this.padre = padre;
	}

	public Mamifero getMadre() {
		return madre;
	}

	public void setMadre(Mamifero madre) {
		this.madre = madre;
	}

	public Mamifero getAbueloMaterno() {
		return this.getMadre().getPadre();
	}

	public Mamifero getAbuelaMaterna() {
		return this.getMadre().getMadre();

	}

	public Mamifero getAbueloPaterno() {
		return this.getPadre().getPadre();
	}

	public Mamifero getAbuelaPaterna() {
		return this.getPadre().getMadre();
	}

	public boolean tieneComoAncestroA(Mamifero unMamimefero) {
		boolean esta = false;
		Mamifero papa = this.getPadre();
		if (!esta) {
			esta = papa.tieneComoAncestroA(unMamimefero);
		}
		if (!esta) {
			Mamifero mama = this.getMadre();
			esta = (unMamimefero == mama);
			if (!esta) {
				esta = mama.tieneComoAncestroA(unMamimefero);
			}
		}
		return esta;
	}

}
