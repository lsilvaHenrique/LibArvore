package sp.fateczl.arvorechar;

public class No
{
    public char dado;
    public No direita;
    public No esquerda;
    
    @Override
    public String toString() {
        return "No [dado=" + this.dado + "]";
    }
}