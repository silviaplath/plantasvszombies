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
    private  double filas;
    private double columnas =5;
    private double tamceldas = 100;
    private double margensup = 100;
    private double[] coorX, coorY;
    Image pasto1, pasto2;
    Entorno e;
    private double escala = 0.5;
   
public cuadricula2 (double x , double y , Entorno e) {
	this.filas = Math.floor((e.alto() - margensup) / tamceldas);
	this.coorX=new double [(int)columnas];
	this.coorY= new double [(int)filas];
	
    this.e = e;

    this.pasto1 = Herramientas.cargarImagen("pasto1.jpeg");
    this.pasto2 = Herramientas.cargarImagen("pasto2.jpg");
    

   for (int i =0; i< columnas; i++) {
	   coorX[i]= i * tamceldas + tamceldas / 2;              
   }
   for (int j=0 ;j < filas; j++) {
	   coorY[j] = margensup + tamceldas / 2 + j * tamceldas; 
   }

    

    

   
}
public void dibujar () {
    for (int i = 0; i < coorY.length; i++) {
        for (int j = 0; j < coorX.length; j++) {
        	 Image img = ((i + j) % 2 == 0) ? pasto1 : pasto2;
            e.dibujarImagen(img, coorX[j], coorY[i], 0, escala);
        }
    }

}
}

