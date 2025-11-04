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
	
	
	
	private Planta plantasDisponibles;
	private Planta[] plantasTablero;          
	private int maxTablero = 50;            
	private double escala = 0.1;
	Planta plantaArrastrando = null;
	private Planta plantaSeleccionada = null;
	private boolean wPresionada = false;
	private boolean sPresionada = false;
	private boolean aPresionada = false;
	private boolean dPresionada = false;

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
		Image planta = Herramientas.cargarImagen("planta.png");
		plantasDisponibles = new Planta(100, 50, planta, escala,cuadricula.getTamCelda(), cuadricula.getMargenSup());
		
		
		for (int i=0 ;i < regalos.length;i++) {
			double posX = posXinical +(i *separacion)+ margensup;
			this.regalos [i]= new Regalo (posX, posY);
<<<<<<< HEAD
		this.zombies=new zombie [10];	
		this.cantzombies=0;
		this.rnd=new Random();
=======
>>>>>>> 02ec85035bbc70a6b772b55559d42dcefdb43e1f
		
		}
		this.zombies=new zombie [15];	
		this.cantzombies=0;
		this.rnd=new Random();
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
			if (rnd.nextInt(80)==0) {
				generarzombies();
			}
			for (int i = 0; i < zombies.length; i++) {
			    zombie z = zombies[i];
			    if (z != null) {
			        z.mover();
			        z.dibujar(entorno);

			        if (z.debeEliminarse()) {
			        	zombies[i] = null;
			        }
				}
			}
				
			}
			for (int i = 0; i < maxTablero; i++) {
			    if (plantasTablero[i] != null) {
			        plantasTablero[i].tick(entorno, entorno.ancho());
			    }
			}

			        if (plantaArrastrando == null) {    
		plantasDisponibles.dibujar(entorno);
			        }
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && plantaArrastrando == null) {
		    plantaArrastrando = new Planta(100 , 50 , Herramientas.cargarImagen("planta.png"), escala,cuadricula.getTamCelda(), cuadricula.getMargenSup());
		     };
	
		 if (plantaArrastrando != null && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) {
			    plantaArrastrando.moverA(mx, my);
			    plantaArrastrando.dibujar(entorno);                 
		       
		     }
		 if (plantaArrastrando != null && entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
<<<<<<< HEAD
			 
			    plantaArrastrando.setColocada(true);
		 for (int i = 0; i < maxTablero; i++) {
		 if (plantasTablero[i] == null) {
		 plantasTablero[i] = plantaArrastrando;
		 break;
=======
			    if (cuadricula.estaDentro(entorno.mouseX(), entorno.mouseY())) {
			        int fila = cuadricula.getFilaDesdeY(entorno.mouseY());
			        int col  = cuadricula.getColumnaDesdeX(entorno.mouseX());
			        if (!estaCeldaOcupada(fila, col)) {
			        plantaArrastrando.setFila(fila);
			        plantaArrastrando.setColumna(col);
			        plantaArrastrando.moverA(
			            cuadricula.getPosXColumna(col),
			            cuadricula.getPosYFila(fila)
			        );
			        plantaArrastrando.setColocada(true);	
			        for (int x = 0; x < maxTablero; x++) {
		                if (plantasTablero[x] == null) {
		                    plantasTablero[x] = plantaArrastrando;
		                    break;
		                }
		            } 
			        plantaSeleccionada = plantaArrastrando; 
			    }
			    }
			    plantaArrastrando = null;
		 }
	
		 if (plantaSeleccionada != null) {

			    // --- W ---
			    if (entorno.estaPresionada('w')) {
			        if (!wPresionada && plantaSeleccionada.getFila() > 0
			                && !estaCeldaOcupada(plantaSeleccionada.getFila() - 1, plantaSeleccionada.getColumna())) {
			            plantaSeleccionada.moverArriba();
			            wPresionada = true;
			        }
			    } else {
			        wPresionada = false;
			    }

			    // --- S ---
			    if (entorno.estaPresionada('s')) {
			        if (!sPresionada && plantaSeleccionada.getFila() < cuadricula.getCantidadFilas() - 1
			                && !estaCeldaOcupada(plantaSeleccionada.getFila() + 1, plantaSeleccionada.getColumna())) {
			            plantaSeleccionada.moverAbajo(cuadricula.getCantidadFilas());
			            sPresionada = true;
			        }
			    } else {
			        sPresionada = false;
			    }

			    // --- A ---
			    if (entorno.estaPresionada('a')) {
			        if (!aPresionada && plantaSeleccionada.getColumna() > 0
			                && !estaCeldaOcupada(plantaSeleccionada.getFila(), plantaSeleccionada.getColumna() - 1)) {
			            plantaSeleccionada.moverIzquierda();
			            aPresionada = true;
			        }
			    } else {
			        aPresionada = false;
			    }

			    // --- D ---
			    if (entorno.estaPresionada('d')) {
			        if (!dPresionada && plantaSeleccionada.getColumna() < cuadricula.getCantidadColumnas() - 1
			                && !estaCeldaOcupada(plantaSeleccionada.getFila(), plantaSeleccionada.getColumna() + 1)) {
			            plantaSeleccionada.moverDerecha(cuadricula.getCantidadColumnas());
			            dPresionada = true;
			        }
			    } else {
			        dPresionada = false;
			    }
		 }
		 
		 for (int i = 0; i < plantasTablero.length; i++) {
			    Planta p = plantasTablero[i];
			    if (p != null && p.isColocada()) {
			        AtaquePlanta[] ataques = p.getAtaques();

			        for (int j = 0; j < ataques.length; j++) {
			            AtaquePlanta a = ataques[j];
			            if (a != null) {
			                for (int k = 0; k < zombies.length; k++) {
			                    zombie z = zombies[k];
			                    if (z != null && !z.estaMuerto()) {
			                        if (colision(a, z)) {
			                            z.restarVida(1);
			                            ataques[j] = null; 
			                        }
			                    }
			                }
			            }
>>>>>>> 02ec85035bbc70a6b772b55559d42dcefdb43e1f
			        }
			    }
		 }
			}

	private boolean estaCeldaOcupada(int fila, int columna) {
	    for (int i = 0; i < maxTablero; i++) {
	        Planta p = plantasTablero[i];
	        if (p != null && p.getFila() == fila && p.getColumna() == columna) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean colision(AtaquePlanta a, zombie z) {
	    return Math.abs(a.getX() - z.getX()) < 20 && Math.abs(a.getY() - z.getY()) < 20;
	}


	    
	 
	
		        
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
