class Node {
    int info;
    Node esquerda;
    Node direita;

    public Node(int info, Node esquerda, Node direita) {
        this.info = info;
        this.esquerda = esquerda;
        this.direita = direita;
    }
}

class ArvoreBinaria {
    Node raiz;

    // Inserir elemento
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node atual, int valor) {
        if (atual == null) {
            return new Node(valor, null, null);
        }
        if (valor < atual.info) {
            atual.esquerda = inserirRec(atual.esquerda, valor);
        } else if (valor > atual.info) {
            atual.direita = inserirRec(atual.direita, valor);
        }
        // Se valor igual, não insere (não permite duplicatas)
        return atual;
    }

    // Remover o maior elemento
    public void removerMaior() {
        raiz = removerMaiorRec(raiz);
    }

    private Node removerMaiorRec(Node atual) {
        if (atual == null) return null;
        if (atual.direita == null) {
            return atual.esquerda;
        }
        atual.direita = removerMaiorRec(atual.direita);
        return atual;
    }

    // Remover o menor elemento
    public void removerMenor() {
        raiz = removerMenorRec(raiz);
    }

    private Node removerMenorRec(Node atual) {
        if (atual == null) return null;
        if (atual.esquerda == null) {
            return atual.direita;
        }
        atual.esquerda = removerMenorRec(atual.esquerda);
        return atual;
    }

    // Remover um elemento específico N
    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private Node removerRec(Node atual, int valor) {
        if (atual == null) return null;

        if (valor < atual.info) {
            atual.esquerda = removerRec(atual.esquerda, valor);
        } else if (valor > atual.info) {
            atual.direita = removerRec(atual.direita, valor);
        } else {
            // Encontrou o nó a ser removido
            if (atual.esquerda == null) return atual.direita;
            if (atual.direita == null) return atual.esquerda;

            // Dois filhos: substitui pelo menor da subárvore direita
            Node sucessor = encontrarMenor(atual.direita);
            atual.info = sucessor.info;
            atual.direita = removerRec(atual.direita, sucessor.info);
        }
        return atual;
    }

    private Node encontrarMenor(Node atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    // Opcional: imprimir em ordem para ver a árvore
    public void imprimirEmOrdem() {
        imprimirEmOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirEmOrdemRec(Node atual) {
        if (atual != null) {
            imprimirEmOrdemRec(atual.esquerda);
            System.out.print(atual.info + " ");
            imprimirEmOrdemRec(atual.direita);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        int[] valores = {14, 15, 4, 9, 7, 18, 3, 5, 16, 20, 17, 5};

        for (int v : valores) {
            arvore.inserir(v);
        }

        System.out.print("Árvore original: ");
        arvore.imprimirEmOrdem();

        arvore.removerMaior();
        System.out.print("Após remover maior: ");
        arvore.imprimirEmOrdem();

        arvore.removerMenor();
        System.out.print("Após remover menor: ");
        arvore.imprimirEmOrdem();

        arvore.remover(9);
        System.out.print("Após remover 9: ");
        arvore.imprimirEmOrdem();
    }
}