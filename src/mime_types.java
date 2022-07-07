
public class mime_types
{
    public static String gettype(String filename)
    {
        if(filename.endsWith(".asx"))
        {
            return("video/x-ms-asf");
        }
        
        else if(filename.endsWith(".asp"))
        {
            return("text/asp");
        }
        
        else if(filename.endsWith(".avi"))
        {
            return("video/avi");
        }
        
        else if(filename.endsWith(".com"))
        {
            return("text/plain");
        }
        else if(filename.endsWith(".swf"))
        {
            return("application/x-shockwave-flash");
        }
        else if(filename.endsWith(".class"))
        {
            return("application/java-byte-code");
        }
        else if(filename.endsWith(".css"))
        {
            return("text/css");
        }
        else if(filename.endsWith(".doc"))
        {
            return("application/msword");
        }
        else if(filename.endsWith(".exe"))
        {
            return("application/octet-stream");
        }
        else if(filename.endsWith(".fif"))
        {
            return("image/fif");
        }
        else if(filename.endsWith(".html"))
        {
            return("text/html");
        }
        else if(filename.endsWith(".java"))
        {
            return("text/plain");
        }
        else if(filename.endsWith(".gif"))
        {
            return("image/gif");
        }
        else if(filename.endsWith(".mp3"))
        {
           return "audio/mpeg";
        }
        else if(filename.endsWith(".mpeg"))
        {
           return("video/mpeg");
        }
        else if(filename.endsWith(".pdf"))
        {
           return("application/pdf");
        }
        else if(filename.endsWith(".png"))
        {
           return("image/png");
        }
        else if(filename.endsWith(".ppt"))
        {
           return("application/mspowerpoint");
        }
        else if(filename.endsWith(".tar"))
        {
           return("application/x-tar");
        }
        else if(filename.endsWith(".txt"))
        {
           return("text/plain");
        } 
        else if(filename.endsWith(".voc"))
        {
           return("audio/voc");
        }  
        else if(filename.endsWith(".wav"))
        {
           return("audio/wav");
        } 
        else if(filename.endsWith(".zip"))
        {
           return ("application/x-compressed");
        } 
        
         else if(filename.endsWith(".ico"))
        {
           return ("image/x-icon");
        }
         
        else if(filename.endsWith(".mp4") || filename.endsWith(".MP4") || filename.endsWith(".Mp4"))
        {
          return "video/mp4"; 
        }
         else if(filename.endsWith(".png") || filename.endsWith(".PNG"))
      {
          return "image/png";
      }
      
        
         else if(filename.endsWith(".flv") || filename.endsWith(".FLV"))
      {
          return "video/x-flv";
      }
         else if(filename.endsWith(".gif"))
      {
          return "image/gif";
      }
           else if(filename.endsWith(".wmv"))
      {
          return "video/x-ms-wmv";
      }
        
        return null;
    }
}
