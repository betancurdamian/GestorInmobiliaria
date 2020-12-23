package view;

public class JFrameLogin extends javax.swing.JFrame {

    public JFrameLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlbl_usuario = new javax.swing.JLabel();
        jtf_userName = new javax.swing.JTextField();
        jlbl_password = new javax.swing.JLabel();
        jbtn_login = new javax.swing.JButton();
        jlbl_mensaje = new javax.swing.JLabel();
        jtf_password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jlbl_usuario.setText("USERNAME:");

        jlbl_password.setText("PASSWORD:");

        jbtn_login.setText("LOGIN");

        jlbl_mensaje.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbl_mensaje.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_password)
                            .addComponent(jlbl_usuario))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_password, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jtf_userName)))
                    .addComponent(jlbl_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtn_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbl_usuario)
                    .addComponent(jtf_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbl_password)
                    .addComponent(jtf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtn_login)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtn_login;
    private javax.swing.JLabel jlbl_mensaje;
    private javax.swing.JLabel jlbl_password;
    private javax.swing.JLabel jlbl_usuario;
    private javax.swing.JPasswordField jtf_password;
    private javax.swing.JTextField jtf_userName;
    // End of variables declaration//GEN-END:variables

    public void arranca() {
        setLocationRelativeTo(null);// centra la ventana en la pantalla
        setVisible(true);// visualiza la ventana
    }

    public javax.swing.JLabel getJlbl_mensaje() {
        return jlbl_mensaje;
    }

    public void setJlbl_mensaje(javax.swing.JLabel jlbl_mensaje) {
        this.jlbl_mensaje = jlbl_mensaje;
    }

    public javax.swing.JPasswordField getJtf_password() {
        return jtf_password;
    }

    public void setJtf_password(javax.swing.JPasswordField jtf_password) {
        this.jtf_password = jtf_password;
    }

    public javax.swing.JTextField getJtf_username() {
        return jtf_userName;
    }

    public void setJtf_username(javax.swing.JTextField jtf_usuario) {
        this.jtf_userName = jtf_usuario;
    }

    public javax.swing.JButton getJbtn_login() {
        return jbtn_login;
    }

}
