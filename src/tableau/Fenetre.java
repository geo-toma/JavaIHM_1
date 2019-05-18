package tableau;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton change = new JButton("Changer la taille");
	private JButton retablir = new JButton("Retablir");
	private JButton add = new JButton("Ajouter une ligne");
	private JButton ver = new JButton("ver");
	private Double[] combobox = {1.2, 1.3, 1.4, 1.5};
	
	public Fenetre() {
		this.setTitle("Tableau");
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
//		Object[][] data = {{new String("Georges"),new JButton("A"),new JComboBox<Double>(new Double[] {1.2, 1.3, 1.4}), new Boolean(false)},
//				{new String("Serges"),new JButton("B"),new JComboBox<Double>(new Double[] {1.2, 1.3, 1.4}), new Boolean(true)},
//				{new String("dave"),new JButton("C"),new JComboBox<Double>(new Double[] {1.2, 1.3, 1.4}), new Boolean(false)},
//				{new String("sam"),new JButton("D"),new JComboBox<Double>(new Double[] {1.2, 1.3, 1.4}), new Boolean(true)},
//				{new String("geo"),new JButton("E"),new JComboBox<Double>(new Double[] {1.2, 1.3, 1.4}), new Boolean(false)}};
//		String tab[] = {"NOM", "AGE", "TAILLE", "OK?"};
//		
//		ZModel model = new ZModel(data, tab);
//		table = new JTable(model);
//		table.setRowHeight(25);
//		table.setDefaultRenderer(JButton.class, new TableComponent());
//		table.setDefaultRenderer(JComboBox.class, new TableComponent());
		
		Object[][] data = {{"Georges","20",1.2,new Boolean(false), "Suppression de la ligne"},
				{"Serges","19",1.3,new Boolean(false),"Suppression de la ligne"},
				{"dave","18",1.4,new Boolean(false), "Suppression de la ligne"},
				{"sam","20",1.5,new Boolean(false), "Suppression de la ligne"}};
		String tab[] = {"NOM", "AGE", "TAILLE", "OK?", "Suppression"};
		//table = new JTable(data, tab);
		YModel model = new YModel(data, tab);
		table = new JTable(model);
		table.setRowHeight(25);
		
		table.getColumn("AGE").setCellRenderer(new ButtonRendere());	
		//table.getColumn("TAILLE").setCellRenderer(new ComboBoxRenderer());	
		table.getColumn("AGE").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Suppression").setCellEditor(new ButtonEditor(new JCheckBox()));	
		
		JComboBox<Double> combo = new JComboBox<>(combobox);
		table.getColumn("TAILLE").setCellEditor(new DefaultCellEditor(combo));
		
		retablir.setEnabled(false);
		change.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeSize(200, 80);
				change.setEnabled(false);
				retablir.setEnabled(true);
			}
		});
		retablir.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeSize(75, 16);
				change.setEnabled(true);
				retablir.setEnabled(false);
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel)table.getModel()).addRow(new Object[] {"Bolo", "25",1.6, new Boolean(true), "Suppression de la ligne"});
			}
		});
		
		ver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexRow();
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(change);
		panel.add(retablir);
		panel.add(add);
		panel.add(ver);
		
		this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void indexRow() {
		for (int i = 0; i < table.getRowCount(); i++) {
			System.out.println(i);
		}
	}
	
	public void ChangeSize(int width, int height) {
		TableColumn column;
		for (int i = 0; i < table.getColumnCount(); i++) {
			if(i == 1) {
				column = table.getColumnModel().getColumn(i);
				column.setPreferredWidth(width);
			}
		}
		for (int i = 0; i < table.getRowCount(); i++) {
			if(i == 1)
				table.setRowHeight(i, height);
		}
	}
	
	class YModel extends DefaultTableModel{
		private static final long serialVersionUID = 1L;
		public YModel(Object[][] data, String[] title) {
			super(data, title);
		}
		
		public void setValueAt(Object value, int row, int col) {
			if(!this.getColumnName(col).equals("AGE") && !this.getColumnName(col).equals("Suppression") )
				super.setValueAt(value, row, col);
		}
		
		public Class<?> getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
		
	}
	
	
	class ZModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;

		private Object[][] data;
		private String[] title;
		
		public ZModel(Object[][] data, String[] title) {
			this.data = data;
			this.title = title;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return title.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
		
		public void setValueAt(Object value, int row, int col) {
			if(!this.getColumnName(col).equals("AGE") && !this.getColumnName(col).equals("Suppression") )
				this.data[row][col] = value;
		}
		
		public String getColumnName(int col) {
			return title[col];
		}
		
		public Class<?> getColumnClass(int col) {
			return data[0][col].getClass();
		}
		 
		public boolean isCellEditable(int row, int col) {
//			if(getValueAt(row, col) instanceof Boolean)
//				return false;
			return true;
		}
	}
	
	class TableComponent extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			if(value instanceof JButton)
				return (JButton) value;
			if(value instanceof JComboBox<?>)
				return (JComboBox<?>) value;
			return this;
		}
	}
	
	class ButtonRendere extends JButton implements TableCellRenderer{
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value != null)? value.toString() : "");
			return this;
		}
		
	}
	
	class ComboBoxRenderer extends JComboBox<Double> implements TableCellRenderer{
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			this.addItem(1.3);
			addItem(1.2);
			addItem(1.4);
			return this;
		}
		
	}
	
	class ButtonListener implements ActionListener{

		private int col, row;
		private JTable table;
		private int nbre = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!((JButton)e.getSource()).getText().equals("Suppression de la ligne")) {
				((DefaultTableModel)table.getModel()).setValueAt("new Valeu"+nbre++, row, col - 1);
				((DefaultTableModel)table.getModel()).fireTableCellUpdated(row, col - 1);
			} else {
				if(table.getSelectedRow() < 0)
					((DefaultTableModel)table.getModel()).removeRow(row);
				else {
					((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
				}
				
			}
				
		}
		public void setCol(int col) {
			this.col = col;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public void setTable(JTable table) {
			this.table = table;
		}
		
		
	}
	
	class ButtonEditor extends DefaultCellEditor{
		private static final long serialVersionUID = 1L;
		
		protected JButton button;
		private ButtonListener bListener = new ButtonListener();

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(bListener);
		}
		
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			bListener.setRow(row);
			bListener.setCol(column);
			bListener.setTable(table);
			button.setText((value != null)? value.toString() : "");
			return button;
		}
		
	}

}
