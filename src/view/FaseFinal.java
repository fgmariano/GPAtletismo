package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Resultado;
import persistence.AtletismoDAO;

public class FaseFinal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Resultado> lista = new ArrayList<Resultado>();
	private AtletismoDAO dao = new AtletismoDAO();

	public FaseFinal(int prova, String nome) {
		setTitle(nome + " - Fase Final");
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 339);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Atleta", "Pa\u00EDs", "Resultado"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(199);
		table.getColumnModel().getColumn(2).setPreferredWidth(207);
		table.getColumnModel().getColumn(3).setPreferredWidth(141);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
		    private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value,  
		            boolean isSelected, boolean hasFocus, int row, int column) {  
		        super.getTableCellRendererComponent(table, value, isSelected,  
		                hasFocus, row, column);  
		        if (row == 0) {  
		            setBackground(Color.YELLOW);  
		        } else if (row == 1) {
		        	setBackground(Color.LIGHT_GRAY);  
		        } else if (row == 2) {
		        	setBackground(Color.ORANGE);
		        } else {
		        	setBackground(null);
		        }
		        return this;  
		    }
		});
		if (preencherTabela(prova)) {
			setVisible(true);
		} else {
			setVisible(false);
		}
		
	}
	
	public boolean preencherTabela(int prova) {
		int row = 0;
		lista = dao.listarResultados(prova+1, 2);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "A bateria ainda não ocorreu !!");
			return false;
		}
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		if (modelo.getRowCount() > 0) {
			modelo.setRowCount(0);
		}
		
		for (Resultado e : lista) {
			if (e.isRecorde_evento())
				setEvento(row);
			if (e.isRecorde_mundial())
				setMundial(row);
			Object[] objeto = new Object[4];
			objeto[0] = e.getCod_atleta();
			objeto[1] = e.getNome_atleta();
			objeto[2] = e.getNome_pais();
			objeto[3] = e.getResultado();
			modelo.addRow(objeto);
			row++;
		}
		return true;
	}
	
	public void setEvento(int linha) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value,  
		            boolean isSelected, boolean hasFocus, int row, int column) {  
		        super.getTableCellRendererComponent(table, value, isSelected,  
		                hasFocus, row, column);  
		        if (row == linha) {
		            setBackground(Color.BLUE);
		        } else if (row == 0) {  
		            setBackground(Color.YELLOW);  
		        } else if (row == 1) {
		        	setBackground(Color.LIGHT_GRAY);  
		        } else if (row == 2) {
		        	setBackground(Color.ORANGE);
		        } else {
		        	setBackground(null);
		        }
		        return this;  
		    }
		});
	}
	
	public void setMundial(int linha) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value,  
		            boolean isSelected, boolean hasFocus, int row, int column) {  
		        super.getTableCellRendererComponent(table, value, isSelected,  
		                hasFocus, row, column);
		        if (row == linha) {
		            setBackground(Color.GREEN);
		        } else if (row == 0) {  
		            setBackground(Color.YELLOW);  
		        } else if (row == 1) {
		        	setBackground(Color.LIGHT_GRAY);  
		        } else if (row == 2) {
		        	setBackground(Color.ORANGE);
		        } else {
		        	setBackground(null);
		        }
		        return this;  
		    }
		});
	}
}
