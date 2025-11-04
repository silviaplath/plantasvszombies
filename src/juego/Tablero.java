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
    e.escribirTexto("Tiempo: " + (int) tiempo + "s", 30, 30);
    e.escribirTexto("Eliminados: " + zombiesEliminados, 200, 30);
    e.escribirTexto("Restantes: " + enemigosRestantes, 400, 30);
}
public boolean juegoGanado() {
    return enemigosRestantes <= 0;
}

}
