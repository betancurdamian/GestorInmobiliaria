package view;

public class JFramePrincipal extends javax.swing.JFrame {

    public JFramePrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel_principal = new javax.swing.JPanel();
        jPanel_contenido = new javax.swing.JPanel();
        jPanel_pie = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu_archivo = new javax.swing.JMenu();
        jMenu_cliente = new javax.swing.JMenu();
        jMenuItem_locador = new javax.swing.JMenuItem();
        jMenuItem_locatario = new javax.swing.JMenuItem();
        jMenu_inmueble = new javax.swing.JMenu();
        jMenu_venta = new javax.swing.JMenu();
        jMenu_alquiler = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor Inmobiliario");
        setMinimumSize(new java.awt.Dimension(416, 485));
        setPreferredSize(new java.awt.Dimension(416, 485));

        jPanel_principal.setBackground(new java.awt.Color(255, 0, 255));
        jPanel_principal.setMinimumSize(new java.awt.Dimension(400, 425));
        jPanel_principal.setPreferredSize(new java.awt.Dimension(400, 425));
        jPanel_principal.setLayout(new java.awt.BorderLayout());

        jPanel_contenido.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_contenido.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel_contenido.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel_contenido.setLayout(new java.awt.BorderLayout());
        jPanel_principal.add(jPanel_contenido, java.awt.BorderLayout.CENTER);

        jPanel_pie.setBackground(new java.awt.Color(153, 153, 153));
        jPanel_pie.setAlignmentX(0.0F);
        jPanel_pie.setAlignmentY(0.0F);
        jPanel_pie.setMinimumSize(new java.awt.Dimension(400, 25));
        jPanel_pie.setPreferredSize(new java.awt.Dimension(400, 25));
        jPanel_pie.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        jLabel1.setText("jLabel1");
        jPanel_pie.add(jLabel1);

        jLabel2.setText("jLabel2");
        jPanel_pie.add(jLabel2);

        jLabel3.setText("jLabel3");
        jPanel_pie.add(jLabel3);

        jLabel4.setText("jLabel4");
        jPanel_pie.add(jLabel4);

        jPanel_principal.add(jPanel_pie, java.awt.BorderLayout.PAGE_END);

        jMenuBar.setAlignmentX(0.0F);
        jMenuBar.setMinimumSize(new java.awt.Dimension(400, 20));
        jMenuBar.setPreferredSize(new java.awt.Dimension(400, 20));

        jMenu_archivo.setText("Archivo");
        jMenuBar.add(jMenu_archivo);

        jMenu_cliente.setText("Clientes");

        jMenuItem_locador.setText("Locadores");
        jMenu_cliente.add(jMenuItem_locador);

        jMenuItem_locatario.setText("Locatarios");
        jMenu_cliente.add(jMenuItem_locatario);

        jMenuBar.add(jMenu_cliente);

        jMenu_inmueble.setText("Inmuebles");
        jMenuBar.add(jMenu_inmueble);

        jMenu_venta.setText("Ventas");
        jMenuBar.add(jMenu_venta);

        jMenu_alquiler.setText("Alquileres");
        jMenuBar.add(jMenu_alquiler);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem_locador;
    private javax.swing.JMenuItem jMenuItem_locatario;
    private javax.swing.JMenu jMenu_alquiler;
    private javax.swing.JMenu jMenu_archivo;
    private javax.swing.JMenu jMenu_cliente;
    private javax.swing.JMenu jMenu_inmueble;
    private javax.swing.JMenu jMenu_venta;
    private javax.swing.JPanel jPanel_contenido;
    private javax.swing.JPanel jPanel_pie;
    private javax.swing.JPanel jPanel_principal;
    // End of variables declaration//GEN-END:variables

    public void arranca() {
        setLocationRelativeTo(null);// centra la ventana en la pantalla
        setVisible(true);// visualiza la ventana
    }

    public javax.swing.JMenuItem getjMenuItem_locador() {
        return jMenuItem_locador;
    }

    public javax.swing.JMenuItem getjMenuItem_locatario() {
        return jMenuItem_locatario;
    }

    public javax.swing.JPanel getjPanel_contenido() {
        return jPanel_contenido;
    }

    public javax.swing.JPanel getjPanel_pie() {
        return jPanel_pie;
    }

    

    

    
    
}
