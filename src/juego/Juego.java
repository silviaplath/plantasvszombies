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

	private Tablero tablero;

	int totalEliminados=0;
	int objetivoEliminacion=50;
	boolean juegoTerminado=false;
	int totalGenerados=0;
	int maxZombiesGenerar=50;
	String mensajeFinal = " " ;
	Color colorMensaje = Color.white;
	
	


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
	private double tiempoUltimoZombie = 0;
	private double tiempoUltimaPlanta = 0;

	Juego()
	{
		
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.fondo = Herramientas.cargarImagen("fondo.png");
		this.cuadricula = new cuadricula2 (0, 0, entorno);
		this.regalos= new Regalo[5];
		this.tablero = new Tablero(50);
		double posY= 60;
		double posXinical =80;
		double separacion =80;
		double margensup= 160;
		plantasTablero = new Planta[maxTablero];
		plantasTablero = new Planta[maxTablero];
		Image planta = Herramientas.cargarImagen("planta.png");
		plantasDisponibles = new Planta(100, 80, planta, escala,cuadricula.getTamCelda(), cuadricula.getMargenSup());
		
		
		for (int i=0 ;i < regalos.length;i++) {
			double posX = posXinical +(i *separacion)+ margensup;
			this.regalos [i]= new Regalo (posX, posY);


		
		}
		this.zombies=new zombie [1];	
		this.cantzombies=0;
		this.rnd=new Random();
		this.entorno.iniciar();
	}
	
	public void generarzombies() {
		if (totalGenerados >= maxZombiesGenerar)
			return;
		for (int i = 0; i < zombies.length; i++) {
			if (zombies [i] == null) {
				
				int filaRandom=rnd.nextInt(cuadricula.getCantidadFilas());
				double y= cuadricula.getPosYFila(filaRandom);
				double x=entorno.ancho()+ 50;
				
				zombies[i]=new zombie(x,y);
				totalGenerados++;
				break;
				
				
			}
		}
		
		
	}
    
	
	 
	public void tick()
	{
		if (juegoTerminado) {
			entorno.cambiarFont("Arial", 40, colorMensaje);
			entorno.escribirTexto(mensajeFinal, 200, 300);
		
			
			return;
			
		}
		
		double mx = entorno.mouseX();
        double my = entorno.mouseY();
        
        if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
        	boolean clicEnPlanta = false;
        	
        	for (int i = 0; i < plantasTablero.length; i++) {
        		Planta p= plantasTablero[i];
        		if (p != null && p.estaColocada()) {
        			double distancia = Math.hypot(mx - p.getX(), my - p.getY());
        			if (distancia < 30) {
        				plantaSeleccionada=p;
        				p.setSeleccionada(true);
        				clicEnPlanta = true;
        			}else {
        				p.setSeleccionada(false);
        			}
        		}
        	}
        	if (!clicEnPlanta) {
        		plantaSeleccionada = null;
        		for (int i = 0; i < plantasTablero.length;i++) {
        			if (plantasTablero[i] != null) {
        				plantasTablero[i].setSeleccionada(false);
        			}
        		}
        	}
        }
			
			entorno.dibujarImagen(fondo, 400, 300, 0);
			cuadricula.dibujar();
			tablero.sumarTiempo(1.0 / 60.0);
		    tablero.dibujar(entorno);
		    double tiempoActual = tablero.getTiempo();
		    double tiempoFaltante = 5 - (tiempoActual - tiempoUltimaPlanta);
		    if (tiempoFaltante < 0) tiempoFaltante = 0;
		    tablero.setCuentaRegresiva(tiempoFaltante);
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
			        
			        for (int p = 0; p < plantasTablero.length; p++) {
			        	Planta planta= plantasTablero[p];
			        	if(planta!= null && planta.estaColocada() ){
			        		
			        		if (Math.abs(z.getX()-planta.getX()) < 25 && Math.abs(z.getY()-planta.getY()) < 25) {
			        			plantasTablero[p]= null;
			        		}
			        		
			        	}
			        }
			        if (z.getX()<120) {
			        	mensajeFinal= "PERDISTE";
			        	colorMensaje= Color.red;
			        	juegoTerminado=true;
			        }

			        if (z.debeEliminarse()) {

			        zombies[i] = null;
			        tablero.sumarZombieEliminado();	

			        	totalEliminados++;
			        	zombies[i] = null;

			        }
				}
			}
			if (totalEliminados >= objetivoEliminacion) {
				mensajeFinal= "GANASTE";
				colorMensaje= Color.green;
			    juegoTerminado=true;
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
			           
			            if (tiempoActual - tiempoUltimaPlanta >= 3) {
			                plantaArrastrando = new Planta(100 , 50 , Herramientas.cargarImagen("planta.png"), escala,cuadricula.getTamCelda(), cuadricula.getMargenSup());
			                tiempoUltimaPlanta = tiempoActual;
			            }
			       
			        }
	
		 if (plantaArrastrando != null && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) {
			    plantaArrastrando.moverA(mx, my);
			    plantaArrastrando.dibujar(entorno);                 
		       
		     }
		 if (plantaArrastrando != null && entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {


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
	
		 if (plantaSeleccionada != null && plantaSeleccionada.isSeleccionada()) {

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

			        }
			    }
		}
		 }
		 

	private void escribirTexto(String mensajeFinal2, int i, int j, int k) {
		// TODO Auto-generated method stub
		
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
