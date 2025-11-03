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
double x,y , escala , ancho,alto ;
Image imagen , imagenseleccionada;
Entorno e;
boolean seleccionado;
public Planta (double x,double y, Entorno e) {
	this.x =x;
	this.y=y;
	this.e=e;
	this.escala=0.35;
	this.imagen=Herramientas.cargarImagen("planta.gif");
	this.imagenseleccionada= Herramientas.cargarImagen("planta.gif");
	this.seleccionado=false;
	
	
}
public void dibujar () {
	if (seleccionado) {
		e.dibujarImagen(imagenseleccionada, x, y, 0, escala);
	}
}
public double distancia (double x1, double y1, double x2, double y2) {
	return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)+(y1-y2));
}
public boolean encima (double xM , double yM ) {
	return distancia (xM,yM,this.x, this.y)<20;
	
}
public void arrastrar (double xM, double yM) {
	this.x= xM;
	this.y=yM;
}
}
