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

    private Node inserirRaiz(Node novo,int valor){
        if (novo == null){
            System.out.println("Node "+valor+" criado");
            return new Node(valor, null,null);
        }
        if (valor < novo.info) {
            System.out.println("Indo para a Esquerda");
            novo.esquerda = inserirRaiz(novo.esquerda, valor);
        } else if (valor > novo.info) {
            System.out.println("Indo para a Direita");
            novo.direita = inserirRaiz(novo.direita, valor);
        }
        return novo;
    }

    public void inserir(int valor) {
        raiz = inserirRaiz(raiz, valor);
    }

    private Node removerRaizMaior(Node removedor){
        if (removedor == null) {
            System.out.println("Sem Node criado");
            return null;
        }
        if(removedor.direita == null){
            return  removedor.esquerda;
        }
        removedor.direita = removerRaizMaior(removedor.direita);
        return removedor;
    }

    public void removerMaior(){
        System.out.println("Remomendo o Maior elemento");
        raiz = removerRaizMaior(raiz);
    }

    private Node removerRaizMenor(Node removedor){
        if (removedor == null){
            System.out.println("Sem Node criado");
            return null;
        }
        if (removedor.esquerda == null){
            return removedor.direita;
        }
        removedor.esquerda = removerRaizMenor(removedor.esquerda);
        return removedor;
    }

    public void removerMenor(){
        System.out.println("Removendo o menor Elemento");
        raiz = removerRaizMenor(raiz);
    }

    private Node descobrirMenor(Node escolhido){
        while (escolhido.esquerda != null){
            escolhido = escolhido.esquerda;
        }
        return escolhido;
    }

    private Node removerElemento(Node escolhido, int valor){
        if (escolhido == null) {
            System.out.println("Sem Node criado");
            return null;
        }
        if (valor < escolhido.info){
            escolhido.esquerda = removerElemento(escolhido.esquerda,valor);}
        if (valor > escolhido.info){
            escolhido.direita = removerElemento(escolhido.direita, valor);}
        else {
            if (escolhido.esquerda == null)
                return  escolhido.direita;
            if (escolhido.direita == null)
                return  escolhido.esquerda;

            // substituie os filhos??
            Node filho = descobrirMenor(escolhido.direita);
            escolhido.info = filho.info;
            escolhido.direita = removerElemento(escolhido.direita, filho.info);
        }
        return escolhido;
    }

    public void imprimirOrdem(){
        descobrirOrdem(raiz);
        System.out.println();
    }

    private void descobrirOrdem(Node ordem){
        if (ordem != null){
            descobrirOrdem(ordem.esquerda);
            System.out.print(ordem.info + " ");
            descobrirOrdem(ordem.direita);
        }
    }

    public void remover(int elemento){
        System.out.println("Removendo o elemento "+elemento);
        raiz = removerElemento(raiz,elemento);
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        int[] valores = {13,15,9,4,3,7,18,20,5,17};
        for (int i : valores){
            arvore.inserir(i);
            System.out.println("Adicionando na arvore elemento:"+i);
        }
        System.out.print("Imprimindo arvore Original: ");
        arvore.imprimirOrdem();

        arvore.removerMaior();
        System.out.print("Imprimindo Arvore apos remover o Maior: ");
        arvore.imprimirOrdem();

        arvore.removerMenor();
        System.out.print("Imprimindo Arvore apos remoover o Menor");
        arvore.imprimirOrdem();

        arvore.remover(9);
        arvore.remover(17);
        System.out.print("Apos remover os elementos: ");
        arvore.imprimirOrdem();

    }
}