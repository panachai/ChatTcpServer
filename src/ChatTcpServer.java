
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ChatTcpServer extends javax.swing.JFrame {

    private DefaultTableModel model;
    private CreateServer createServer;
    private int port;

    private ServerSocket ss;
    private Socket s;

    private String msg;

    private Vector clientVector;

    public ChatTcpServer() {
        initComponents();
        clientVector = new Vector();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUser = new javax.swing.JTable();
        btClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PORT : ");

        tfPort.setText("8888");

        btStart.setText("START");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btStop.setText("STOP");
        btStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopActionPerformed(evt);
            }
        });

        lbStatus.setText("Status");

        tbUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IP", "Status"
            }
        ));
        jScrollPane1.setViewportView(tbUser);

        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btStop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btClear))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStart)
                    .addComponent(btStop)
                    .addComponent(lbStatus)
                    .addComponent(btClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void sendFromServer() {

        try {
            Socket s2 = new Socket("127.0.0.1", port);
            PrintWriter out = new PrintWriter(s2.getOutputStream());
            out.println("ServerStop");
            out.flush();
        } catch (IOException ioei) {
            System.out.println("IOException in Interruped : " + ioei);
        }

    }

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        port = Integer.parseInt(tfPort.getText());
        createServer = new CreateServer(port);

        createServer.start();

        tfPort.setEnabled(false);

        btStart.setEnabled(false);
        btStop.setEnabled(true);

        btClear.setEnabled(true);

        //show table status
        tbUser.setEnabled(true);
    }//GEN-LAST:event_btStartActionPerformed

    private void btStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopActionPerformed
        createServer.interrupt();
        sendFromServer();

        tfPort.setEnabled(true);

        btStart.setEnabled(true);
        btStop.setEnabled(false);

        btClear.setEnabled(false);

        //show table status
        tbUser.setEnabled(false);

        lbStatus.setText("Stop");
    }//GEN-LAST:event_btStopActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        //model.setRowCount(0);
    }//GEN-LAST:event_btClearActionPerformed

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
            java.util.logging.Logger.getLogger(ChatTcpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatTcpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatTcpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatTcpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatTcpServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTable tbUser;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables

    class ServerClient extends Thread {

        private BufferedReader in;  //character
        private PrintWriter out;    //character

        Socket clientSocket;
        String clientName;

        public ServerClient(Socket soc) {
            clientSocket = soc;
            //step 3 create input and output
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //byte to character to buffered
                out = new PrintWriter(clientSocket.getOutputStream()); //byte to character
            } catch (IOException ioe) {
                System.out.println("Contructor error ioe : " + ioe);
            }
            
            

        }

        public void run() {

            try {

                while (true) {
                    sleep(1);
                    //step 4 process
                    msg = in.readLine();    //echo server ส่งอะไรมา ตอบอันนั้นกลับ
                    //มันรวม pack in ด้วย clientSocket ไปแล้ว แล้วจะ echo ยังไง?

                    /*
                    //fisrt message get name
                    if(msg.substring){
                        
                        clientName = n;
                    }*/
                    //for(int i=0;i<clientVector.size();i++) {
                    //ทำต่อ
                    out.println(msg);       //echo server
                    out.flush();
                    //}

                }

            } catch (IOException ex) {
                System.out.println("IOE interruped : " + ex);
            } catch (InterruptedException ie) {
                System.out.println("ServerClient Interruped : " + ie);
            }

            //step 5 close
            //s.close();
            //ss.close();
        }

        public String getNames(){
            return clientName;
        }
        
        public Socket getSocket(){
            return clientSocket;
        }
        
        public void sendEveryUser(String msg) {

        }
        
        
    }

    class CreateServer extends Thread {

        private int ports;

        public CreateServer(int p) {
            ports = p;
        }

        public void run() {

            try {

                try {
                    //step 1 open port
                    ss = new ServerSocket(ports);
                } catch (IOException ioe) {
                    System.out.println("IOException port : " + ioe);
                }

                while (true) {

                    sleep(1);

                    //step 2 wait for connect from client
                    s = ss.accept();    //เหมือน wait() รอ socket ของ client ส่งมา

                    System.out.println("Socker Client : " + s);

                    ServerClient serverClient = new ServerClient(s);

                    serverClient.start();
                    //ใส่ object ServerClient
                    clientVector.addElement(serverClient);

                }

            } catch (IOException ioe) {
                System.out.println("Interruped IOE : " + ioe.getMessage());
            } catch (InterruptedException in) {

                System.out.println("Interrupted E : " + in);

                //ลบ socket ออกจาก server
                clientVector.removeAllElements();

                try {
                    s.close();
                    ss.close();
                } catch (IOException ioei) {
                    System.out.println("IOException in Interruped : " + ioei);
                }

            }
        }

    }

}
