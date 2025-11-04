package juego;
import entorno.Entorno;

import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno; 
import entorno.InterfaceJuego;
public class cuadricula2 {

	Image img;
    private  int filas=5;
    private int columnas =10;
    private double tamceldas;
    private double margensup = 200;
    private double[] coorX, coorY;
    private boolean[][] ocupado;
    Image pasto1, pasto2;
    Entorno e;
   
public  cuadricula2 (double x , double y , Entorno e) {
	
	 this.e = e;
	
	tamceldas = Math.min(e.ancho() /(double) columnas, (e.alto()- margensup) / (double) filas);
	this.coorX=new double [(int)columnas];
	this.coorY= new double [(int)filas];
	this.ocupado = new boolean[filas][columnas];
   for (int i =0; i< columnas; i++) {
	   coorX[i]= i * tamceldas + tamceldas / 2;              
   }
   for (int j=0 ;j < filas; j++) {
	   coorY[j] = margensup + tamceldas / 2 + j * tamceldas; 
   }

   this.pasto1 = Herramientas.cargarImagen("pasto1.jpeg");
   this.pasto2 = Herramientas.cargarImagen("pasto2.jpg");
}
   public double getPosXColumna(int col) {
	   return this.coorX[col];  
   }
   public double getPosYFila(int fila) {
	   return this.coorY[fila];
   }
   public int getCantidadFilas() {
	   return this.filas;
   }
   public int getCantidadColumnas() {
	    return columnas;
	}
   public double getTamCelda() {
	    return tamceldas;
	}
   public double getMargenSup() {
	    return margensup;
	}

public void dibujar () {
    for (int i = 0; i < coorY.length; i++) {
        for (int j = 0; j < coorX.length; j++) {
        	 Image img = ((i + j) % 2 == 0) ? pasto1 : pasto2;
        	 
        	 double escalaX = tamceldas / img.getWidth(null);
             double escalaY = tamceldas / img.getHeight(null);
             double escalaFinal = Math.max(escalaX, escalaY);
            e.dibujarImagen(img, coorX[j], coorY[i], 0, escalaFinal);
            e.dibujarImagen(img, coorX[j], coorY[i], 0, escalaFinal);
        }
    }

}
<<<<<<< HEAD
public boolean estaOcupado(int fila, int col) {
    return ocupado[fila][col];
}

public void ocupar(int fila, int col) {
    ocupado[fila][col] = true;
}
public int getColumnaDesdeX(double x) {
    return (int)(x / tamceldas);
}

public int getFilaDesdeY(double y) {
    return (int)((y - margensup) / tamceldas);
=======
public int getColumnaDesdeX(double x) {
    int col = (int) (x / tamceldas);
    if (col < 0 || col >= columnas) return -1;
    return col;
}
public int getFilaDesdeY(double y) {
    int fila = (int) ((y - margensup) / tamceldas);
    if (fila < 0 || fila >= filas) return -1;
    return fila;
}
public boolean estaDentro(double x, double y) {
    return x >= 0 && x < columnas * tamceldas &&
           y >= margensup && y < margensup + filas * tamceldas;
>>>>>>> 02ec85035bbc70a6b772b55559d42dcefdb43e1f
}
}

