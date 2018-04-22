package model;

public class LzwTree
{
    private int currentIndex;
    private int size = 0;
    private int maxSize;
    private LzwNode root;

    public LzwTree(int maxSize)
    {
        currentIndex = 0;
        this.maxSize = (maxSize > 256) ? maxSize : 256;
        root = new LzwNode(-1, -1, null);
        initializeSymbols();
    }

    public LzwTree()
    {
        this(10000);
    }

    /**
     * Inicializa o dicionário com o alfabeto de 256 bytes (codificação Ascii)
     */
    private void initializeSymbols(){
        for (int i = 0; i < 256; i++){
            root.addChild(i, i);
            currentIndex++;
        }

        this.size = 256;
    }

    /**
     * Função que retorna o nó filho da raiz que possui o símbolo lido
     * @param symbol
     * @return
     */
    public LzwNode getNodeBySymbol(int symbol)
    {
        for (LzwNode node : root.getChildren()){
            if(node.getSymbol() == symbol) //Busca em largura
                return node;
        }

        return root.getNodeBySymbol(symbol); //Busca em profundidade
    }

    /**
     * Função que retorna o nó filho da raiz que possui o indice lido
     * @param index
     * @return
     */
    public LzwNode getNodeByIndex(int index)
    {
        for (LzwNode node : root.getChildren()){
            if(node.getIndex() == index) //Busca em largura
                return node;
        }

        return root.getNodeByIndex(index); //Busca em profundidade
    }

    /**
     * Adiciona um novo simbolo.
     * @param parent, index
     */
    public void addSymbol(LzwNode parent, int index)
    {
        if(maxSize <= currentIndex) return;
        LzwNode node = getNodeByIndex(index);

        if(node == null)  parent.addChild(currentIndex++, parent.getFirstSymbol());
        else parent.addChild(currentIndex++, node.getFirstSymbol());
        size++;
    }

    /**
     * Retorna o tamanho atual do dicionário.
     * @return
     */

    public int getSize()
    {
        return this.size;
    }
}
