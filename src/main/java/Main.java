import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args)  {
        String s = "The construction &copy; is a copyright symbol,but the construction &cru is not" +
                "but design &copy - no\n";
        byte[] buf = s.getBytes();
        ByteArrayInputStream inputStream  = new ByteArrayInputStream(buf);
        int c;
        boolean marked = false;

        Files.mismatch()
                Reader    
        try(BufferedInputStream f = new BufferedInputStream(inputStream)) {
            while ((c = f.read()) != -1){
                switch (c){
                    case '&':
                        if(!marked){
                            f.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked){
                            marked = false;
                            System.out.println("<");
                        } else {
                            System.out.println((char) c);
                        }
                        break;
                    case ' ':
                        if (marked){
                            marked = false;
                            f.reset();
                            System.out.println("&");
                        } else
                            System.out.println((char) c);
                        break;
                    default:
                        if(!marked)
                            System.out.println((char) c);
                        break;
                }
            }
        } catch (IOException e){

        }
    }
}