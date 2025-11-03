package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;
import java.util.random.*;

public class Juego extends InterfaceJuego
{
	Image fondo;
	Image Regalo;

	private Entorno entorno;
	private cuadricula2 cuadricula;
	Regalo[] regalos;
	zombie[]zombies;
	int cantzombies;
	Random rnd;
	
	
	
	
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
		for (int i=0 ;i < regalos.length;i++) {
			double posX = posXinical +(i *separacion)+ margensup;
			this.regalos [i]= new Regalo (posX, posY);
		this.zombies=new zombie [20];	
		this.cantzombies=0;
		this.rnd=new Random();
		
		}
		
		
		this.entorno.iniciar();
	}
	
	public void generarzombies() {
		if (cantzombies<zombies.length) {
			int filaRandom=rnd.nextInt(cuadricula.getCantidadFilas());
			double y= cuadricula.getPosYFila(filaRandom);
			double x=entorno.ancho()+ 50;
			
			zombies[cantzombies]=new zombie(x,y);
			cantzombies++;
		}
	}
    private void eliminarZombieEn(int i) {
    	if (i<0 || i >= cantzombies) return;
    	zombies[i]=zombies[cantzombies-1];
    	zombies[cantzombies-1]=null;
    	cantzombies--;
    }
	
	 
	public void tick()
	{
	
			
			entorno.dibujarImagen(fondo, 400, 300, 0);
			cuadricula.dibujar();
			for (Regalo r : regalos) {
				if (r != null) {
	                r.dibujar(entorno);
			}
			if (rnd.nextInt(80)==0) {
				generarzombies();
			}
			for (int i=0;i<cantzombies;i++) {
				zombie z=zombies[i];
				if (z!= null) {
					z.mover();
					z.dibujar(entorno);
					if (z.getX()< - 50) {
						eliminarZombieEn(i);
						i--;
					}
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
