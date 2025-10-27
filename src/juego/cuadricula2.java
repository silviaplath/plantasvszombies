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
    private double x;
    private double y;
    private double escala;
    private double ancho;
    private double alto;
    Image pasto1, pasto2;
    Entorno e;
    double[] coorX;
    double[] coorY;
public cuadricula2 (double x , double y , Entorno e) {
	
	
	this.x = x;
    this.y = y;
    this.e = e;

    this.pasto1 = Herramientas.cargarImagen("pasto1.jpeg");
    this.pasto2 = Herramientas.cargarImagen("pasto2.jpg");
    this.escala = 0.1;

    this.ancho = this.pasto1.getWidth(null) * this.escala;
    this.alto = this.pasto1.getHeight(null) * this.escala;

    this.coorX = new double [] {50,150,250,350,450,550,650,750};
    this.coorY = new double []{130,230,330,430,530};

    

}
public void dibujar () {
    for (int i = 0; i < coorY.length; i++) {
        for (int j = 0; j < coorX.length; j++) {
            if ( (i + j) % 2 == 0)
            	img = pasto1;
            else
                img = pasto2;
            e.dibujarImagen(img, coorX[j], coorY[i], 0, escala);
        }
    }

}
}

