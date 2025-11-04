package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;
import java.util.random.*;
public class Tablero {
	 private double tiempo;
	 private int zombiesEliminados;
	 private int enemigosRestantes;
	 private double cuentaRegresiva;

public Tablero(int enemigosTotales) {	    
	this.tiempo = 0;
    this.zombiesEliminados = 0;
    this.enemigosRestantes = enemigosTotales;
}
public void sumarZombieEliminado () {
	zombiesEliminados++;
	enemigosRestantes--;
}
public void sumarTiempo(double segundos) {
    tiempo += segundos;
}
public void dibujar(Entorno e) {
    e.cambiarFont("araial",20,Color.BLACK);
    if (cuentaRegresiva > 0) {
        e.escribirTexto("Próxima planta en: " + (int) cuentaRegresiva + "s", 30, 30);
    } else {
        e.escribirTexto("¡Podés plantar!", 30, 30);
    }
    e.escribirTexto("Eliminados: " + zombiesEliminados, 250, 30);
    e.escribirTexto("Restantes: " + enemigosRestantes, 400, 30);
}


public double getTiempo() {
    return tiempo;
}
public void setCuentaRegresiva(double segundos) {
    this.cuentaRegresiva = segundos;
}


}
