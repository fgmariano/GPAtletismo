package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import persistence.AtletismoDAO;

public class ConsultaResultado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<String> listaBox = new ArrayList<String>();
	private JComboBox<String> comboBox = new JComboBox<String>();
	private AtletismoDAO dao = new AtletismoDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaResultado frame = new ConsultaResultado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConsultaResultado() {
		setTitle("Consultar Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProvas = new JLabel("Provas");
		lblProvas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProvas.setBounds(97, 11, 56, 22);
		contentPane.add(lblProvas);
		
		comboBox.setBounds(152, 13, 214, 20);
		contentPane.add(comboBox);
		carregarCombobox();
		
		JRadioButton rdbtnFaseInicial = new JRadioButton("Fase Inicial");
		buttonGroup.add(rdbtnFaseInicial);
		rdbtnFaseInicial.setBounds(159, 46, 96, 23);
		contentPane.add(rdbtnFaseInicial);
		
		JRadioButton rdbtnFaseFinal = new JRadioButton("Fase final");
		buttonGroup.add(rdbtnFaseFinal);
		rdbtnFaseFinal.setBounds(257, 46, 109, 23);
		contentPane.add(rdbtnFaseFinal);
		
		JButton btnListarResultados = new JButton("Listar Resultados");
		btnListarResultados.setBounds(169, 76, 136, 23);
		contentPane.add(btnListarResultados);
		
		btnListarResultados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnFaseInicial.isSelected()) {
					new FaseInicial(comboBox.getSelectedIndex(), (String) comboBox.getSelectedItem());
				} else {
					new FaseFinal(comboBox.getSelectedIndex(), (String) comboBox.getSelectedItem());
				}
			}
		}); 
		
	}
	
	public void carregarCombobox() {
		listaBox = dao.getProvas();
		for (int i = 0; i < listaBox.size(); i++) {
			comboBox.addItem(listaBox.get(i));
		}
	}
}
