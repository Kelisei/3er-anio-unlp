package ar.edu.info.oo2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author frank
 */
import java.time.LocalDate;

public interface MamiferoInterfaz {

    public abstract String getIdentificador();

    public abstract void setIdentificador(String id);

    public abstract String getEspecie();

    public abstract void setEspecie(String especie);

    public abstract LocalDate getFechaDeNacimiento();

    public abstract void setFechaDeNacimiento(LocalDate fecha);

    public abstract Mamifero getPadre();

    public abstract void setPadre(Mamifero padre);

    public abstract Mamifero getMadre();

    public abstract void setMadre(Mamifero madre);

    public abstract Mamifero getAbueloMaterno();

    public abstract Mamifero getAbuelaMaterna();

    public abstract Mamifero getAbueloPaterno();

    public abstract Mamifero getAbuelaPaterna();

    public abstract boolean tieneComoAncestroA(Mamifero unMamifero);
}
