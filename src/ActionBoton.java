import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 *
 */
public class ActionBoton implements ActionListener{

	VentanaPrincipal ventana;
	int i;
	int j;

	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventana.getJuego().getTablero()[i][j]== -1) { //si hay una mina debajo del bot�n
			ventana.mostrarFinJuego(true);
		} else {										//si no hay mina debajo del bot�n
			ventana.mostrarNumMinasAlrededor(i, j);
		}
		if (ventana.getJuego().getPuntuacion() == 80) { //si ha descubierto todas las minas
			JOptionPane.showMessageDialog(ventana.ventana, "��Has descubierto todas las minas!!");
			ventana.bloquearBotones();
		}
	}

}
