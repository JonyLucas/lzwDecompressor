package model;

import java.util.ArrayList;

public class LzwNode
{
    private int index;
    private int symbol;
    private LzwNode parent;
    private ArrayList<LzwNode> children = new ArrayList<LzwNode>();

    public  LzwNode(int index, int symbol, LzwNode parent)
    {
        this.index = index;
        this.symbol = symbol;
        this.parent = parent;
    }

    public int getSymbol() { return symbol; }

    public String getCode()
    {
        String code = "";
        LzwNode node = this;

        code += (char) node.getSymbol();

        while(node.getParent().getSymbol() != -1)
        {
            node = node.getParent();
            code = (char) node.getSymbol() + code;
        }

        System.out.println("Codigo lido: " + code);
        return code;
    }

    public int getIndex(){ return index; }

    public ArrayList<LzwNode> getChildren() { return children; }

    /**
     * Realiza a busca do índice do símbolo passado como argumento, retornando o seu índice, caso exista,
     * caso contrário, retorna -1.
     *
     * @param searchIndex
     * @return
     */
    public int findIndexSymbol(int searchIndex)
    {
        if(this.index == searchIndex)
            return symbol;
        else{

            for (LzwNode node : children){
                int symbol = node.findIndexSymbol(searchIndex);
                if(symbol != -1)
                    return symbol;
            }

            return -1;
        }
    }

    /**
     * Realiza a busca do nó pelo índice e retorna-o, caso exista, caso contrário, retorna null.
     *
     * @param searchIndex
     * @return
     */
    public LzwNode getNodeByIndex(int searchIndex)
    {
        if(this.index == searchIndex)
            return this;
        else {
            for (LzwNode node : children){
                LzwNode indexedChild = node.getNodeByIndex(searchIndex);
                if(indexedChild != null)
                    return indexedChild;
            }

            return null;
        }
    }

    /**
     * Realiza a busca do nó pelo símbolo, retornando o nó correspondente, caso exista
     *
     * @param searchSymbol
     * @return
     */
    public LzwNode getNodeBySymbol(int searchSymbol)
    {
        if(this.symbol == searchSymbol)
            return this;
        else {
            for (LzwNode node : children){
                LzwNode symbolNode = node.getNodeBySymbol(searchSymbol);
                if(symbolNode != null)
                    return symbolNode;
            }

            return null;
        }
    }

    public void addChild(int index, int symbol)
    {
        children.add(new LzwNode(index, symbol, this));
    }

    public String getDescription()
    {

        String description = "Parent: Index[" + this.index + "] Symbol[" + this.symbol + "]\n";
        for (LzwNode node : children){
            description += "\tChild: Index[" + node.getIndex() + "] Symbol[" + node.getSymbol() + "]\n";
        }

        return description;

    }

    private LzwNode getParent() {
        return this.parent;
    }

    public int getFirstSymbol()
    {
        LzwNode node = this;

        while(node.getParent().getSymbol() != -1) node = node.getParent();

        return node.getSymbol();
    }
}
