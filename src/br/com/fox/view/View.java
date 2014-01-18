package br.com.fox.view;

import br.com.fox.control.ClienteJpaController;
import br.com.fox.control.SinalRouterJpaController;
import br.com.fox.control.ZonaJpaController;
import br.com.fox.db.Cliente;
import br.com.fox.db.SinalRouter;
import br.com.fox.db.Zona;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Dvr
 */
public class View extends javax.swing.JFrame {

    private Config config = new Config();
    private Cliente selectedCliente;
    private String ip;
    private SinalRouter sr;

    public View(GraphicsConfiguration gc) {

        super(gc);

        initComponents();
        carregaConfig();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void setSinalRouter(SinalRouter sr) {
        this.sr = sr;
    }

    private void viewCameras() {
        if (selectedCliente != null) {
            int qtdCameras = 0;

            for (int i = 0; i < selectedCliente.getZonaList().size(); i++) {
                int nuc = selectedCliente.getZonaList().get(i).getCamera();
                if (nuc > 0) {
                    qtdCameras++;   
                }
            }
            
            
            if (qtdCameras == 1) {
                desenhaTela(1, 1, 1);
            } else 
            if (qtdCameras == 2) {
                desenhaTela(2, 1, 2);
            } else {
                if (qtdCameras <= 4 && qtdCameras > 2) {
                    desenhaTela(2, 2, 4);
                } else {
                    if (qtdCameras <= 8 && qtdCameras > 4) {
                        desenhaTela(4, 2, 8);                        
                    } else {
                        if (qtdCameras <= 16 && qtdCameras > 8) {
                            desenhaTela(6, 3, 16);
                        } else {
                            if (qtdCameras <= 32 && qtdCameras > 16) {
                                desenhaTela(8, 4, 32);
                            }
                        }
                    }
                }
            }
        }
    }

    private void desenhaTela(int col, int row, int qtdTela) {
        
        ZonaJpaController jpaZona = new ZonaJpaController();
        final List<Zona> listZona = jpaZona.getCameras(selectedCliente);          
        
        int width, height;
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //int rows = (int) Math.round((double)qtdTela/col);                
//        if (qtdTela == 32) {
//            width = (int) (screenSize.getWidth() / col) - (3*col);
//            height = (int) (screenSize.getHeight() / rows - 80);            
//        } else {           
            //width = (int) (screenSize.getWidth() / col) - (5*col);                
            //height = (int) (screenSize.getHeight() / row - (3*row));
//        }
        
            
        width = (int) (this.getWidth() / col) - (5*col);
        height = (int) (this.getHeight() / row) - (5*row) - 50;
        
        System.out.println(this.getWidth()+" - "+this.getHeight());
        
        pnCamera.setLayout(new MigLayout("wrap " + col));
        //pnCamera.setLayout(new MigLayout());
      
        
        
        for (int i = 0; i < qtdTela; i++) {
        //for (int i = 0; i < listZona.size(); i++) {            
            
            if( i <= listZona.size() ) {
                
                final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
                mediaPlayerComponent.setVisible(true);

                //final EmbeddedMediaPlayerComponent mediaPlayerRecord = new EmbeddedMediaPlayerComponent();

                JPanel painel = new JPanel(new MigLayout("wrap 2"));
                painel.setBackground(Color.getHSBColor(80, 255, 100));

                try {

                    final JLabel lblCam = new JLabel(listZona.get(i).getAreas());
                    lblCam.setForeground(Color.blue);
                    lblCam.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));

                    if (listZona.get(i).getCamera() > 0) {
                        painel.setToolTipText(lblCam.getText());
                    } //else {
                    //    painel.setToolTipText("Não possui câmera");
                    //    lblCam.setText("Não possui câmera");
                    //}

                    final int nuc = i;
                    //final int nuc = qtdCamera;
                    JButton btnFullScren = new JButton("Tela Cheia");
                    btnFullScren.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();    
                            mediaPlayerComponent.addMouseListener(new MouseAdapter() {
                                
                                @Override                                
                                public void mouseClicked(MouseEvent e) {
                                    if( e.getClickCount() == 2 ) {
                                        //mediaPlayerComponent.setfu
                                    }
                                }
                            });
                            
                            
                            
                            final JFrame fullscreen = new JFrame(lblCam.getText() + " - Tela Cheia");
                            fullscreen.setIconImage(new ImageIcon(getClass().getResource("/br/com/fox/icones/Actions-window-fullscreen-icon.png")).getImage());
                            fullscreen.setExtendedState(MAXIMIZED_BOTH);
                            fullscreen.setContentPane(mediaPlayerComponent);
                            fullscreen.addKeyListener(new KeyAdapter() {

                                @Override
                                public void keyReleased(KeyEvent e) {
                                    if (e.getKeyCode() == 27) {     
                                        mediaPlayerComponent.getMediaPlayer().stop();
                                        mediaPlayerComponent.getMediaPlayer().release();                                            
                                        mediaPlayerComponent.release();
                                        fullscreen.dispose();
                                    }   
                                }
                            });
                            fullscreen.addWindowListener(new WindowAdapter() {
                                
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    
                                    mediaPlayerComponent.getMediaPlayer().stop();
                                    mediaPlayerComponent.getMediaPlayer().release();                                                                        
                                    mediaPlayerComponent.release();
                                    fullscreen.dispose();
                                }
                            });
                            fullscreen.setVisible(true);

                            mediaPlayerComponent.getMediaPlayer().playMedia("HTTP://" + ip + ":9540/IPLLREDIR:" + listZona.get(nuc).getCamera());
                        }
                    });
                                        

                    mediaPlayerComponent.getSize();
                    //painel.add(mediaPlayerRecord);
                    //painel.add(mediaPlayerComponent);
                    //painel.add(mediaPlayerComponent, "grow, push, cell 0 2 3");
                    painel.add(mediaPlayerComponent, "width :"+width+":,height :"+height+":,wrap");
                    //painel.add(mediaPlayerComponent, "width :250:,height :250:,wrap");
                    painel.add(btnFullScren, "align left, split");
                    //painel.add(btnFullScren);
                    //painel.add(lblCam, "align right");
                    painel.add(lblCam);

                    //pnCamera.add(painel, "grow, push");
                    if( (i+1) == col )                    
                        pnCamera.add(painel, "wrap");
                    else
                        pnCamera.add(painel);

                    mediaPlayerComponent.getMediaPlayer().playMedia("HTTP://" + ip + ":9540/IPLLREDIR:" + listZona.get(nuc).getCamera());

                    final StringBuilder fileName = new StringBuilder("");
                    fileName.append(sr.getNor()).append("_").append(new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(sr.getDat())).append("_").append(listZona.get(nuc).getCamera()).append("_all.flv");
                    
                    //mediaPlayerRecord.getMediaPlayer().playMedia("HTTP://" + ip + ":9540/IPLLREDIR:" + listZona.get(i).getCamera(), getMediaOptions("c:\\" + fileName));

                    this.addWindowListener(new WindowAdapter() {

                        @Override
                        public void windowClosing(WindowEvent e) {

                            mediaPlayerComponent.getMediaPlayer().stop();
                            mediaPlayerComponent.getMediaPlayer().release();

                            //mediaPlayerRecord.getMediaPlayer().stop();
                            //mediaPlayerRecord.getMediaPlayer().release();

                            /*
                            //enviando arquivo por FTP
                            FTPClient ftp = new FTPClient();
                            InputStream is = null;

                            try {
                                ftp.connect(config.getHostFTP(), config.getPortaFTP());

                                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                                    ftp.login(config.getUserFTP(), config.getPassFTP());

                                    is = new FileInputStream("c:\\" + fileName);

                                    ftp.setFileTransferMode(FTPClient.BLOCK_TRANSFER_MODE);
                                    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

                                    ftp.makeDirectory("Seegate 2.0");
                                    ftp.changeWorkingDirectory("Seegate 2.0");

                                    ftp.makeDirectory("videos");
                                    ftp.changeWorkingDirectory("videos");

                                    ftp.makeDirectory(sr.getNor());
                                    ftp.changeWorkingDirectory(sr.getNor());

                                    ftp.makeDirectory(new SimpleDateFormat("ddMMyyyy").format(sr.getDat()));
                                    ftp.changeWorkingDirectory(new SimpleDateFormat("ddMMyyyy").format(sr.getDat()));

                                    ftp.storeFile(fileName.toString(), is);

                                    ftp.disconnect();

                                } else {
                                    //erro ao se conectar  
                                    ftp.disconnect();
                                    System.out.println("Conexão recusada");
                                }
                            } catch (SocketException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                try {
                                    ftp.disconnect();
                                    is.close();

                                    File file = new File("c:\\" + fileName);
                                    file.delete();
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                            //Fim do envio
                            * 
                            * */                        
                        }
                    });

                } catch (Exception e) {
                    mediaPlayerComponent.release();
                    pnCamera.remove(mediaPlayerComponent);
                    
                    System.out.println(e.getMessage());
                    break;
                }

            }

            paintComponents(this.getGraphics());
        
        }

    }    


    private void carregaConfig() {
        try {
            File file = new File("config.xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(file);
            Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator i = list.iterator();

            while (i.hasNext()) {
                Element xml = (Element) i.next();

                config.setPathRouterSnapshot(xml.getChildText("pathRouterSnapshot"));
                config.setPathDestSnapshot(xml.getChildText("pathDestSnapshot"));
                config.setSmtpHostEmail(xml.getChildText("smtpHostEmail"));
                config.setSmtpPortEmail(Integer.parseInt(xml.getChildText("smtpPortEmail")));
                config.setUserEmail(xml.getChildText("userEmail"));
                config.setPassEmail(xml.getChildText("passEmail"));
                config.setHostFTP(xml.getChildText("hostFTP"));
                config.setPortaFTP(Integer.parseInt(xml.getChildText("portaFTP")));
                config.setUserFTP(xml.getChildText("userFTP"));
                config.setPassFTP(xml.getChildText("passFTP"));
                config.setPathDestFTP(xml.getChildText("pathDestFTP"));
            }
        } catch (JDOMException ex) {
            JOptionPane.showMessageDialog(null, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }

    private String[] getMediaOptions(String destination) {

        String SOUT = ":sout=#transcode{vcodec=FLV1,vb=%d,scale=%f}:duplicate{dst=file{dst=%s}}";
        String FPS = ":screen-fps=%d";
        String CACHING = ":screen-caching=%d";
        //int fps = 20;
        int fps = 10;
        int caching = 500;
        int bits = 4096;
        float scale = 0.5f;

        return new String[]{
                    String.format(SOUT, bits, scale, destination),
                    String.format(FPS, fps),
                    String.format(CACHING, caching)
                };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCamera = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Câmeras");
        setIconImage(new ImageIcon(getClass().getResource("/br/com/fox/icones/eye.png")).getImage());
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        pnCamera.setBackground(new java.awt.Color(102, 102, 102));
        pnCamera.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCamera, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCamera, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    viewCameras();
}//GEN-LAST:event_formComponentShown

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        NativeLibrary.addSearchPath(                
                RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC");
                
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        final String ip = args[0];
        final int cliente = Integer.parseInt(args[1]);
        final int idSr = Integer.parseInt(args[2]);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                View view = null;
                
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] devices = ge.getScreenDevices();
                
                if (devices.length == 1) {
                    view = new View(devices[0].getDefaultConfiguration());
                }else{
                    view = new View(devices[1].getDefaultConfiguration());
                }

                view.setIp(ip);

                ClienteJpaController jpa = new ClienteJpaController();
                SinalRouterJpaController jpaSr = new SinalRouterJpaController();

                view.setSinalRouter(jpaSr.findSinalRouter(idSr));
                view.setSelectedCliente(jpa.findCliente(cliente));
                view.setVisible(true);


                //new View().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnCamera;
    // End of variables declaration//GEN-END:variables
}
