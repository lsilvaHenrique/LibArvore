package sp.fateczl.arvorechar;

public class Arvore {
    No raiz;

    public Arvore() {
        this.raiz = null;
    }

    private void insertLeaf(final No no, final No raizSubArvore) {
        if (this.raiz == null) {
            this.raiz = no;
        } else if (no.dado < raizSubArvore.dado) {
            if (raizSubArvore.esquerda == null) {
                raizSubArvore.esquerda = no;
            } else {
                this.insertLeaf(no, raizSubArvore.esquerda);
            }
        } else if (no.dado >= raizSubArvore.dado) {
            if (raizSubArvore.direita == null) {
                raizSubArvore.direita = no;
            } else {
                this.insertLeaf(no, raizSubArvore.direita);
            }
        }
    }

    public void insert(final char dado) {
        final No no = new No();
        no.dado = dado;
        no.esquerda = null;
        no.direita = null;
        this.insertLeaf(no, this.raiz);
    }

    private No nodeSearch(final No raizSubArvore, final char valor) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        if (raizSubArvore.dado > valor) {
            return this.nodeSearch(raizSubArvore.esquerda, valor);
        }
        if (raizSubArvore.dado < valor) {
            return this.nodeSearch(raizSubArvore.direita, valor);
        }
        return raizSubArvore;
    }

    private int nodeLevel(final No raizSubArvore, final char valor) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        if (raizSubArvore.dado > valor) {
            return 1 + this.nodeLevel(raizSubArvore.esquerda, valor);
        }
        if (raizSubArvore.dado < valor) {
            return 1 + this.nodeLevel(raizSubArvore.direita, valor);
        }
        return 0;
    }

    public void search(final char valor) throws Exception {
        try {
            final No no = this.nodeSearch(this.raiz, valor);
            final int level = this.nodeLevel(this.raiz, valor);
            System.out.println("Valor " + no.dado + " nível " + level);
        } catch (Exception e) {
            throw new Exception("Valor não encontrado");
        }
    }

    private No removeChild(No raizSubArvore, final char valor) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        if (raizSubArvore.dado > valor) {
            raizSubArvore.esquerda = this.removeChild(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado < valor) {
            raizSubArvore.direita = this.removeChild(raizSubArvore.direita, valor);
        } else if (raizSubArvore.esquerda == null && raizSubArvore.direita == null) {
            raizSubArvore = null;
        } else if (raizSubArvore.esquerda == null) {
            raizSubArvore = raizSubArvore.direita;
        } else if (raizSubArvore.direita == null) {
            raizSubArvore = raizSubArvore.esquerda;
        } else {
            No no;
            for (no = raizSubArvore.esquerda; no.direita != null; no = no.direita) {}
            raizSubArvore.dado = no.dado;
            no.dado = valor;
            raizSubArvore.esquerda = this.removeChild(raizSubArvore.esquerda, valor);
        }
        return raizSubArvore;
    }

    public void remove(final char valor) throws Exception {
        try {
            this.removeChild(this.raiz, valor);
        } catch (Exception e) {
            throw new Exception("Valor não existente");
        }
    }

    private void prefix(final No raizSubArvore) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        System.out.print(raizSubArvore.dado + " ");
        if (raizSubArvore.esquerda != null) {
            this.prefix(raizSubArvore.esquerda);
        }
        if (raizSubArvore.direita != null) {
            this.prefix(raizSubArvore.direita);
        }
    }

    private void infix(final No raizSubArvore) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        if (raizSubArvore.esquerda != null) {
            this.infix(raizSubArvore.esquerda);
        }
        System.out.print(raizSubArvore.dado + " ");
        if (raizSubArvore.direita != null) {
            this.infix(raizSubArvore.direita);
        }
    }

    private void postfix(final No raizSubArvore) throws Exception {
        if (this.raiz == null) {
            throw new Exception("Árvore vazia");
        }
        if (raizSubArvore.esquerda != null) {
            this.postfix(raizSubArvore.esquerda);
        }
        if (raizSubArvore.direita != null) {
            this.postfix(raizSubArvore.direita);
        }
        System.out.print(raizSubArvore.dado + " ");
    }

    public void prefixSearch() throws Exception {
        this.prefix(this.raiz);
    }

    public void infixSearch() throws Exception {
        this.infix(this.raiz);
    }

    public void postfixSearch() throws Exception {
        this.postfix(this.raiz);
    }
}

