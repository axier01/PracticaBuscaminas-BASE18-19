import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPrincipal {

/**
 * 
 * {@link #inicializar()}
 * {@code}
 * @author Axier Gonzalez
 * @version 1.1
 * @since 1.1
 * @see ControlJuego
 * 
 * * */
	
	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar despues los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;
	int Tablero[][];
	int contador = 0;

	// Constructor, marca el tama�o y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
		Tablero = juego.getTablero();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

	}

	public void inicializarBotones() {
		Tablero = juego.getTablero();
		for (int i = 0; i < 10; i++) {
			int contadori = i;
			for (int j = 0; j < 10; j++) {
				int contadorj = j;
				botonesJuego[i][j].addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
					if(e.isMetaDown()) {
						if(botonesJuego[contadori][contadorj].getText().equalsIgnoreCase("-")) {
							botonesJuego[contadori][contadorj].setText("<|");
							botonesJuego[contadori][contadorj].setForeground(Color.red);
						}else {
							botonesJuego[contadori][contadorj].setText("-");
							botonesJuego[contadori][contadorj].setForeground(Color.black);
						}
					}
					}
				});
				botonesJuego[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						panelesJuego[contadori][contadorj].remove(botonesJuego[contadori][contadorj]);
						JLabel texto = new JLabel(Integer.toString(Tablero[contadori][contadorj]));
						switch (texto.getText()) {
						case "0": {
							texto.setForeground(correspondenciaColores[0]);
							contador++;
							pantallaPuntuacion.setText(Integer.toString(contador));
						}
							break;
						case "-1": {
							texto.setForeground(correspondenciaColores[1]);
							
							mostrarFinJuego(true);
						}
							break;
						case "1": {
							texto.setForeground(correspondenciaColores[2]);
							contador++;
							pantallaPuntuacion.setText(Integer.toString(contador));
						}
							break;
						case "2": {
							texto.setForeground(correspondenciaColores[3]);
							contador++;
							pantallaPuntuacion.setText(Integer.toString(contador));
						}
							break;
						case "3": {
							texto.setForeground(correspondenciaColores[4]);
							contador++;
							pantallaPuntuacion.setText(Integer.toString(contador));
						}
							break;
						default:
							contador++;
							texto.setForeground(correspondenciaColores[4]);
						}
						texto.setHorizontalAlignment(JLabel.CENTER);
						panelesJuego[contadori][contadorj].add(texto);
						if (Integer.parseInt(pantallaPuntuacion.getText()) == 80) {
							mostrarFinJuego(false);
						}
						refrescarPantalla();
					}
				});
			}
		}
	}

	/**
	 * Metodo que inicializa todos los listeners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {
		inicializarBotones();
		botonEmpezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelJuego.removeAll();
				inicializarBotones();
				juego.inicializarPartida();
				for (int i = 0; i < panelesJuego.length; i++) {
					for (int j = 0; j < panelesJuego[i].length; j++) {
						panelesJuego[i][j] = new JPanel();
						panelesJuego[i][j].setLayout(new GridLayout(1, 1));
						panelJuego.add(panelesJuego[i][j]);
					}
				}
				
				// Botones
				
				for (int i = 0; i < botonesJuego.length; i++) {
					for (int j = 0; j < botonesJuego[i].length; j++) {
						botonesJuego[i][j] = new JButton("-");
						panelesJuego[i][j].add(botonesJuego[i][j]);
					}
				}
				pantallaPuntuacion.setText("0");
				contador = 0;	
				inicializarBotones();
				refrescarPantalla();				
			}
		});
	}

	/**
	 * Pinta en la pantalla el numero de minas que hay alrededor de la celda Saca el
	 * boton que haya en la celda determinada y a�ade un JLabel centrado y no
	 * editable con el numero de minas alrededor. Se pinta el color del texto segun
	 * la siguiente correspondecia (consultar la variable correspondeciaColor): - 0
	 * : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 ó más : rojo
	 * 
	 * @param i:
	 *            posicion vertical de la celda.
	 * @param j:
	 *            posicion horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		// TODO
	}

	/**
	 * Muestra una ventana que indica el fin del juego
	 * 
	 * @param porExplosion
	 *            : Un booleano que indica si es final del juego porque ha explotado
	 *            una mina (true) o bien porque hemos desactivado todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		if (porExplosion == true) {
			JOptionPane.showMessageDialog(ventana, "Has perdido", "Fin de juego", JOptionPane.INFORMATION_MESSAGE);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					botonesJuego[i][j].setEnabled(false);
				}
			}

		} else {
			JOptionPane.showMessageDialog(ventana, "Has ganado", "Fin de juego", JOptionPane.INFORMATION_MESSAGE);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					botonesJuego[i][j].setEnabled(false);
				}
			}
		}
	}

	/**
	 * Metodo que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		// TODO
	}

	/**
	 * Metodo para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Metodo que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Metodo para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

}
