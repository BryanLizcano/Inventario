package inventario.ui.componets;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class ReportPanel extends JPanel{

    private JComboBox<String> cbType;
    private JDateChooser dcFrom, dcTo;
    private JButton btnGenerate;
    private JTable table;
    private DefaultTableModel model;

    public ReportPanel() {
        setLayout(new BorderLayout(10,10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbType = new JComboBox<>(new String[]{"Reporte de Ventas","Reporte de Stock","Reporte de Ganancias"});
        dcFrom = new JDateChooser();
        dcTo = new JDateChooser();
        btnGenerate = new JButton("Generar Reporte");
        top.add(cbType); top.add(new JLabel("Desde:")); top.add(dcFrom);
        top.add(new JLabel("Hasta:")); top.add(dcTo); top.add(btnGenerate);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnGenerate.addActionListener(e -> onGenerate());
    }

    private void onGenerate() {
        String tipo = (String)cbType.getSelectedItem();
        Date from = dcFrom.getDate();
        Date to = dcTo.getDate();
        // TODO: consulta según tipo y fechas
        model.setColumnIdentifiers(new String[]{"Col1","Col2","Col3"});
        model.setRowCount(0);
        // ejemplo
        model.addRow(new Object[]{"--","--","--"});
    }

}
