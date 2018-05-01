package controller;

import infra.LzwReader;
import infra.LzwWriter;
import model.LzwNode;
import model.LzwTree;

public class LzwDecompressor
{
    private LzwTree dictionary = null;
    private LzwNode currentNode = null;

    private LzwReader lzwReader;
    private LzwWriter lzwWriter;

    private int[] bufferReader;
    private static final int NBYTES = 2;
    private int nBits = 8;

    public LzwDecompressor(String fileInputPath, String fileOutputPath)
    {
        lzwReader = new LzwReader(fileInputPath);
        lzwWriter = new LzwWriter(fileOutputPath);
        bufferReader = new int[NBYTES];

    }

    /**
     * Inicia o algoritimo LZW para descomprimir.
     * É criado o dicionário inicial e, depois, é feito a decodificação.
     */
    public void run()
    {
        try {
            createDictionary(lzwReader.getDictionarySize(), true);
            readAndSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Função que cria o dicionário, caso overwrite seja true
     * A função irá criar um novo dicionário, descartando o
     * dicionário anterior.
     *
     * @param size
     * @param overwrite
     */
    private void createDictionary(int size, boolean overwrite){
        if(dictionary == null || overwrite){
            dictionary = new LzwTree(size);
        }
    }

    /**
     * Função que recolhe o símbolo lido na entrada e busca na árvore do dicionário,
     * Caso o símbolo lido não esteja presente no dicionário, acrescenta ao código
     * de saída o índice do nó atual e inicia a busca deste símbolo pela raiz da árvore.
     *
     * @return
     */
    private String readAndSearch() throws Exception
    {

        String code = "";
        Integer[] indexBR = new Integer[3];
        indexBR[0] = 7;
        indexBR[1] = 0;
        indexBR[2] = 0;


        bufferReader = lzwReader.nextBytes(NBYTES);
        if(bufferReader[0] == -1) return "";

        int index = getIndex(indexBR);
        if(Math.pow(2, nBits) <= dictionary.getSize()+1) nBits++;

        while(true)
        {
            searchAndAddSymbol(index);
            lzwWriter.write(currentNode.getCode());
            if(Math.pow(2, nBits) <= dictionary.getSize()+1) nBits++;

            index = getIndex(indexBR);
            if (indexBR[2] == 1){
                if(index != -1)lzwWriter.write(dictionary.getNodeByIndex(index).getCode());
                break;

            }
        }

        return code;
    }

    /**
     * Função que busca o indice a partir do nó atual (ultimo nó percorrido),
     * caso o nó atual seja null, inicia a busca pela raiz da árvore dicionário,
     * caso o nó atual possua um filho com o indice procurado, este passa a ser
     * o nó atual e é retornado true (indicando que o símbolo está presente no dicionário),
     * caso o nó atual não possua filhos com o indice procurado, é adicionado um
     * novo filho a este nó com o simbolo de acordo com o próximo indice e é retornado false.
     *
     * @param index
     * @return
     */
    private boolean searchAndAddSymbol(int index){

        if(currentNode == null) {
            currentNode = dictionary.getNodeByIndex(index);
            currentNode.getDescription();
            System.out.println(currentNode.getDescription());
            return true;
        }
        else {
            dictionary.addSymbol(currentNode, index);
            currentNode = dictionary.getNodeByIndex(index);
            currentNode.getDescription();
            System.out.println(currentNode.getDescription());
            return false;
        }

    }

    /**
     * Extrai o próximo indice do buffer a partir das variáveis indexBR que demarcam em que posição do buffer (indexBR) a leitura
     * irá começar/retornar.
     *
     * indexBR[0] indica a posição no buffer
     * indexBR[1] indica qual byte está lendo do buffer
     * indexBR[2] indica se a leitura terminou(1) ou não(0)
     * @param indexBR
     * @return
     */
    private int getIndex(Integer[] indexBR) throws Exception
    {
        int index = 0;

        if(bufferReader[indexBR[1]] == -1) return -1;
        for(int i = nBits-1; i >= 0; i--)
        {
            index += getBit(bufferReader[indexBR[1]], indexBR[0])*Math.pow(2, i);

            indexBR[0]--;
            if(indexBR[0] == -1)
            {
                indexBR[1]++;
                if(indexBR[1] == NBYTES)
                {
                    bufferReader = lzwReader.nextBytes(NBYTES);
                    indexBR[1] = 0;
                }

                if(bufferReader[indexBR[1]] == -1)
                {
                    indexBR[2] = 1;
                    if(i>0) index = -1;
                    break;
                }

                indexBR[0] = 7;
            }
        }

        return index;
    }

    /**
     * Extrai o bit k do inteiro n e retorna se o bit é 1 ou 0
     * @param n, k
     * @return
     */
    private int getBit(int n, int k) {
        return (n >> k) & 1;
    }
}
