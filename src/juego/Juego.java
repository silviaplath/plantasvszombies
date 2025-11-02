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
	
	 private Planta plantaDisponible;
	 private Planta plantaSeleccionada;
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
		
		Image planta = Herramientas.cargarImagen("planta.gif");
        plantaDisponible = new Planta(100, 50, planta, 0.3);
    
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
			 plantaDisponible.dibujar(entorno);
			 if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		            if (plantaDisponible.contiene(mx, my)) {
		            	  plantaSeleccionada = new Planta(mx, my, Herramientas.cargarImagen("planta.gif"),0.3);
		            }
		        }
			 if (entorno.estaPresionado(entorno.BOTON_IZQUIERDO) && plantaSeleccionada != null && !plantaSeleccionada.estaColocada()) {
		            plantaSeleccionada.moverA(mx, my);
		        }
			 if (entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO) && plantaSeleccionada != null && !plantaSeleccionada.estaColocada()) {
		            plantaSeleccionada.setColocada(true);
		        }
			 if (plantaSeleccionada != null && plantaSeleccionada.estaColocada()) {
		            if (entorno.estaPresionada('w')) plantaSeleccionada.moverArriba();
		            if (entorno.estaPresionada('s')) plantaSeleccionada.moverAbajo();
		            if (entorno.estaPresionada('a')) plantaSeleccionada.moverIzquierda();
		            if (entorno.estaPresionada('d')) plantaSeleccionada.moverDerecha();
		        }
			 if (plantaSeleccionada != null) {
		            plantaSeleccionada.dibujar(entorno);
		        }
		            }
		

	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
