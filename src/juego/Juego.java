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

	private Entorno entorno;
	private cuadricula2 cuadricula;
	
	
	Juego()
	{
		
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.fondo = Herramientas.cargarImagen("fondo.jpg");
		this.cuadricula = new cuadricula2 (0, 0, entorno);
		
		this.entorno.iniciar();
	}

	
	 
	public void tick()
	{
	
			
			entorno.dibujarImagen(fondo, 400, 300, 0);
			cuadricula.dibujar();
		}

	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
