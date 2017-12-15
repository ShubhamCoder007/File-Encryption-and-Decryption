import java.io.*;
import java.util.Random;
class Encryption
{
    private static int filecount;
    private static BufferedReader br=null;
    private static PrintWriter p=null;
    private static String defaultpath="";
    
    static
    {
        try
        {
            File f=new File("default_path.txt");
            if(!f.exists())
            {
                System.out.println("Register default path for the program...");
                defaultpath=new BufferedReader(new InputStreamReader(System.in)).readLine();
                new PrintWriter(new FileWriter(f)).println(defaultpath);
            }
            else
            {
                defaultpath = new BufferedReader(new FileReader(f)).readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Some problem occured while loading the default path...");
            e.printStackTrace();
            System.out.println("Terminating...");
            System.exit(1);
            try
            {
                Thread.sleep(6000);
            }
            catch(InterruptedException t)
            {
                t.printStackTrace();
            }
        }
    }
    
    static
    {
        try{
            File f=new File(defaultpath+"filecount.txt");
            if(!f.exists()){
            f.createNewFile();
            p=new PrintWriter(new BufferedWriter(new FileWriter(f)));
            p.println("0");
            p.close();
            }
            br=new BufferedReader(new FileReader(f));
       }catch(IOException e){
           System.out.println("Was unable to read the file... tracing problem...");
           e.printStackTrace();
        }
        String s="1";
        try
        {
            do
            {
                filecount=Integer.parseInt(s);
            }
            while((s=br.readLine())!=null);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        try{
            p=new PrintWriter(new BufferedWriter(new FileWriter(defaultpath+"\\filecount.txt")));
            p.println(filecount+1);
            p.close();
        }catch(IOException e){
            System.out.println("Was unable to open writer stream... tracing problem...");
            e.printStackTrace();
        }
    }
    
    public static void encrypt(StringBuffer s)
    {
        Random r=new Random();
        PrintWriter p1=null;
        try
        {
            File f=new File(defaultpath+"\\Encryption_log"+filecount+".txt");
            File f1=new File(defaultpath+"\\EncryptedFile_log"+filecount+".txt");
        
            if(!f.exists())
                f.createNewFile();
            if(!f1.exists())
                f1.createNewFile();
        
            p1=new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            p=new PrintWriter(new BufferedWriter(new FileWriter(f)));
        }
        catch(IOException e)
        {
            System.out.println("Was unable to open or create the file... tracing problem...");
            e.printStackTrace();
        }
        
        while(s.length()>9)
        {
            int ind=s.indexOf("new_Line");
            String sb=s.substring(0,ind-1);
            String temp="";
            
            for(int j=0;j<sb.length();j++)
            {
                char c=s.charAt(j);
                int i=r.nextInt(255255); 
                int a=(int)c+i; 
                p.println(i+" ");
                temp+=(char)a;    
            }
            
            p1.println(temp);
            System.out.println(temp);
            s.delete(0,ind+9);
        }
        
        p.close();
        p1.close();
    }
    
    public static void main(String[] args)throws IOException
    {      
        br=new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb=new StringBuffer();
        String s="";
        
        System.out.println("Enter data to e encrypted...");
        while((s=br.readLine())!=null)
        {
            sb=sb.append(s);
            sb.append(" new_Line ");
        }
        //char[] c=new String(sb).toCharArray();
        encrypt(sb);
        System.out.println("Data encryption successfull...\nTerminating...");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e)
        {
            System.out.println("Main thread interrupted...");
            e.printStackTrace();
        }
        System.exit(1);
    }
}