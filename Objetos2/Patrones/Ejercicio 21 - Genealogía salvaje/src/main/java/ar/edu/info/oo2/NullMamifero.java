/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.info.oo2;

import java.time.LocalDate;

/**
 *
 * @author frank
 */
public class NullMamifero implements MamiferoInterfaz {

    @Override
    public String getIdentificador() {
        return null;
    }

    @Override
    public void setIdentificador(String id) {

    }

    @Override
    public String getEspecie() {
        return null;
    }

    @Override
    public void setEspecie(String especie) {

    }

    @Override
    public LocalDate getFechaDeNacimiento() {
        return null;
    }

    @Override
    public void setFechaDeNacimiento(LocalDate fecha) {
    }

    @Override
    public Mamifero getPadre() {
        return null;
    }

    @Override
    public void setPadre(Mamifero padre) {

    }

    @Override
    public Mamifero getMadre() {
        return null;
    }

    @Override
    public void setMadre(Mamifero madre) {
    }

    @Override
    public Mamifero getAbueloMaterno() {
        return null;
    }

    @Override
    public Mamifero getAbuelaMaterna() {
        return null;
    }

    @Override
    public Mamifero getAbueloPaterno() {
        return null;
    }

    @Override
    public Mamifero getAbuelaPaterna() {
        return null;
    }

    @Override
    public boolean tieneComoAncestroA(Mamifero unMamifero) {
        return false;
    }

}
