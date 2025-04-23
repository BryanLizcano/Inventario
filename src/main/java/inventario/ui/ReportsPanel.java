package inventario.ui;

import inventario.controller.MainController;
import inventario.controller.ReportController;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class ReportsPanel extends JPanel {
    private final MainController controller;

    private JTextField txtFrom;
    private JTextField txtTo;
    private JButton btnGenerate;
    private JTextArea txtReport;

    public ReportsPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtFrom = new JTextField(8);
        txtTo = new JTextField(8);
        form.add(new JLabel("Desde (YYYY-MM-DD):")); form.add(txtFrom);
        form.add(new JLabel("Hasta (YYYY-MM-DD):")); form.add(txtTo);
        btnGenerate = new JButton("Generar Reporte");
        form.add(btnGenerate);
        add(form, BorderLayout.NORTH);

        txtReport = new JTextArea();
        txtReport.setEditable(false);
        add(new JScrollPane(txtReport), BorderLayout.CENTER);

        btnGenerate.addActionListener(e -> generate());
    }

    private void generate() {
        try {
            LocalDate from = LocalDate.parse(txtFrom.getText());
            LocalDate to = LocalDate.parse(txtTo.getText());
            Map<LocalDate, Double> report = controller.getProfitReport(from, to);
            StringBuilder sb = new StringBuilder();
            report.forEach((date, profit) -> sb.append(date).append(" = ").append(profit).append("\n"));
            txtReport.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generando reporte: " + ex.getMessage());
        }
    }
}

