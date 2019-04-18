package Recognizer;

import Util.ConectaBanco;
import Util.ControlPerson;
import Util.ModelPerson;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_face;
import static org.bytedeco.javacpp.opencv_imgcodecs.imencode;
import org.bytedeco.javacpp.opencv_imgproc;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_videoio; 

public final class Recognizer extends javax.swing.JFrame {

    private Recognizer.DaemonThread myThread = null;
    String i="";
    //JavaCV
    opencv_videoio.VideoCapture webSource = null;
    opencv_core.Mat cameraImage = new opencv_core.Mat();
    opencv_objdetect.CascadeClassifier cascade = new opencv_objdetect.CascadeClassifier("C:\\Users\\email\\Downloads\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
    opencv_face.FaceRecognizer recognizer = opencv_face.LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    opencv_core.RectVector detectedFaces = new opencv_core.RectVector();

    //Vars
    String root, firstNamePerson, lastNamePerson, officePerson, dobPerson, telefone;
    //Social Info
    String facebook, insta, linkedin, git;
    int idPerson;

    //Utils
    ConectaBanco conecta = new ConectaBanco();

    //Loading students
    private final String[] defaultValues = new String[17];
    DefaultListModel<String> model;
    DefaultListModel<String> model1 = new DefaultListModel<>();
    
    String var= "";
    
    //date stringa
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    Date date = new Date();
    String dat = String.valueOf(dateFormat.format(date)); 
        
    
    public Recognizer() {
        initComponents();

        absent_list.setModel(createDefaultListModel()); 
        attendance_list.setModel(model1);
        
        recognizer.read("C:\\photos\\classifierLBPH.yml");
        recognizer.setThreshold(80);
 
        startCamera();
    }

     private ListModel<String> createDefaultListModel() {
           model = new DefaultListModel<>();
            
           conecta.conexao();
                try { 
                    String SQL = "SELECT name FROM mscit";
                    conecta.executaSQL(SQL);
                    int i = 0;
                    while (conecta.rs.next()) 
                    {
                        String s = conecta.rs.getString("name");
                        defaultValues[i] = s;
                        i++;
                        model.addElement(s);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                conecta.desconecta();
           while (true) {
              
               break;
           }
           return model;
       }
    private void filter(String text) { 
        
      try
      {
            for (String s : defaultValues) {  
            if (s.startsWith(text)) {
                if (model.contains(text)) {
                    model.removeElement(s); 
                    System.out.println("removing..."+s); 
                    if (!model1.contains(text)) {
                        model1.addElement(s);  
                    }
                }
            }    
        }
      }
      catch (Exception e)
      {
          System.out.println(e);
      }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        label_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_id_label = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        saveButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        absent_list = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        attendance_list = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Security System - Recognizer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 380, 10));
        jPanel4.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, 20));

        jLabel4.setText("Name:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 430, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(95, 106, 117));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Hi, $name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 400, 40));

        txt_id_label.setBackground(new java.awt.Color(68, 128, 193));
        txt_id_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_id_label.setForeground(new java.awt.Color(255, 255, 255));
        txt_id_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_label.setText("1");
        txt_id_label.setOpaque(true);
        jPanel1.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        deleteButton.setBackground(new java.awt.Color(255, 102, 102));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.setContentAreaFilled(false);
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deleteButton.setOpaque(true);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 160, -1));

        saveButton1.setBackground(new java.awt.Color(93, 200, 119));
        saveButton1.setForeground(new java.awt.Color(255, 255, 255));
        saveButton1.setText("Finish");
        saveButton1.setContentAreaFilled(false);
        saveButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveButton1.setOpaque(true);
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(saveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 160, -1));

        jScrollPane1.setViewportView(absent_list);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 180, 230));

        jScrollPane3.setViewportView(attendance_list);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 180, 230));

        jLabel3.setText("Namelist");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        jLabel5.setText("Present students");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 440, 490));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 480));

        setSize(new java.awt.Dimension(796, 527));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        int selectedIndex = attendance_list.getSelectedIndex(); 
        if (selectedIndex != -1) {
            String s = model1.getElementAt(selectedIndex);
            model.addElement(model1.getElementAt(selectedIndex));
            model1.remove(selectedIndex);
            
            //delete the attendance
            deleteattendance(s);
        } 
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // through the database
        
        for (int j = 0; j < model.getSize(); j++) {
            int id = getIdof(model.getElementAt(j));
            ControlPerson cod = new ControlPerson();
            ModelPerson mod = new ModelPerson(); 
            mod.setId(id);
            mod.setPresent(0);
            mod.setDateon(dat);
            cod.attendance(mod);
        }
