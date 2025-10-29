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
    private int columnas =9;
    private double tamceldas;
    private double margensup = 160;
    private double[] coorX, coorY;
    Image pasto1, pasto2;
    Entorno e;
   
public cuadricula2 (double x , double y , Entorno e) {
	
	 this.e = e;
	
	tamceldas = Math.min(e.ancho() /(double) columnas, (e.alto()- margensup) / (double) filas);
	this.coorX=new double [(int)columnas];
	this.coorY= new double [(int)filas];
	
   for (int i =0; i< columnas; i++) {
	   coorX[i]= i * tamceldas + tamceldas / 2;              
   }
   for (int j=0 ;j < filas; j++) {
	   coorY[j] = margensup + tamceldas / 2 + j * tamceldas; 
   }

   this.pasto1 = Herramientas.cargarImagen("pasto1.jpeg");
   this.pasto2 = Herramientas.cargarImagen("pasto2.jpg");

    

   
}
public void dibujar () {
    for (int i = 0; i < coorY.length; i++) {
        for (int j = 0; j < coorX.length; j++) {
        	 Image img = ((i + j) % 2 == 0) ? pasto1 : pasto2;
        	 
        	 double escalaX = tamceldas / img.getWidth(null);
             double escalaY = tamceldas / img.getHeight(null);
             double escalaFinal = Math.min(escalaX, escalaY);
            e.dibujarImagen(img, coorX[j], coorY[i], 0, escalaFinal);
        }
    }

}
}

