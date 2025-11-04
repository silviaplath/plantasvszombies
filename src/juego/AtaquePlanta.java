package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
public class AtaquePlanta {
	 private double x;
	 private double y;
	 private double velocidad;
	 private Image imagen;

public AtaquePlanta(double x, double y, double velocidad, Image imagen) {
    this.x = x;
    this.y = y;
    this.velocidad = velocidad;
    this.imagen = imagen;
}
public void mover() {
    x += velocidad;
}
public void dibujar(Entorno e) {
    e.dibujarImagen(imagen, x, y, 0, 0.5);
}
public boolean estaFuera(double anchoPantalla) {
    return x > anchoPantalla;
}
public double getX() { return x; }
public double getY() { return y; }
}
