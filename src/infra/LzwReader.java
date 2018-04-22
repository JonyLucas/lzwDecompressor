package infra;

import java.io.*;

public class LzwReader
{

    private DataInputStream reader;

    public LzwReader(String filePath)
    {
        try {
            reader = new DataInputStream(new FileInputStream(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LzwReader()
    {
        this("file");
    }

    /**
     * Extrai o tamanho máximo do dicionário.
     *
     * @return
     */
    public int getDictionarySize() throws Exception {
        String header = "";
        try {

            int current = reader.readByte();
            while (current != 10) {
                header += (char) current;
                current = reader.readByte();
            }

            return Integer.parseInt(header);
        } catch (IOException e) {
            close();
            e.printStackTrace();
            throw new Exception("Falha na leitura de arquivo");
        }
    }

    /**
     * Retorna os próximos nBytes do arquivo.
     *
     * @param nBytes
     * @return
     */
    public int[] nextBytes(int nBytes) throws Exception {

        int i = 0;
        int[] buffer = new int[nBytes];

        try {

            for(; i < nBytes; i++) buffer[i] = reader.readUnsignedByte();

        } catch (EOFException eof){
            close();
            for(; i < nBytes; i++) buffer[i] = -1;
        }
        catch (IOException e) {
            close();
            e.printStackTrace();
            throw new Exception("Falha na leitura de arquivo");
        }

        return buffer;
    }

    private void close()
    {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
