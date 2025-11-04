package juego;
import entorno.Entorno;

import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno; 
import entorno.InterfaceJuego;

public class Planta {
	private double X;
	private double Y;
    private Image imagen;	
private boolean colocada;

private int fila;
private int columna;
private double tamCelda;
private double escala;
private double margenSup;

private AtaquePlanta [] ataques;
private int tiempoDisparo;
private int intervaloDisparo;
public Planta(double x, double y, Image imagen, double escala,double tamcelda, double margenSup) {
    this.X = x;
    this.Y = y;
    this.imagen = imagen;
    this.colocada = false;
    
    this.escala= escala;
    this.tamCelda = tamcelda;
    this.fila = 0;
    this.columna = 0;
    this.margenSup = margenSup;
    
    this.ataques = new AtaquePlanta[10];
    this.tiempoDisparo = 0;
    this.intervaloDisparo = 60; 
}
public void dibujar (Entorno e) {
	 e.dibujarImagen(imagen, X,Y,0,escala);
     dibujarataques(e);
    
}
public void tick(Entorno e, double anchoPantalla) {
    tiempoDisparo++;
    if (tiempoDisparo >= intervaloDisparo && colocada) {
        disparar();
        tiempoDisparo = 0;
    }

    moverataques();
    eliminarataquesFuera(anchoPantalla);
    dibujar(e);
}
private void disparar() {
    Image fuego = Herramientas.cargarImagen("ezgif-882e311c995332.gif"); 
    for (int i = 0; i < ataques.length; i++) {
        if (ataques[i] == null) {
            ataques[i] = new AtaquePlanta(X + 20, Y, 4, fuego);
            break;
        }
    }
}
private void moverataques() {
    for (int i = 0; i < ataques.length; i++) {
        if (ataques[i] != null) {
            ataques[i].mover();
        }
    }
    }
private void dibujarataques(Entorno e) {
    for (AtaquePlanta p : ataques) {
        if (p != null) {
            p.dibujar(e);
        }
    }
}
private void eliminarataquesFuera(double anchoPantalla) {
    for (int i = 0; i < ataques.length; i++) {
        if (ataques[i] != null && ataques[i].estaFuera(anchoPantalla)) {
            ataques[i] = null;
        }
    }
}
public void moverA(double x, double y) {
    this.X = x;
    this.Y = y;
}


public void moverArriba() { 
	 if (colocada && fila > 0) {
         fila--;
         actualizarPosicion();
     }	 }
public void moverAbajo(int maxFilas) {
	if (colocada && fila < maxFilas - 1) { 
        fila++; 
        actualizarPosicion(); 
    }	 }
public void moverIzquierda() { 
	if (colocada && columna > 0) {
        columna--;
        actualizarPosicion();
    } }
public void moverDerecha(int maxCols) {
	 if (colocada && columna < maxCols - 1) { 
	        columna++; 
	        actualizarPosicion(); 
	    } 
	 }
private void actualizarPosicion() {
    X = columna * tamCelda + tamCelda/2;
    Y = margenSup + fila * tamCelda + tamCelda/2; 
}

public void setFila(int fila) {
	this.fila = fila; 
	 actualizarPosicion();
}
public void setColumna(int columna) {
	this.columna = columna; 
	actualizarPosicion();
}
public boolean isColocada() { return colocada; }
public void setColocada(boolean colocada) { this.colocada = colocada; }
public boolean estaColocada() { return colocada; }
public int getFila() { return fila; }
public int getColumna() { return columna; }

	
	
	 
	    public double getX() { return X; }
	    public double getY() { return Y; }
	} 
