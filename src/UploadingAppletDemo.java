/*
 <applet code="UploadingAppletDemo.class" width="500" height="250">
  <param name="ip" value="192.168.137.48"></param>
  <param name="uploading_path" value="c:\\two.jpg"></param>
  </applet>
 */


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.applet.Applet;
import java.awt.Graphics;

public class UploadingAppletDemo extends javax.swing.JApplet 
{
    String file=""; 
    String ip="";
    String uploading_path="";
    @Override
    public void init()
    {
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
            java.util.logging.Logger.getLogger(UploadingAppletDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadingAppletDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadingAppletDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadingAppletDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try 
        {
            java.awt.EventQueue.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    initComponents();
                    setSize(500,250);
                    setVisible(true);
                    ip = getParameter("ip");
                    System.out.println("Server IP:: "+ip);
                    uploading_path = getParameter("uploading_path");
                    System.out.println("Uploading_path:: "+uploading_path);
                    lb_upload_path.setText(uploading_path);
                    jProgressBar1.setVisible(false);
                    bt_upload.setVisible(false);
                    tfpath.setEditable(false);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfpath = new javax.swing.JTextField();
        bt_browse = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        bt_upload = new javax.swing.JButton();
        lb_upload_to = new javax.swing.JLabel();
        lb_upload_path = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("File Uploading Here");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 10, 270, 40);
        getContentPane().add(tfpath);
        tfpath.setBounds(30, 60, 300, 30);

        bt_browse.setBackground(new java.awt.Color(255, 255, 255));
        bt_browse.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_browse.setText("Browse");
        bt_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseActionPerformed(evt);
            }
        });
        getContentPane().add(bt_browse);
        bt_browse.setBounds(340, 60, 120, 30);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(30, 130, 300, 30);

        bt_upload.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_upload.setText("Upload");
        bt_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_uploadActionPerformed(evt);
            }
        });
        getContentPane().add(bt_upload);
        bt_upload.setBounds(340, 130, 120, 30);

        lb_upload_to.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_upload_to.setText("Upload To : ");
        getContentPane().add(lb_upload_to);
        lb_upload_to.setBounds(30, 190, 100, 30);

        lb_upload_path.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(lb_upload_path);
        lb_upload_path.setBounds(140, 180, 330, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseActionPerformed
        JFileChooser fc = new JFileChooser("computer");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fc.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
         {
            File fileobj = fc.getSelectedFile();
            file=fileobj.getPath();    
            tfpath.setText(file);
            jProgressBar1.setVisible(true);
            bt_upload.setVisible(true);
            bt_browse.setVisible(false);
         }
         else 
         {
             JOptionPane.showMessageDialog(null,"You Clicked Cancel");
             bt_browse.setVisible(true);
         }
    }//GEN-LAST:event_bt_browseActionPerformed

    private void bt_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_uploadActionPerformed
        Uploader o1 = new Uploader();
    }//GEN-LAST:event_bt_uploadActionPerformed

    class Uploader implements Runnable
    {
        Socket sock;
        Thread t;
        FileInputStream fis;
        DataOutputStream dos;
        
        public Uploader() 
        {
           try
           {
               sock = new Socket(ip,8200);
               dos = new DataOutputStream(sock.getOutputStream());
               t = new Thread(this);
               t.start();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        }
        public void run()
        {
             try
            {
                File f= new File(file);
                String filename=f.getName();
                filename = filename.replaceAll(" ","%20");
                uploading_path=uploading_path.replaceAll(" ","%20");
                long size=f.length();
                System.out.println("Size:: "+size);
                System.out.println("File:: "+file);

                dos.writeBytes("GET /upload HTTP/1.1\r\n" );
                dos.writeBytes("HOST: "+ip+":8200\r\n");
                dos.writeBytes("file: "+filename+"\r\n");
                dos.writeBytes("upload_to: "+uploading_path+"\r\n");
                dos.writeBytes("Content-Length: "+size+"\r\n");
                dos.writeBytes("\r\n");
                dos.flush();

                fis=new FileInputStream(file);
                byte b[]=new byte[10000];
                long count=0;
                int per;
                int r;
                while(true)
                {
                  r=fis.read(b,0,10000);
                  dos.write(b,0,r);
                  count=count+r;
        
                  per= (int)((count*100)/size);
                  jProgressBar1.setStringPainted(true);
                  System.out.println("per:: "+per);
                  jProgressBar1.setValue(per);
                  jProgressBar1.setString(per+"%");

                  if(count==size)
                  {
                      break;
                  }
                }
                fis.close();
                dos.close();
                dos.flush();
                JOptionPane.showMessageDialog(null,"File Send Successfully");
                bt_browse.setVisible(true);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_browse;
    private javax.swing.JButton bt_upload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lb_upload_path;
    private javax.swing.JLabel lb_upload_to;
    private javax.swing.JTextField tfpath;
    // End of variables declaration//GEN-END:variables
}
