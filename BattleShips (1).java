//Welcome To Our Battleships Game
//Kelompok 5 ( Arnold Andhika Putra, Galang Maulana, Ainun Syahrani)
//Have Fun and Enjoy

import java.util.*;

public class BattleShips {
    public static int jumlahtabel = 8;
    public static int jumlahkolom = 8;
    public static int kapalamerika;
    public static int kapalchina;
    public static String[][] koordinat = new String[jumlahtabel][jumlahkolom];
    public static int[][] menebak = new int[jumlahtabel][jumlahkolom];

    public static void main(String[] args){
        System.out.println("**** Selamat Datang Di Game Battlefield ****");
        System.out.println("Sekarang kita akan memulai peperangan di laut\n");

        //Langkah 1 – Membuat Peta Di Laut
        membuatpetalaut();

        //Langkah 2 – Meletakkan Kapal Amerika
        meletakkankapalamerika();

        //Langkah 3 - Meletakkan Kapal China
        meletakkankapalchina();

        //Langkah 4 - Berperang
        do {
            Battle();
        }while(BattleShips.kapalamerika != 0 && BattleShips.kapalchina != 0);

        //Langkah 5 - Permainan Berakhir
        gameOver();
    }

    public static void membuatpetalaut(){
        //Bagian Pertama dari peta di laut
        System.out.print("  ");
        for(int i = 0; i < jumlahkolom; i++)
            System.out.print(i);
        System.out.println();

        //Bagian Tengah dari peta di laut
        for(int i = 0; i < koordinat.length; i++) {
            for (int j = 0; j < koordinat[i].length; j++) {
                koordinat[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + koordinat[i][j]);
                else if (j == koordinat[i].length - 1)
                    System.out.print(koordinat[i][j] + "|" + i);
                else
                    System.out.print(koordinat[i][j]);
            }
            System.out.println();
        }

        //Bagian Terakhir dari peta di laut
        System.out.print("  ");
        for(int i = 0; i < jumlahkolom; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void meletakkankapalamerika(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nLetakkan Kapal Amerika:");
        //Meletakkan 5 kapal amerika
        BattleShips.kapalamerika = 5;
        for (int i = 1; i <= BattleShips.kapalamerika; ) {
            System.out.print("Masukkan Koordinat X untuk  " + i + " kapal: ");
            int x = input.nextInt();
            System.out.print("Masukkan Koordinat Y untuk  " + i + " kapal: ");
            int y = input.nextInt();

            if((x >= 0 && x < jumlahtabel) && (y >= 0 && y < jumlahkolom) && (koordinat[x][y] == " "))
            {
                koordinat[x][y] =   "@";
                i++;
            }
            else if((x >= 0 && x < jumlahtabel) && (y >= 0 && y < jumlahkolom) && koordinat[x][y] == "@")
                System.out.println("Kamu tidak bisa meletakkan 2 kapal mu pada tempat yang sama");
            else if((x < 0 || x >= jumlahtabel) || (y < 0 || y >= jumlahkolom))
                System.out.println("Kamu tidak bisa meletakkan kapal mu di  " + jumlahtabel + " luar " + jumlahkolom + "koordinat");
        }
        printpetalaut();
    }

    public static void meletakkankapalchina(){
        System.out.println("\nChina meletakkan kapal");
        //Letakkan 5 kapal untuk china
        BattleShips.kapalchina = 5;
        for (int i = 1; i <= BattleShips.kapalchina; ) {
            int x = (int)(Math.random() * 8);
            int y = (int)(Math.random() * 8);

            if((x >= 0 && x < jumlahtabel) && (y >= 0 && y < jumlahkolom) && (koordinat[x][y] == " "))
            {
                koordinat[x][y] =   "x";
                System.out.println(i + ". kapal diletakkan");
                i++;
            }
        }
        printpetalaut();
    }

    public static void Battle(){
        giliranamerika();
        giliranchina();

        printpetalaut();

        System.out.println();
        System.out.println("Kapal mu: " + BattleShips.kapalamerika + " | Kapal China: " + BattleShips.kapalchina);
        System.out.println();
    }

    public static void giliranamerika(){
        System.out.println("\nGiliran Amerika");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan Koordinat X: ");
            x = input.nextInt();
            System.out.print("Masukkan Koordinat Y: ");
            y = input.nextInt();

            if ((x >= 0 && x < jumlahtabel) && (y >= 0 && y < jumlahkolom))
            {
                if (koordinat[x][y] == "x") //jika kapal komputer sudah ada disitu; komputer kehilangan kapal
                {
                    System.out.println("Boom! Kau menghancurkan kapalnya!");
                    koordinat[x][y] = "!"; //Tanda terkena
                    --BattleShips.kapalchina;
                }
                else if (koordinat[x][y] == "@") {
                    System.out.println("Oh tidak, kau menenggelamkan kapalmu sendiri :(");
                    koordinat[x][y] = "x";
                    --BattleShips.kapalamerika;
                }
                else if (koordinat[x][y] == " ") {
                    System.out.println("Maaf,tembakan mu salah");
                    koordinat[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= jumlahtabel) || (y < 0 || y >= jumlahkolom))  //musuhnya salah
                System.out.println("Kamu tidak bisa meletakkan kapal Amerika diluar koordinat" + jumlahtabel + " by " + jumlahkolom + " grid");
        }while((x < 0 || x >= jumlahtabel) || (y < 0 || y >= jumlahkolom));  //terus kan sampai tembakan mu berhasil
    }

    public static void giliranchina(){
        System.out.println("\nGiliran China");
        //Tebak Koordinat
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 8);
            y = (int)(Math.random() * 8);

            if ((x >= 0 && x < jumlahtabel) && (y >= 0 && y < jumlahkolom)) //valid guess
            {
                if (koordinat[x][y] == "@") //jika kapal pemain ada disitu; pemain kehilangan kapal
                {
                    System.out.println("China menghancurkan salah satu kapal Amerika!");
                    koordinat[x][y] = "x";
                    --BattleShips.kapalamerika;
                }
                else if (koordinat[x][y] == "x") {
                    System.out.println("China menghancurkan salah satu kapalnya sendiri");
                    koordinat[x][y] = "!";
                }
                else if (koordinat[x][y] == " ") {
                    System.out.println("China Salah Menembak Kapal Amerika");
                    //Saving missed guesses for China
                    if(menebak[x][y] != 1)
                        menebak[x][y] = 1;
                }
            }
        }while((x < 0 || x >= jumlahtabel) || (y < 0 || y >= jumlahkolom));  //Teruskan sampai tembakanmu berhasil
    }

    public static void gameOver(){
        System.out.println("Kapal Amerika: " + BattleShips.kapalamerika + " | Kapal China: " + BattleShips.kapalchina);
        if(BattleShips.kapalamerika > 0 && BattleShips.kapalchina <= 0)
            System.out.println("Hore Amerika memenangkan pertarungan nya :)");
        else
            System.out.println("Maaf Amerika kalah pada pertarungannya");
        System.out.println();
    }

    public static void printpetalaut(){
        System.out.println();
        //Bagian Pertama dari peta di laut
        System.out.print("  ");
        for(int i = 0; i < jumlahkolom; i++)
            System.out.print(i);
        System.out.println();

        //Bagian Pertengahan dari peta di laut
        for(int x = 0; x < koordinat.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < koordinat[x].length; y++){
                System.out.print(koordinat[x][y]);
            }

            System.out.println("|" + x);
        }

        //Bagian Terakhir dari peta di laut
        System.out.print("  ");
        for(int i = 0; i < jumlahkolom; i++)
            System.out.print(i);
        System.out.println();
    }
}