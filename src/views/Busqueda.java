package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservaController reservaController; 
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(253, 62, 358, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		/*modelo.addRow(
                new Object[] {
                	"ID Reserva",
                	"Check-In",
                	"Check-Out",
                	"Valor",
                	"Forma de pago"
                });*/
		cargaReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		/*modeloH.addRow(
                new Object[] {
                	"Numero Huesped",
                	"Nombre",
                	"Apellido",
                	"Nacimiento",
                	"Nacionalidad",
                	"Telefono",
                	"ID Reserva"
                });*/
		cargaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = txtBuscar.getText();
				if(!input.equals("")) {
					limpiarTablas();
					busca(input);
				} else {
					limpiarTablas();
					cargaHuespedes();
					cargaReservas();
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					editarReserva();
					limpiarTablas();
					cargaHuespedes();
					cargaReservas();
				} else if (filaHuespedes >= 0) {
					editarHuesped();
					limpiarTablas();
					cargaHuespedes();
					cargaReservas();
				}
				 
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					eliminarReserva();
					limpiarTablas();
					cargaHuespedes();
					cargaReservas();
				} else if (filaHuespedes >= 0) {
					eliminarHuesped();
					limpiarTablas();
					cargaHuespedes();
					cargaReservas();
				}
				 
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
	    private void cargaReservas() {
	    	var reservas = this.reservaController.listar();
	    	
	    	reservas.forEach(reserva -> modelo.addRow(
	                new Object[] {
	                	reserva.getId(),
	                	reserva.getFechaE(),
	                	reserva.getFechaS(),
	                	reserva.getValor(),
	                	reserva.getFormaPago()
	                }));
	    }
	    
	    private void cargaHuespedes() {
	    	var huespedes = this.huespedController.listar();
	    	
	    	huespedes.forEach(huesped -> modeloH.addRow(
	                new Object[] {
	                	huesped.getId(),
	                	huesped.getNombre(),
	                	huesped.getApellido(),
	                	huesped.getFechaNac(),
	                	huesped.getNacionalidad(),
	                	huesped.getTelefono(),
	                	huesped.getIdReserva()
	                }));
	    }
	    
	    private void limpiarTablas() {
	    	((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	    	((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);		
	    }
	    
	   private boolean filaSeleccionada(JTable tabla) {
	    	return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	    }
	    
	    private void editarReserva() {
	    	if (filaSeleccionada(tbReservas)) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	    	}
	    	
	    	Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	    		.ifPresentOrElse(fila  ->{
	    			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	    			Date fechaE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
	    			Date fechaS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
	    			String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
	    			String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
	    			
	    			var filasModificadas = this.reservaController.editar(id, fechaE, fechaS, valor, formaPago);
	    			
	    			 JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	    		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }
	    
	    private void eliminarReserva() {
	        if (filaSeleccionada(tbReservas)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

	                    var filasModificadas = this.reservaController.eliminar(id);

	                    modelo.removeRow(tbReservas.getSelectedRow());

	                    JOptionPane.showMessageDialog(this,
	                            String.format("%d item eliminado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }
	    
	    private void editarHuesped() {
	    	if (filaSeleccionada(tbHuespedes)) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	    	}
	    	
	    	Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	    		.ifPresentOrElse(fila  ->{
	    			Integer id = Integer.valueOf(modelo.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
	    			String nombre = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 1);
	    			String apellido = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 2);
	    			Date fechaNac = Date.valueOf(modelo.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
	    			String nacionalidad = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 4);
	    			String telefono = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 5);
	    			Integer idReserva = Integer.valueOf(modelo.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
	    			
	    			var filasModificadas = this.huespedController.editar(id, nombre, apellido, fechaNac, nacionalidad, telefono, idReserva);
	    			
	    			 JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	    		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }
	    
	    private void eliminarHuesped() {
	        if (filaSeleccionada(tbHuespedes)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modelo.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modelo.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

	                    var filasModificadas = this.huespedController.eliminar(id);

	                    modelo.removeRow(tbHuespedes.getSelectedRow());

	                    JOptionPane.showMessageDialog(this,
	                            String.format("%d item eliminado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }
	    
	    private void busca(String input) {
	    	Integer id = Integer.valueOf(input);
	    	
	    	var huespedes = this.huespedController.listar();
	    	var reservas = this.reservaController.listar();
	    	
	    	List<Huesped> FHuespedes = new ArrayList<Huesped>(); 
	    	List<Reserva> FReservas = new ArrayList<Reserva>();
	    	
	    	FHuespedes = huespedes.stream().filter(huesped -> huesped.getIdReserva().equals(id)).collect(Collectors.toList());
	    	FReservas = reservas.stream().filter(reserva -> reserva.getId().equals(id)).collect(Collectors.toList());	    	
	    	
	    	FHuespedes.forEach(huesped -> modeloH.addRow(
	                new Object[] {
	                	huesped.getId(),
	                	huesped.getNombre(),
	                	huesped.getApellido(),
	                	huesped.getFechaNac(),
	                	huesped.getNacionalidad(),
	                	huesped.getTelefono(),
	                	huesped.getIdReserva()
	                }));
	    	
	    	FReservas.forEach(reserva -> modelo.addRow(
	                new Object[] {
	                	reserva.getId(),
	                	reserva.getFechaE(),
	                	reserva.getFechaS(),
	                	reserva.getValor(),
	                	reserva.getFormaPago()
	                }));
	    	
	    }
	    
}
