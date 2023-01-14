import java.util.ArrayList;

import static java.lang.System.exit;

public class Simplex {
    public float [][] tablo;
    public int posicaoMenorVertical;
    public ArrayList<String> variaveisNBasicas = new ArrayList<>();

    public Simplex(float [][]tablo){
        this.tablo = tablo;

    }

    public float retornaMenorValor(){
        float menorFObj = 100;
        for(int i = 0; i < tablo[0].length - 1; i++){
            if (tablo[0][i] < menorFObj && tablo[0][i] < 0){
                menorFObj = tablo[0][i];
            }
        }
        return menorFObj;
    }

    public int retornaPosicaoHorizontal(){
        float menorValor = retornaMenorValor();
        int posicaoHorizontal = 0;
        for (int i = 0; i < tablo[0].length; i++){
            if (tablo[0][i] == menorValor && i > 0){
                posicaoHorizontal = i;
            }
        }
        System.out.println("Menor valor" + menorValor);
        System.out.println("Posição horizontal: " + posicaoHorizontal);
        return posicaoHorizontal;
    }

    public float retornaMenorDivisao(){
        float resultado= 0;
        float menorDivisao = 10;
        int posicaoHorizontal = retornaPosicaoHorizontal();
        for (int i = 1; i < tablo.length; i++){
            if (tablo[i][posicaoHorizontal] > 0 && tablo[i][tablo[0].length - 1] > 0){
                System.out.println("Divisão: " + tablo[i][tablo[0].length - 1] + " / " +  tablo [i][posicaoHorizontal]);
                System.out.println("TABLO[" + i + "]" + "[" + posicaoHorizontal + "]");
                resultado = tablo[i][tablo[0].length - 1] / tablo [i][posicaoHorizontal];
                if (resultado < menorDivisao && tablo[i][tablo[0].length - 1] > 0 && tablo[i][posicaoHorizontal] > 0){
                    menorDivisao = resultado;
                    posicaoMenorVertical = i;
                }

            }
            }


        System.out.println("Posição vertical: " + posicaoMenorVertical);
        System.out.println("Menor divisão = " + menorDivisao);
        variaveisNBasicas.add("x" + posicaoHorizontal);
        System.out.println(variaveisNBasicas);

        return menorDivisao;
    }
    public void dividePivo(){
        float []dividendos = preencheVetorDividendo();
        int contadorNulosNegativos = 0;
        for (int i = 0; i < dividendos.length;i++){
            if(dividendos[i] <= 0){
                contadorNulosNegativos = contadorNulosNegativos + 1;
            }
        }

        if(contadorNulosNegativos == dividendos.length){
            imprimeMatriz();
            System.out.print("Valor dos dividendos : ");
            for (int i = 0; i < tablo.length; i++){
                System.out.print(dividendos[i] + " ");
            }
            System.out.println(" ");
            System.out.println("Problema com solução ilimitada, devido todas as variáveis possuirem coeficientes não possitivos na coluna pivô.");
            exit(0);
        } else {
            int menorPosicaoHorizontal = retornaPosicaoHorizontal();
            float pivo = tablo[posicaoMenorVertical][menorPosicaoHorizontal];
            if (pivo != 1){
                if (pivo > 0){
                    System.out.println("VALOR DO PIVO: " + pivo);
                    for (int i = 0; i < tablo[0].length; i++){
                        tablo[posicaoMenorVertical][i] = tablo[posicaoMenorVertical][i]/pivo;
                    }
                }
            }
        }


    }

    public float[] preencheVetorDividendo (){
        float []valorDaColuna = new float[tablo.length];

        for (int i = 0; i < tablo.length; i++){
            valorDaColuna[i] = tablo[i][posicaoMenorVertical];
        }
        return valorDaColuna;
    }

    public void escalonaMatriz (){
        retornaMenorDivisao();
        int posicaoHorizontal = retornaPosicaoHorizontal();

        if(tablo[posicaoMenorVertical][posicaoHorizontal] != 1){
            dividePivo();
        }
        for (int i = 0; i < tablo.length; i++){
            if (i != posicaoMenorVertical){
                float multiplicador = tablo[i][posicaoHorizontal];
                System.out.println("Linha L" + i);
                System.out.println("Valor do multiplicador " + multiplicador);

                for (int j = 1; j < tablo[0].length; j++){
                    if (multiplicador == 0){
                        tablo[i][j] = tablo[i][j];
                    } else {
                        System.out.println(tablo[i][j] +" = " +  tablo[i][j] + " - " + " ( " + multiplicador + "*" + tablo[posicaoMenorVertical][j] + ")");
                        tablo[i][j] = tablo[i][j] - (multiplicador * tablo[posicaoMenorVertical][j]);
                    }

                }
            }


        }

        imprimeMatriz();

    }


    public void calculaSimplex(){
        int contador = 0;
        for (int i = 0; i < tablo[0].length; i++){
            if (tablo[0][i] < 0){

                System.out.println("VALOR NEGATIVO A SER TRABALHADO : " + tablo[0][i]);

                escalonaMatriz();

                contador ++;
                variaveisSolucaoOtima(contador);
                System.out.println("x" + contador + " entrou na base");
                System.out.println("Valor do contador " + contador);
                System.out.println("Ainda não está na solução ótima");
                System.out.println("____________________________________________________________________________");
            }

        }
        System.out.println("Variaveis não basicas na base: " + variaveisNBasicas);
        System.out.print("Variaveis Não Basicas: ");
        int quantidadeVariaveisNaoBasicas = qntVariaveisNaoBasicas();
        int aux = 1;
        for (int i = 1; i <= quantidadeVariaveisNaoBasicas; i++){
            System.out.print(" x" + i );
            aux++;
        }
        boolean algumNegativo = false;
        for (int i =0; i < tablo[0].length; i++){
            if (tablo[0][i] < 0){
                algumNegativo = true;
            }
        }

        if (algumNegativo){
            calculaSimplex();
        }


        System.out.println(" ");
        System.out.println("Valor do contador " + contador);
        imprimeMatriz();
        System.out.println("x" + contador + " entrou na base");
        System.out.println("Problema resolvido");
        preencheVetorVariaveis();
    }

    public void preencheVetorVariaveis(){
        float []variaveisFuncaoObj = new float[tablo[0].length];

        for(int i = 0; i < tablo[0].length; i++){
            variaveisFuncaoObj[i] = tablo[0][i];
        }
        System.out.println("Variaveis da Função objetivo:");

        for (int i = 0; i < tablo[0].length; i++){
            System.out.print(variaveisFuncaoObj[i] + "   ");
        }
    }

    public float[] variaveisSolucaoOtima(int contador){
        float []variaveisSO = new float[tablo[0].length];
        variaveisSO[contador] = tablo[0][contador];
        ++contador;
        return variaveisSO;

    }


    public ArrayList<String> vetorVariaveisNaoBasicasNaBase(int contador) {
        ArrayList<String> variaveisNaoBasicasNaBase = new ArrayList<String>();
        return variaveisNaoBasicasNaBase;
    }

    public int qntVariaveisNaoBasicas (){
        int qntVariaveisNaoBasicas = tablo[0].length - tablo.length - 1;

        return qntVariaveisNaoBasicas;
    }

    public void imprimeMatriz(){
        for (int i = 0; i < tablo.length; i++){
            for (int j = 0; j < tablo[0].length; j++){
                System.out.print(tablo[i][j] + " | ");
            }
            System.out.println(" ");
        }

    }

}
