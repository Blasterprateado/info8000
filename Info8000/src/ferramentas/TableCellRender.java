/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author USUARIO
 */
public class TableCellRender extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
	if(row % 2 == 0 ){
		setBackground(Color.LIGHT_GRAY);
	}else{
		setBackground(null);
		setForeground(new Color(51, 51, 51));
	}
	if(isSelected){
		setBackground(Color.GREEN);
		setForeground(getForeground()); 
	}
	
	return this;
}
}  

