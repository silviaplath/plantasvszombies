package juego;
import entorno.Entorno;

import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno; 
import entorno.InterfaceJuego;
public class Regalo {
private double X;
private double Y;
private Image img;
private double escala=0.08;
	public Regalo(Entorno e) {
		this.X =X;
		this.Y =Y;
		
		
		this.img=Herramientas.cargarImagen("png-clipart-gift-purple-drawing-purple-cartoon-holiday-gift-miscellaneous-cartoon-character (1).png");

	}
public Regalo(double X, double Y) {
	this.X = X;
    this.Y = Y;
    this.img = Herramientas.cargarImagen("png-clipart-gift-purple-drawing-purple-cartoon-holiday-gift-miscellaneous-cartoon-character (1).png");
}// TODO Auto-generated constructor stub
	
public void dibujar (Entorno e) {
	 e.dibujarImagen(this.img,this.Y, this.X,0, this.escala  );
}
}