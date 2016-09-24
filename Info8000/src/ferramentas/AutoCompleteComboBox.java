/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collection;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Marcos
 */
public class AutoCompleteComboBox extends JComboBox
{
    public AutoCompleteComboBox()
    {
        super();
        initialize(false);
    }

    public AutoCompleteComboBox(ComboBoxModel aModel)
    {
        super(aModel);
        initialize(false);
    }

    public AutoCompleteComboBox(Object... items)
    {
        super(items);
        initialize(false);
    }

    public AutoCompleteComboBox(Collection< ? > items)
    {
        super(new Vector<Object>(items));
        initialize(false);
    }

    public AutoCompleteComboBox(boolean editable)
    {
        super();
        initialize(editable);
    }

    public AutoCompleteComboBox(ComboBoxModel aModel, boolean editable)
    {
        super(aModel);
        initialize(editable);
    }

    public AutoCompleteComboBox(Collection< ? > items, boolean editable)
    {
        super(new Vector<Object>(items));
        initialize(editable);
    }

    private void initialize(boolean editable)
    {
        setEditable(editable);

        AutoCompleteDecorator.decorate(this);
        final Color gray = getBackground();
        final Color green = UIManager.getColor("ComboBox.selectionBackground");
        // final Color selection = ((JTextComponent)
        // getEditor().getEditorComponent()).getSelectionColor();

        getEditor().getEditorComponent().setBackground(gray);
        ((JTextComponent) getEditor().getEditorComponent()).setSelectionColor(gray);
        ((JTextComponent) getEditor().getEditorComponent()).setCaretColor(Color.white);
        ((JTextComponent) getEditor().getEditorComponent()).setForeground(((JTextComponent) getEditor().getEditorComponent()).getSelectedTextColor());

        getEditor().getEditorComponent().addFocusListener(new FocusListener()
        {
            public void focusGained(FocusEvent e)
            {
                getEditor().getEditorComponent().setBackground(green);
                ((JTextComponent) getEditor().getEditorComponent()).setSelectionColor(green);
            }

            public void focusLost(FocusEvent e)
            {
                getEditor().getEditorComponent().setBackground(gray);
                ((JTextComponent) getEditor().getEditorComponent()).setSelectionColor(gray);
            }
        });
    }

    @Override
    public void setEnabled(boolean b)
    {
        super.setEnabled(b);
        getEditor().getEditorComponent().setBackground(getBackground());
    }
}
