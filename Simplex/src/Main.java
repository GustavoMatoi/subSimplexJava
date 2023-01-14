public class Main {
    public static void main(String[] args) {
        float [][]tablo  = new float[][] {
                //Não existe solução ótima
                //{1, -1, -1, 0, 0, 0},
                //{0, -5, -4, 1, 0, -40},
                //{0, 2, 1, 0, 1, 6}

                //Solução ilimitada
//                {1,-4,-3,0,0,0},
//                {0,-2,-5,1,0,-20},
//                {0,1,0,0,1,8}
//
//                {1,-4,-3,0,0,0},
//                {0,-2,0,1,0,-16},
//                {0,1,0,0,1,8}

                {1,-50,-92,0,0,0},
                {0,2,4,1,0,10},
                {0,90,180,0,1,240}

//                {1,-8,-4,0,0,0},
//                {0,4,2,1,0,16},
//                {0,1,1,0,1,6}

//                //Solução degenerada
//                {1,-9,-9,0,0,0},
//                {0,1,4,1,0,8},
//                {0,1,2,0,1,4}

        };



        Simplex implementacaoSimplex = new Simplex(tablo);
        implementacaoSimplex.imprimeMatriz();
        implementacaoSimplex.calculaSimplex();
    }
}
