package ipr.projekt.program;

import ipr.projekt.classes.Klient;
import ipr.projekt.dialogi.*;
import ipr.projekt.zakladki.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Michal
 */
public class MainWindow extends JFrame implements WindowListener {

    ZawrzyjZaklad zawrzyjZaklad = new ZawrzyjZaklad(this);
    Mecze mecze = new Mecze(this);
    MeczSingle mecz = new MeczSingle(this);
    HistoriaZakladow historiaZakladow = new HistoriaZakladow();
    Druzyny druzyny = new Druzyny();
    Sedziowie sedziowie = new Sedziowie();
    Zawodnicy zawodnicy = new Zawodnicy();
    ZawarteZaklady zawarteZaklady = new ZawarteZaklady((this));

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        
        tabbedPane.add(mecze, "Mecze");
        tabbedPane.add(druzyny, "Drużyny");
        tabbedPane.add(zawodnicy, "Zawodnicy");
        tabbedPane.add(sedziowie, "Sędziowie");
        tabbedPane.add(zawrzyjZaklad, "Zawrzyj zakład");
        tabbedPane.add(historiaZakladow, "Historia zakładów");
        tabbedPane.setEnabledAt(4, false);
        tabbedPane.setEnabledAt(5, false);
        addWindowListener(this);
        this.setResizable(false);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedTab = tabbedPane.getSelectedIndex();
                if(selectedTab == 5){
                    historiaZakladow.odswiezZakladke();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnZaloguj = new javax.swing.JButton();
        btnWyloguj = new javax.swing.JButton();
        btnEdytuj = new javax.swing.JButton();
        lblNazwaUzytkownika = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnZaloguj.setText("Zaloguj");
        btnZaloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZalogujActionPerformed(evt);
            }
        });

        btnWyloguj.setText("Wyloguj");
        btnWyloguj.setEnabled(false);
        btnWyloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWylogujActionPerformed(evt);
            }
        });

        btnEdytuj.setText("Edytuj Konto");
        btnEdytuj.setEnabled(false);
        btnEdytuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdytujActionPerformed(evt);
            }
        });

        lblNazwaUzytkownika.setText("Zalogowany użytkownik: -");

        jLabel2.setText("Klient");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNazwaUzytkownika)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnZaloguj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnWyloguj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdytuj)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnZaloguj)
                        .addComponent(btnWyloguj)
                        .addComponent(btnEdytuj))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(lblNazwaUzytkownika)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZalogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZalogujActionPerformed
        LoginDialog loginDlg = new LoginDialog(this, true);
        loginDlg.setVisible(true);

        if (loginDlg.isSucceeded()) {
            zaloguj();
        }
    }//GEN-LAST:event_btnZalogujActionPerformed

    private void btnWylogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWylogujActionPerformed
        wyloguj();
    }//GEN-LAST:event_btnWylogujActionPerformed

    private void btnEdytujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdytujActionPerformed
        EdytujKonto edytujKontoDlg = new EdytujKonto(this, true);
        edytujKontoDlg.setVisible(true);
        int msg = edytujKontoDlg.getReturnCode();
        switch (msg) {
            case 1:
                Klient klient = Klient.getInstance();
                String imie = klient.getImie();
                String nazwisko = klient.getNazwisko();
                lblNazwaUzytkownika.setText("Zalogowany użytkownik: " + klient.getNazwisko() + " " + klient.getImie());
                break;
            case 2:
                wyloguj();
                break;
        }


    }//GEN-LAST:event_btnEdytujActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        MySql mySQL = MySql.getInstance();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdytuj;
    private javax.swing.JButton btnWyloguj;
    private javax.swing.JButton btnZaloguj;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNazwaUzytkownika;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowClosing(WindowEvent e) {
        MySql.getInstance().closeConnection();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    private void wyloguj() {
        btnWyloguj.setEnabled(false);
        btnEdytuj.setEnabled(false);
        btnZaloguj.setEnabled(true);
        Klient.getInstance().clearUser();
        lblNazwaUzytkownika.setText("Zalogowany użytkownik: -");
        int index = tabbedPane.getSelectedIndex();
        if (index == 4 || index == 5) {
            tabbedPane.setSelectedIndex(0);
        }
        tabbedPane.setEnabledAt(4, false);
        tabbedPane.setEnabledAt(5, false);
    }

    private void zaloguj() {
        btnWyloguj.setEnabled(true);
        btnEdytuj.setEnabled(true);
        btnZaloguj.setEnabled(false);
        tabbedPane.setEnabledAt(4, true);
        tabbedPane.setEnabledAt(5, true);
        Klient klient = Klient.getInstance();
        lblNazwaUzytkownika.setText("Zalogowany użytkownik: " + klient.getNazwisko() + " " + klient.getImie());
    }
    
    public void switchMeczTab(int id){
        tabbedPane.setComponentAt(0, mecz);
        mecz.setIDMeczu(id);
        tabbedPane.repaint();
    }

    public void backMecze() {
        tabbedPane.setComponentAt(0, mecze);
        tabbedPane.repaint();
    }

    public void switchZakladTab() {
        tabbedPane.setComponentAt(4, zawarteZaklady);
        zawarteZaklady.odswiezZakladke();
        tabbedPane.repaint();
    }
    
    public void backZaklad() {
        tabbedPane.setComponentAt(4, zawrzyjZaklad);
        zawrzyjZaklad.odswiezZakladke();
        tabbedPane.repaint();
    }
}
