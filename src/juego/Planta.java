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
private double velocidad;
private double escala;
public Planta(double x, double y, Image imagen, double escala) {
    this.X = x;
    this.Y = y;
    this.imagen = imagen;
    this.colocada = false;
    this.velocidad=3;
    this.escala= escala;
}
public void dibujar (Entorno e) {
	 e.dibujarImagen(imagen, X,Y,0,escala);
     
    
}
public boolean contiene(double mx, double my) {
    double ancho=imagen.getWidth(null)*escala;
    double alto= imagen.getHeight(null)*escala;
    return mx >= X - ancho / 2 && mx <= X + ancho / 2 &&
            my >= Y - alto / 2 && my <= Y + alto / 2;

}
public void moverA(double x, double y) {this.X = x; this.Y = y;}
public void moverArriba() { if (colocada) Y -= velocidad; }
public void moverAbajo() { if (colocada) Y += velocidad; }
public void moverIzquierda() { if (colocada) X -= velocidad; }
public void moverDerecha() { if (colocada) X += velocidad; }

public void setColocada(boolean colocada) { this.colocada = colocada; }
public boolean estaColocada() { return colocada; }

public double getX() { return X; }
public double getY() { return Y; }
}