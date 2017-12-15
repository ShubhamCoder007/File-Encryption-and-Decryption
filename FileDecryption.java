import java.io.*;
import java.util.*;
class Decryption
{
    private static BufferedReader file=null;
    private static BufferedReader log=null;
    private static String defaultpath="";
    
    static
    {
        if(defaultpath=="")
        {
        try
        {
            File f=new File("Default_path0.txt");
            if(!f.exists())
            {
                f.createNewFile();
                System.out.println("Register default path for the program...");
                defaultpath=new BufferedReader(new InputStreamReader(System.in)).readLine();
                PrintWriter p=new PrintWriter(new FileWriter(f));
                p.println(defaultpath);
                p.close();
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
}
    
    public static void channelFile(int log1)
    {
        try
        {
            log=new BufferedReader(new FileReader(defaultpath+"\\Encryption_log"+log1+".txt"));
            file=new BufferedReader(new FileReader(defaultpath+"\\EncryptedFile_log"+log1+".txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Some problems occured while channeling the file...\n"+e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
 
    public static void decrypt(String s, final int[] key)
    {
        String temp="";
        int j=0;
        for(int i:key)
        {
            int a=(int)s.charAt(j)-i;
            temp+=(char)a;
            j++;
        }
        System.out.println(temp);
    }
       
    public static void closeFile()throws IOException, NullPointerException
    {
        try
        {
            file.close();
            log.close();
        }catch(IOException | NullPointerException e)
        {
            System.out.println("There was some problem closing the file...\n"+e);
            System.out.println(e.getMessage());
            e.initCause(new FileNotFoundException("Could not Locate the file with the log..."));
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void main(String[] args)throws IOException
    {
        System.out.print("Enter the Encryption log number of the file to be decrypted : ");
        int log=Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        String s="";
        int mem=0;
       
        channelFile(log);
        
        if(Decryption.log!=null)
        System.out.println("Decrypting file please wait...\nLog number of the file:"+log+"\nReading from the file:\n");
        
        try
        {
            while((s=file.readLine())!=null)
            {
                int[] a=new int[s.length()];
                int lc=mem;
                String s1="";
                int rem=0;
    
                while((s1=Decryption.log.readLine())!=null)
                {
                    if(rem>=mem)
                    {
                        s1=s1.trim();
                        a[lc-mem]=Integer.parseInt(s1);
                        lc++;
                    }
                    rem++;
                    if(lc==(s.length()+mem))
                        break;
                }
                
                decrypt(s,a);
                mem=lc;
                s=null;
        }
    }catch(NullPointerException e)
    {
        System.out.println("The log number which you provided doesn't exist or the name might has changed");
        System.out.println("Could not find the log number");
        System.out.println("Report : "+e);
        System.out.println("Value : "+e.getMessage());
        Throwable t=e;
        while(t!=null)
        {
            System.out.println(t.getCause());
            t=t.getCause();
        }
        e.printStackTrace();
    }
        try
        {
            closeFile();
        }catch(NullPointerException np)
        {
            Throwable e=np;
            while(e!=null)
            {
                System.out.println(e.getCause());
                e=e.getCause();
            }
        }
        
        try
        {
            System.out.println("\n\n\nCreated by Shubham Banerjee");
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}