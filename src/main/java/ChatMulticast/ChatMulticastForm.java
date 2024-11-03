package ChatMulticast;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class ChatMulticastForm extends javax.swing.JFrame {

    //Entrada de Dados
    private String username = "";
    private int multicastPort;
    private InetAddress multicastAddr;
    private MulticastSocket sock;
    private SocketAddress sockAddr;
    static Connection connection;

    public ChatMulticastForm() {
        initComponents();
        tfMulticastAddr.setText("224.0.0.251");
        tfPort.setText("50000");
        bEnviar.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tfMessage = new javax.swing.JTextField();
        bEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bEnviar = new javax.swing.JButton();
        tfUsername = new javax.swing.JTextField();
        bSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfMulticastAddr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 353, 187);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Digite sua Mensagem:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(19, 325, 140, 16);
        getContentPane().add(tfMessage);
        tfMessage.setBounds(19, 345, 270, 24);

        bEntrar.setBackground(new java.awt.Color(255, 255, 255));
        bEntrar.setForeground(new java.awt.Color(0, 0, 0));
        bEntrar.setText("Entrar");
        bEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(bEntrar);
        bEntrar.setBounds(280, 70, 90, 24);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 30, 50, 16);

        bEnviar.setBackground(new java.awt.Color(0, 204, 0));
        bEnviar.setForeground(new java.awt.Color(0, 0, 0));
        bEnviar.setText("Enviar");
        bEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(bEnviar);
        bEnviar.setBounds(293, 345, 80, 24);
        getContentPane().add(tfUsername);
        tfUsername.setBounds(20, 50, 144, 24);

        bSair.setBackground(new java.awt.Color(255, 255, 255));
        bSair.setForeground(new java.awt.Color(0, 0, 0));
        bSair.setText("Sair");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });
        getContentPane().add(bSair);
        bSair.setBounds(280, 100, 90, 24);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Endereço IP: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 80, 113, 16);
        getContentPane().add(tfMulticastAddr);
        tfMulticastAddr.setBounds(20, 100, 144, 24);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Porta:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(180, 80, 40, 16);
        getContentPane().add(tfPort);
        tfPort.setBounds(180, 100, 73, 24);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 400);

        setSize(new java.awt.Dimension(414, 437));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEntrarActionPerformed
        //valida username
        username = tfUsername.getText();
        if (username.length() < 2) {
            JOptionPane.showMessageDialog(null, "Username inválido",
                    "Erro no campo Username.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //valida porta
        if (tfPort.getText().length() != 0) {
            try {
                multicastPort = Integer.parseInt(tfPort.getText());
                if ((multicastPort >= 1 && multicastPort <= 65535) == false) {
                    JOptionPane.showMessageDialog(null,
                            "Porta fora dos paremetros de 1-65535",
                            "Erro no campo Porta.", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Erro no campo Porta.", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "A porta não pode estar vazia",
                    "Erro no campo Porta.", JOptionPane.ERROR_MESSAGE);
        }

        //valida multicast
        try {
            multicastAddr = InetAddress.getByName(tfMulticastAddr.getText());

        } catch (HeadlessException | UnknownHostException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro no Endereço Multicast.", JOptionPane.ERROR_MESSAGE);

        }
        // valida Sockt
        try {
            sock = new MulticastSocket(multicastPort);
            connection = new Connection(sock, txtArea);
            connection.start();
            sockAddr = new InetSocketAddress(multicastAddr, multicastPort);
            sock.joinGroup(multicastAddr);

            SendMessage("Entrou no grupo");
            bEnviar.setEnabled(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro de socket", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bEntrarActionPerformed

    private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
        try {
            SendMessage("Saiu da sala");
        } catch (IOException ex) {
            Logger.getLogger(ChatMulticastForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);

    }//GEN-LAST:event_bSairActionPerformed

    private void bEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnviarActionPerformed
        try {
            SendMessage(tfMessage.getText());
        } catch (IOException ex) {
            Logger.getLogger(ChatMulticastForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfMessage.setText("");

    }//GEN-LAST:event_bEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEntrar;
    private javax.swing.JButton bEnviar;
    private javax.swing.JButton bSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfMessage;
    private javax.swing.JTextField tfMulticastAddr;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    private void SendMessage(String message) throws IOException {
        try {
            // Obtem a data e hora atual
            Date dataHoraAtual = new Date();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
            String time = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

            // Cria um objeto JSON com a data, hora, nome de usuário e mensagem
            String msg = message;
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("date", date);
            jsonObj.put("time", time);
            jsonObj.put("username", username);
            jsonObj.put("message", msg);

            // Converte o objeto JSON para um array de bytes
            byte[] data = jsonObj.toString().getBytes();

            // Cria um pacote de datagrama com os dados da mensagem e o envia
            DatagramPacket pkt = new DatagramPacket(data, data.length, multicastAddr, multicastPort);
            sock.send(pkt);

        } catch (IOException e) {
            // Exibe uma mensagem de erro se ocorrer um erro de entrada/saída ao enviar a mensagem
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro de socket", JOptionPane.ERROR_MESSAGE);
        }
    }
}
