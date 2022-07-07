import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.filechooser.FileSystemView;

public class assign27
{
ServerSocket sersock;

public assign27() throws IOException
{
   try
   {
    sersock=new ServerSocket(8200);
    System.out.println("server started at port 8200");
    while(true)
    { 
       Socket sock;
       sock=sersock.accept();   
       System.out.println("Connection Build");
       new webserverreqhandler(sock);
    }
   }
   catch(Exception e)
   {
       e.printStackTrace();
   }  
}

class webserverreqhandler implements Runnable
{
    
     DataOutputStream dos;
     Thread t1;
     String s,firstline="",method,resource;
     Socket sock;
     File f;
     String upload_filename="",upload_to="";
     long upload_file_length=0;
     DataInputStream dis;
     String ip="";
     public webserverreqhandler(Socket socket) throws IOException
     {
         sock=socket;
         ip = sock.getLocalAddress().getHostAddress();   
         dis = new DataInputStream(sock.getInputStream());
         dos=new DataOutputStream(sock.getOutputStream());
         t1=new Thread(this);
         t1.start();
    }

        @Override
        public void run()
        {
          try
          {
            while(true)
            {
                   s=dis.readLine();
                   System.out.println(s);
                   if(s.equals(""))
                   {
                       break;
                   }
                    else if(s.startsWith("GET"))
                    {
                        firstline=s;
                    }
                   else if(s.startsWith("file"))
                    {
                       StringTokenizer st = new StringTokenizer(s);
                       st.nextToken();
                       upload_filename = st.nextToken();
                       upload_filename=upload_filename.replaceAll("%20"," ");
                       System.out.println("name"+upload_filename);
                    }
                    else if(s.startsWith("Content-Length"))
                    {
                        StringTokenizer st = new StringTokenizer(s);
                        st.nextToken();
                        upload_file_length=Long.parseLong(st.nextToken());
                        System.out.println("length"+upload_file_length);
                    }
                    else if(s.startsWith("upload_to"))
                    {
                        StringTokenizer st = new StringTokenizer(s);
                        st.nextToken();
                        upload_to=st.nextToken();
                        upload_to=upload_to.replaceAll("%20"," ");
                    }
            }
               
         StringTokenizer st=new StringTokenizer(firstline);
        
         method=st.nextToken();
         resource=st.nextToken();
         
         if(resource.equals("/upload"))
                 {
                   upload_to=upload_to.substring(12);
                   File f_upload = new File(upload_to+"\\"+upload_filename);          
                   DataInputStream dis1 = new DataInputStream(this.sock.getInputStream());
                   FileOutputStream foss = new FileOutputStream(f_upload);
                   byte b[] = new byte[10000000];
                   long count = 0;
                   long size = f_upload.length();
                   int r = 0;
                   while (true)
                   {
                      r = dis1.read(b, 0, 10000000);
                      foss.write(b, 0, r);
                      count = count + r;
                      if (size == count)
                      {
                         break;
                      }
                       System.out.println(count+"   file size--->"+upload_file_length);
                   }
                        foss.close();
                        dis1.close();
                }
           else
           {
             resource = resource.replaceAll("%20"," ");
             resource = resource.replaceAll("%7B","{");
             resource = resource.replaceAll("%7D","}");
             resource = resource.replaceAll("%5C","/");
             resource = resource.replaceAll("%5B","[");
             resource = resource.replaceAll("%5D","]");
          
         
         
         System.out.println("resource::: "+resource);
          
         f = new File(resource);
         
         if(resource.startsWith("/mycomputer")) //to check drive
         {
              if(resource.indexOf("/",2)==-1)  //drive case
              { 
                  String res2="";
                  String res ="<html>"
                                + "<head  >"
                                
                                + "<title>SkyNet</title>"
                                + "<style type=\"text/css\" >"              //css
                               
                                + "a:link{color:black;}"
                                + "a:visited{color:black;}"
                                + "a:hover{color:blue;}"
                                + "a:active{color:yellow;}"   
                         
                               
                                + "body{background-image: url('/images/2.jpg'); background-repeat:no repeat;}"
                                
                                + "#all{width: 1000;margin-left: auto;margin-right: auto;}"
                                + "#header{opacity:0.9;padding-top:20px;border:solid 2px gray;background-color: white;}"
                                
                                + "#main{opacity:0.9;border:solid 2px gray;padding-left: 50px; padding-right:20px; padding-bottom:44px; background-color:white;margin-top: 2px;min-height: 384px;}"
                                + "#footer{opacity:0.9;border:solid 2px gray;padding-top:10px;background-color: white; clear:both;margin-top:2px;  width:1000;text-align:right;} "
                                + "</style> "
                                + "</head>"
                                
                                + "<body >"
                            
                                + "<div id=\"all\">"
                                + "<div id=\"header\">"
                                            + "<center>"
                                            + "<table>"
                                                + "<tr>"
                                                    + "<td>"
                                                        + "<h2 style = \"text-align:center; color:black\" ><img src=\"/images/download.png\" height=\"80\" width=\"80\">PlayStore</h2>"
                                                    + "</td>"
                                                    
                                                + "</tr>"
                                            + "</table>"
                                            + "</center>"
                                + " </div> "
                                
                                + "<div id=\"main\">"
                                           
                                            + "<center>"
                                            + "<br><table >"
                                                + "<tr>"
                                                     + "<td>"
                                                        + "<h3 style = \"text-align:center;\" >Welcome to Unite PlayStore</h3>"
                                          
                                                     + "</td>"
                                                + "</tr>"
                                            + "</table>"
                                            + "</center><hr>"
                                        
                                        + "<br><br><br><table style=\"text-align:center\" >";
                                       
                     
                   File f2[]=File.listRoots();            // to show the disk drives
                   int len=f2.length;
           
                   for(int i=0;i<len;i++)
                     {
                       String s1=FileSystemView.getFileSystemView().getSystemTypeDescription(f2[i]);
                
                       if(s1.equals("Local Disk")) 
                         {
                           String s2 =FileSystemView.getFileSystemView().getSystemDisplayName(f2[i]);
                           if(i%3==0 || i==0)
                                 {
                                        res2 = res2+"<tr style=\"height: 137px;\">";
                                 }
                          
                              
                           res2 = res2+"<td width=\"875\" align=\"center\"><div style=\"background-color:white; width: 200px; \">"
                                      + "<a href=\"/mycomputer/"+f2[i].toString()+"\"><img src=\"/images/drive.png\" width=\"250\" height=\"100\"></a><br>"
                                      + "<a href=\"/mycomputer/"+f2[i].toString()+"\" style=\"text-decoration: none;\" >"+s2+"</a> </div></td> ";
                         
                         }
                     }
                   res=res + res2 +" </table>"
                                  + "</div>"            // main's div closed
                                  + "<div id=\"footer\">"
                                      + "<table align=\"center\">"
                                          + "<tr>"
                                              + "<td>"
                                                  + "<h4> <b><i>Developed By<br>Bhupinder Karan Singh</i></b></h4>"
                                              + "</td>"
                                          + "</tr>"
                                      + "</table>"
                                  + "</div>"           // footer's div closed here
                                 
                                  + "</div>"           // all's div closed 
                                  + "</body>"
                                  + "</html>";
                                         
            
             dos.writeBytes("HTTP/1.1 200 OK\r\n");
             dos.writeBytes("Content-Type: text/html\r\n");
             dos.writeBytes("Content-Length: "+res.length()+"\r\n");
             dos.writeBytes("\r\n");
             dos.writeBytes(res+"\r\n");
             dos.flush();
             } 
              else
              {
                 File f9 = new File(resource);
                 String res="";
                 String res2="";
               
                  res ="<html>"
                               + "<head>"
                               + "<title>SkyNet</title>"
                               + "<style type=\"text/css\">"         // css
                          
                               + "a:link{color:black;}"
                               + "a:visited{color:black;}"
                               + "a:hover{color:blue;}"
                               + "a:active{color:yellow;}"
                          
                               + "body{background-image: url('/images/2.jpg');background-attachment:fixed;}"      //background image
                               + "#all{width: 1000;margin-left: auto;margin-right: auto;}"
                               + "#header{opacity:0.9;filter:alpha(opacity=100);padding-top:20px;border:solid 2px gray;background-color: white;}"
                               
                               + "#main{opacity:0.9;border:solid 2px gray;padding-left: 60px;padding-top: 20px; padding-right:60px; background-color: white;padding-bottom:33px;margin-top: 2px; min-height: 384px;}"
                               + "#footer{opacity:0.9;border:solid 2px gray;padding-top:10px;background-color: white; clear:both;margin-top:2px; } "
                               + "</style>"
                               + "</head>"
                               
                               + "<body>"
                               + "<div id=\"all\">"
                               + "<div id=\"header\">"
                                           + "<center>"
                                               + "<table>"
                                                   + "<tr>"
                                                       + "<td>"
                                                           + "<h2 style = \"text-align:center; color:black\" ><img src=\"/images/download.png\" height=\"80\" width=\"80\">PlayStore</h2>"
                                                       + "</td>"
                                                   + "</tr>"
                                               + "</table>"
                                           + "</center> "
                               + "</div> "
                               
                         
                               + "<div id=\"main\" >"
                                            + "<table>"
                                                + "<tr>"
                                                    + "<td width=\"950\" >"
                                                    
                                                    + "<a href=\"http://127.0.0.1:8200/mycomputer\" style = \"text-align:left;\" ><img src=\"/images/home.png\" height=\"40\" width=\"80\"></a>"
                                                    
                                                     
                                                    + "</td>"
                                                    + "<td>"
                                                        + "<a href = \""+f9.getParent()+"\"   style=\"text-decoration: none;\" >"
                                                        + "<h3 style = \"text-align:right;\" ><img src=\"/images/back1.png\" width=\"50\" height=\"50\"></a>"  //back button as image
                                                    + "</td>"
                                                + "</tr>"
                                              +"<marquee behavior=\"alternate\" direction=\"left\" scrollamount=\"4\" bgcolor=white><font style=\"color:#355692;\">Use Winrar to Extract the Downloaded Files</font></marquee>"
                                         +"</table><hr>";
                  
                      
                          
 
                File f=new File(resource.substring(12));      
                File f2[]=f.listFiles();
                int len = f2.length;
                for(int i=0;i<len;i++)
                { 
                 if(f2[i].isHidden())
                  {
                  continue; 
                  }
                   else if(f2[i].isFile())
                  {
                         
                      if(f2[i].toString().endsWith(".jpg"))
                        {
                           res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/jpg.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                        }
                  else  if(f2[i].toString().endsWith(".jpeg"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/jpg.png\" width=\"40\" height=\"40\">  <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else if(f2[i].toString().endsWith(".mp3"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/mp3.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" target=\"_blank\">"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else if(f2[i].toString().endsWith(".mp4"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/mp4.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else if(f2[i].toString().endsWith(".avi"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/avi.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else  if(f2[i].toString().endsWith(".mkv"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/mkv.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else  if(f2[i].toString().endsWith(".html"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/html.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else if(f2[i].toString().endsWith(".txt"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/text.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  
                  
                   else  if(f2[i].toString().endsWith(".pdf"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/pdf.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else if(f2[i].toString().endsWith(".exe"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/exe.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  
                  else   if(f2[i].toString().endsWith(".png"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/jpg.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                 
                  else     if(f2[i].toString().endsWith(".doc"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/doc.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else   if(f2[i].toString().endsWith(".docx"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/docx.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  
                 
                  else   if(f2[i].toString().endsWith(".wav"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/mp3.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else   if(f2[i].toString().endsWith(".img"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/iso.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  
                  else   if(f2[i].toString().endsWith(".rar"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/rar.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else   if(f2[i].toString().endsWith(".zip"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/rar.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else     if(f2[i].toString().endsWith(".flv"))
                    {
                       res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/flv.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  else
                    {
                     res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/default.png\" width=\"40\" height=\"40\"> <a href=\"/images/piccc/"+f2[i].toString()+"\" target=\"_blank\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                    }
                  
                  }    
                  else if(f2[i].isDirectory())
                  {
                   res2 = res2+"<tr><td width=\"800\"><br><img src=\"/images/folder2.png\" width=\"40\" height=\"40\"> <a href=\"/mycomputer/"+f2[i].toString()+"\"  style=\"text-decoration: none;\" > "+f2[i].getName().toString()+"</a><br><br></td></tr>";
                  }
                  
                 else
                      
                  {
                      res2 = res2+"<tr>  <td> <br> <a href=\"/mycomputer/"+f2[i].toString()+"\" style=\"text-decoration: none;\" >"+f2[i].getName().toString()+"</a><br><br></td></tr>";
                   } 
                }
               
                res= res + res2+" </table>"
                                  + "</div> "   //main's div closed here
                        
                                  + "<div id=\"footer\">"
                                      + "<table>"
                                          + "<tr>"
                                              + "<td>"
                                                  + "<img src = \"/images/logo.png\"width=\"60\" height=\"60\">"
                                              + "</td>"
                                              + "<td  width=\"700\">"
                                                  + "<h4><b><i>Beant College Of Engineering And Technology,Gurdaspur.<br>"
                                                  + "Established and Promoted by Govt. of Punjab-143521</i></b></h4>"
                                              + "</td> "
                                              + "<td>"
                                                  + "<h4> <b><i>Developed By:<br>Bhupinder Karan Singh<br>Branch:-Comp.Sci.& Engg. </h4></i></b>"
                                              + "</td>"
                                          + "</tr> "
                                 + " </div>"            //footer's div closed here
                        + "</div>"                   //all's div closed here
                       
                        + "</body>"
                        + "</html>";
              
             dos.writeBytes("HTTP/1.1 200 OK\r\n");
             dos.writeBytes("Content-Type: text/html\r\n");
             dos.writeBytes("Content-Length: "+res.length()+"\r\n");
             dos.writeBytes("\r\n");
             dos.writeBytes(res+"\r\n");
             dos.flush();  
            }     
           
          }
         
        
         else if(resource.indexOf("/images")!=-1)
         {
            File f_im;
            String name="";
            
            int p = resource.indexOf("/images");
            String res_path=resource.substring(p);
            
            if(res_path.indexOf("piccc")!=-1)
            {
               f_im=new File(resource.substring(14));
               name=f_im.getPath();
              // System.out.println(":::::::::::::::::::"+f_im);
            }

            else
            {
              f_im=new File("."+resource);
              name="./images/"+f_im.getName();
            } 
         
           
           if(f_im.exists())
           {
              dos.writeBytes("HTTP/1.1 200 OK\r\n");
              dos.writeBytes("Content-Length: "+f_im.length()+"\r\n");
              dos.writeBytes("Content-Type: "+mime_types.gettype(f_im.getName())+"\r\n");
              dos.writeBytes("\r\n"); 
             
              FileInputStream fis= new FileInputStream(name);
              int r=0;
              long count=0;
              byte b[]=new byte[10000000];
             
              while(true)
              {
                r=fis.read(b,0,10000000);  
                dos.write(b,0,r);
                count=count+r;
                if(count==f_im.length())
                {
                  break;
                }
              }   
              dos.flush();
              dos.close();
              fis.close();
                 
           }
         
         }
          else
          {
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            long size = f.length();
            if(f.exists())
            {
              dos.writeBytes("Content-Length: "+f.length()+"\r\n");
              dos.writeBytes("Content-Type: "+mime_types.gettype(f.getName())+"\r\n");
              dos.writeBytes("\r\n"); 
              FileInputStream fis= new FileInputStream(f);
              int r=0;
              long count=0;
              byte b[]=new byte[10000000];
              while(true)
              {
                r=fis.read(b,0,10000000);  
                dos.write(b,0,r);
                count=count+r;
                if(count==size)
                {
                  break;
                }
              }
            }
            else
            {
                dos.writeBytes("Content-Length: "+f.length()+"r\n");
                dos.writeBytes("Content-Type: text/html\r\n");
                dos.writeBytes("\r\n");
                dos.writeBytes("<html>\r\n");
                dos.writeBytes("<head>\r\n");
                dos.writeBytes("<title></title>\r\n");
                dos.writeBytes("</head>\r\n");
                dos.writeBytes("<body>\r\n");
                dos.writeBytes("<h1> Error : File Not Found </h1>\r\n");
                dos.writeBytes("</body>\r\n");
                dos.writeBytes("</html>\r\n");
            }
          }
          dos.close();
         }
        }
        catch (IOException ex) 
        {
           ex.printStackTrace();
        }
      }
  }
   
      
      
    public static void main(String args[]) throws IOException 
    {
        assign27 obj=new assign27();
       
    }
}

