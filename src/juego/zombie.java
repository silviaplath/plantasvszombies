package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import javax.swing.ImageIcon;


public class zombie {
	double x, y;
	double velocidad=0.3;
	Image img;
	public zombie(double xInicial, double yInicial) {
		this.x=xInicial;
		this.y=yInicial;
		this.img=Herramientas.cargarImagen("zombie1.png");
		
	}
	public void mover() {
		this.x-=velocidad;
		
	}
	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.12);
		
	}
	public double getX() {return x;}
	public double getY() {return y ;}
	

	}


