package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


public class zombie {
	double x, y;
	double velocidad=0.2;
	Image img;
	private int vida;
    private boolean muerto;
    private Image tumba;
    private int tiempoTumba = 180;
	public zombie(double xInicial, double yInicial) {
		this.x=xInicial;
		this.y=yInicial;
		this.img=Herramientas.cargarImagen("zombie1.png");
		this.vida=3;
		this.muerto=false;
		this.tumba= Herramientas.cargarImagen("WhatsApp Image 2025-11-03 at 21.35.28.png");
	}
	public void mover() {
		if (!muerto) {
		this.x-=velocidad;
		 } else {
	        tiempoTumba--;
	}
}
	public void dibujar(Entorno e) {
		if (muerto) {
            e.dibujarImagen(tumba, x, y, 0, 0.07);
        } else
		e.dibujarImagen(img, x, y, 0, 0.12);
	}
		public void restarVida(int cantidad) {
	        if (!muerto) {
	            vida -= cantidad;
	            if (vida <= 0) {
	                morir();
	            }
	        }
	}
		private void morir() {
	        muerto = true;
	        velocidad = 0; 
	        y += 10;
	    }	
		public boolean estaMuerto() {
	        return muerto;
	    }
		public boolean debeEliminarse() {
		    return muerto && tiempoTumba <= 0;
		}
	public double getX() {return x;}
	public double getY() {return y ;}
	 public int getVida() { return vida; }
	}

	


