package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	Image fondo;
	Image Regalo;

	private Entorno entorno;
	private cuadricula2 cuadricula;
	Regalo[] regalos;
	private Planta plantasDisponibles;
	private Planta[] plantasTablero;          
	private Planta[] plantasArrastrando; 
	private int maxTablero = 20;           
	private int maxArrastrando = 5; 
	private double escala = 0.2;
	
	Juego()
	{
		
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.fondo = Herramientas.cargarImagen("fondo.png");
		this.cuadricula = new cuadricula2 (0, 0, entorno);
		this.regalos= new Regalo[5];
		double posY= 60;
		double posXinical =80;
		double separacion =80;
		double margensup= 160;
		
		plantasTablero = new Planta[maxTablero];
        plantasArrastrando = new Planta[maxArrastrando];
		Image planta = Herramientas.cargarImagen("planta.gif");
		plantasDisponibles = new Planta(100, 50, planta, escala);
		
		for (int i=0 ;i < regalos.length;i++) {
			double posX = posXinical +(i *separacion)+ margensup;
			this.regalos [i]= new Regalo (posX, posY);
		}
		
		
		this.entorno.iniciar();
	}

	
	 
	public void tick()
	{
		double mx = entorno.mouseX();
        double my = entorno.mouseY();
			
			entorno.dibujarImagen(fondo, 400, 300, 0);
			cuadricula.dibujar();
			for (Regalo r : regalos) {
				if (r != null) {
	                r.dibujar(entorno);
			}
				
			}
		plantasDisponibles.dibujar(entorno);
		 if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		 for (int i = 0; i < maxArrastrando; i++) {
			 if (plantasArrastrando[i] == null) {
		        plantasArrastrando[i] = new Planta(plantasDisponibles.getX(), plantasDisponibles.getY(),Herramientas.cargarImagen("planta.png"), escala);
		                        break; 
		     }
		                    
		        }
			 
		            } 
		     for  (int i = 0; i < maxArrastrando; i++) {
		       Planta p = plantasArrastrando[i];
		       if (p != null) {
		      if (!p.estaColocada() && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) {
		      p.dibujar(entorno);
		     }
	}}
		  if (entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
		   for (int i = 0; i < maxArrastrando; i++) {
		    Planta p = plantasArrastrando[i];
		    if (p != null && !p.estaColocada()&& p.getY() > 100) {
		   p.setColocada(true);
		                           
		  for (int j = 0; j < maxTablero; j++) {
			  if (plantasTablero[j] == null) {
                  plantasTablero[j] = p;
                  break;
              }
          }
          plantasArrastrando[i] = null;
      }
  }
                             
 for (int a= 0; a < maxTablero; a++) {
 Planta p = plantasTablero[a];
		                    if (p != null) {
		                        if (entorno.estaPresionada('w')) p.moverArriba();
		                        if (entorno.estaPresionada('s')) p.moverAbajo();
		                        if (entorno.estaPresionada('a')) p.moverIzquierda();
		                        if (entorno.estaPresionada('d')) p.moverDerecha();
		                    }
		                }
		              
		          
		                }
		            }
		        
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
