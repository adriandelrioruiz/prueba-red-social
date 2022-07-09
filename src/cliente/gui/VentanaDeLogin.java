package cliente.gui;

import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.UIManager;

public class VentanaDeLogin extends JFrame {

	private static final String campoContraseña = "contraseña";
	private static final String campoCorreo = "usuario o correo electrónico" ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Panel
	private JPanel contentPane ;
	// TextField para introducir el usuario o el correo electrónico
	private JTextField userField;
	// TextField para introducir la contraseña
	private JPasswordField passwordField;
	// Label que al clicar te lleva al cambio de contraseña
	private JLabel reestablecerContraseña;
	// TextField que funciona como botón de login
	private JTextField botonLogin;
	
	
	// Estos atributos son para que funcionen correctamente los TextField de usuario y contraseña
	private boolean usuarioVacio = true;
	private boolean contraseñaVacio = true;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDeLogin frame = new VentanaDeLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaDeLogin() {
		
		// Modificamos el JFrame
		setBounds(100, 100, 450, 500);
		// Centramos el panel
		setLocationRelativeTo(null);
		// Quitamos los botones de minimización, maximización y cierre
		setUndecorated(true);
		
		// Modificamos el JPanel
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Campo usuario/correo electrónico
		userField = new JTextField();
		userField.setFont(new Font("Arial", Font.PLAIN, 14));
		userField.setBorder(UIManager.getBorder("PasswordField.border"));
		userField.setBackground(new Color(255,255,255));
		userField.setBounds(79, 168, 267, 37);
		userField.setText(campoCorreo);
		contentPane.add(userField);
		userField.setColumns(10);
		
		// Campo contraseña
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setBackground(new Color(255,255,255));
		passwordField.setText(campoContraseña);
		passwordField.setBounds(79, 225, 267, 37);
		contentPane.add(passwordField);
		
		// Etiqueta reestablecer contraseña
		reestablecerContraseña = new JLabel("Reestablecer contraseña");
		reestablecerContraseña.setCursor(new Cursor(HAND_CURSOR));
		Font labelFont = new Font("Arial", Font.PLAIN, 13);
		Map attributes = labelFont.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		reestablecerContraseña.setFont(labelFont.deriveFont(attributes));
		reestablecerContraseña.setBounds(79, 273, 153, 21);
		contentPane.add(reestablecerContraseña);
		
		textField = new JTextField();
		textField.setBounds(79, 320, 267, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(65, 59, 319, 345);
		contentPane.add(panel);
				
		iniciarListeners();

	}
	
	private void iniciarListeners() {
		
		userField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				if (usuarioVacio)
					userField.setText(null);
			}
		});
		
		userField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if (userField.getText().isEmpty()) {
					userField.setText(campoCorreo);
					usuarioVacio = true;
				}
				else
					usuarioVacio = false;
			}
		});
		
		passwordField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				if (contraseñaVacio)
					passwordField.setText(null);
			}
		});
		
		passwordField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if (passwordField.getText().isEmpty()) {
					passwordField.setText(campoContraseña);
					contraseñaVacio = true;
				}
				else
					contraseñaVacio = false;
					
			}

		});
		
		// Cuando estemos en cualquiera de los dos campos de texto, se debe poder cerrar la app con Ctrl+W
		userField.addKeyListener(new CerrarCtrlW());
		passwordField.addKeyListener(new CerrarCtrlW());
		
		reestablecerContraseña.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
			}
			
			public void mouseEntered(MouseEvent evt) {
				reestablecerContraseña.setForeground(Color.white);
			}
			
			public void mouseExited(MouseEvent evt) {
				reestablecerContraseña.setForeground(Color.black);
			}
		});
		
		
	}
	
	class CerrarCtrlW extends KeyAdapter {
		public void keyPressed(KeyEvent evt) {
			if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_W)
				System.exit(0);
		}
	}
}
