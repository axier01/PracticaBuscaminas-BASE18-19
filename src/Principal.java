import java.awt.EventQueue;

/**
 * Clase principal
 * @author axiergonzalezdiaz
 *
 */
public class Principal {

	/**
	 * Metodo main
	 * @param args : Cadenas de parámetros del main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