//        String s = String.valueOf(model.getElementAt(10));
        JOptionPane.showMessageDialog(null,"Attendence closed today");
        this.dispose();
     
    }//GEN-LAST:event_saveButton1ActionPerformed

    public void deleteattendance(String name)
    {
        // get id of the respective name 
        int id = getIdof(name);
        
        //        ControlPerson cod = new ControlPerson();
        //        ModelPerson mod = new ModelPerson(); 
        //        mod.setFirst_name(dat);
        //        mod.setId(id);
        //        cod.deleteattendance(mod);

        System.out.println("id is "+String.valueOf(id)+"   "+dat);
        
        conecta.conexao();
        try { 
            String SQL = "DELETE from attendance where regno = "+ id +" and dateon = '"+ dat +"'";
            conecta.executeSQL(SQL); 
            System.out.println("fucked");  
        } catch (Exception e) {
            System.out.println("Exception:...."+e); 
        }
        conecta.desconecta();
    }
    
    public int getIdof(String name)
    {
        int id = 0;
        conecta.conexao();
        try { 
            String SQL = "SELECT regno FROM mscit WHERE name = '" + name +"'";
            conecta.executaSQL(SQL);
            if (conecta.rs.next()) {
                id = Integer.parseInt(conecta.rs.getString("regno")); 
            }
        } catch (Exception e) {
            System.out.println("Exception");//+String.valueOf(e.getMessage())); 
        }
        conecta.desconecta();
        return id;
    }
     
    public static void main(String args[]) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); 
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Recognizer().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> absent_list;
    private javax.swing.JList<String> attendance_list;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label_name;
    private javax.swing.JButton saveButton1;
    private javax.swing.JLabel txt_id_label;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;
        
        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = jPanel2.getGraphics();

                            opencv_core.Mat imageGray = new opencv_core.Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            opencv_core.RectVector detectedFace = new opencv_core.RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new opencv_core.Size(150, 150), new opencv_core.Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                opencv_core.Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new opencv_core.Scalar(0, 255, 0, 0));
                                opencv_core.Mat faceCapturada = new opencv_core.Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new opencv_core.Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String nome;
                                nome = firstNamePerson;

                                if (prediction == -1) {
                                    label_name.setText("");
                                    idPerson = 0;
                                } else {
//                                    System.out.println("log "+confidence.get(0));
                                    idPerson = prediction;
                                    rec(); 
                                } 
                                break;
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() + 600, 0, 0, buff.getWidth() + 600, buff.getHeight() + 600, null)) {
                                if (runnable == false) {
                                    System.out.println("Salve a Foto");
                                    this.wait();
                                }
                            }
                        }
                    }
                    catch (IOException | InterruptedException ex) {
                    }
                }
            }
        }
    }

    private void rec() {
   
        String dummy = "", name = "";
                conecta.conexao();
                try {
                 //   System.out.println("work aaguthu da....."+String.valueOf(idPerson));
                    
                    String SQL = "SELECT * FROM mscit WHERE regno = " + String.valueOf(idPerson);
                    conecta.executaSQL(SQL);
                    if (conecta.rs.next()) 
                    {
                        firstNamePerson = conecta.rs.getString("name");
                        jLabel10.setText("Hi, " + firstNamePerson);
                        label_name.setText(conecta.rs.getString("name"));
                        
                        filter(firstNamePerson);  
                        txt_id_label.setText(conecta.rs.getString("regno"));
                        attendanceregister(conecta.rs.getString("regno"));
                    }
                } catch (Exception e) {
                    System.out.println("Exception");//+String.valueOf(e.getMessage())); 
                }
                conecta.desconecta();
            }
       

    public void attendanceregister(String regno)
    {
        System.out.println("inside attendance"); 
        
        if (!regno.equals(var)) {
            ControlPerson cod = new ControlPerson();
            ModelPerson mod = new ModelPerson(); 
            mod.setId(Integer.parseInt(txt_id_label.getText()));
            mod.setPresent(1);
            mod.setDateon(dat);
            cod.attendance(mod);
            var = regno;    
        }
    }
    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera() {
        webSource = new opencv_videoio.VideoCapture(0);
        myThread = new Recognizer.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
}

//                        String s = label_name.getText();
//                        if (!i.equals(s)) {
//                            attendance_list.setModel(listModel);
//                            listModel.addElement(s);
//                        }
//                        i = s;

//                        String s = label_name.getText();
//                        
//                        for(int j=0; j<=listModel.getSize();j++)
//                        {
//                            if (!String.valueOf(attendance_list.indexToLocation(j)).equals(s)) {
//                                attendance_list.setModel(listModel);
//                                listModel.addElement(s);                           
//                            }
//                        }
//                        i = s;