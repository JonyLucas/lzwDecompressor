package infra;

import java.io.*;
import java.util.ArrayList;

public class LzwWriter
{

    private DataOutputStream writer;

    public LzwWriter(String filePath)
    {
        try {
            writer = new DataOutputStream(new FileOutputStream(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LzwWriter()
    {
        this("file");
    }

    /**
     * Escreve os caractéres no arqivo
     *
     * @param chars
     * @return
     */
    public void write(ArrayList<Integer> chars) throws Exception {

        try {


            for(Integer c: chars)
            {
                //System.out.println("Byte: " + c);
                writer.write(c);
            }

        } catch (EOFException eof){
            close();
        }
        catch (IOException e) {
            close();
            e.printStackTrace();
            throw new Exception("Falha na escrita no arquivo");
        }

    }

    private void close()
    {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
