package com.example.el3gost.td02ex02;

import java.util.ArrayList;
import java.util.List;

public class GrilleStatus
{
    private int[][] grilleMorpion;
    private int cptCroix;
    private int cptRond;
    private int caseVide;
    private int results;
    private List<Integer> listMove = new ArrayList<Integer>();
    private int currentPlayer = 1;

    public GrilleStatus()
    {
        grilleMorpion = new int[3][3];
        for(int i=0; i < 3; i++)
            for(int j=0; j < 3; j++)
                grilleMorpion[i][j] = 0;
    }

    public int getResults()
    {
        return results;
    }

    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    public int setGrilleValue(int i, int j)
    {
        if(grilleMorpion[i][j] == 0) {
            listMove.add(i * 3 + j);
            grilleMorpion[i][j] = currentPlayer;
            if (currentPlayer == 1)
                currentPlayer = 2;
            else
                currentPlayer = 1;
            return 1;
        }
        return 0;
    }

    public void removeLastGrilleValue()
    {
        if(listMove.size() > 0){
        switch (listMove.get(listMove.size()-1))
        {
            case 0 : grilleMorpion[0][0] = 0 ;break;
            case 1 : grilleMorpion[0][1] = 0;break;
            case 2 : grilleMorpion[0][2] = 0;break;
            case 3 : grilleMorpion[1][0] = 0;break;
            case 4 : grilleMorpion[1][1] = 0;break;
            case 5 : grilleMorpion[1][2] = 0;break;
            case 6 : grilleMorpion[2][0] = 0;break;
            case 7 : grilleMorpion[2][1] = 0;break;
            case 8 : grilleMorpion[2][2] = 0;break;
        }
        if (currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
        listMove.remove(listMove.size()-1);
        }
    }

    public int getGrilleValue(int i, int j)
    {
        return grilleMorpion[i][j];
    }

    public String printPlaintTxtGrille() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grilleMorpion[i][j] == 0)
                    str.append('_');
                else if (grilleMorpion[i][j] == 1)
                    str.append('X');
                else str.append('O');
            }
            str.append('\n');
        }
        str.append('\0');
        return str.toString();
    }

    public int checkResults()
    {
        int emptyBox = 0;
        //Grille Pleine
        for(int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grilleMorpion[i][j] == 0)
                    emptyBox++;
            }
        }
        if(emptyBox == 0)
            return results = 0;
        //Ligne horizontale
        for(int i = 0; i < 3; i++)
        {
            if(grilleMorpion[i][0] == 1 && grilleMorpion[i][1] == 1 && grilleMorpion[i][2] == 1)
                return results = 1;
            if(grilleMorpion[i][0] == 2 && grilleMorpion[i][1] == 2 && grilleMorpion[i][2] == 2)
                return results = 2;
        }
        //Ligne Verticale
        for(int i = 0; i < 3; i++)
        {
            if(grilleMorpion[0][i] == 1 && grilleMorpion[1][i] == 1 && grilleMorpion[2][i] == 1)
                return results = 1;
            if(grilleMorpion[0][i] == 2 && grilleMorpion[1][i] == 2 && grilleMorpion[2][i] == 2)
                return results = 2;
        }
        //Diagonale
        if(grilleMorpion[0][0] == 1 && grilleMorpion[1][1] == 1 && grilleMorpion[2][2] == 1)
            return results = 1;
        if(grilleMorpion[0][0] == 2 && grilleMorpion[1][1] == 2 && grilleMorpion[2][2] == 2)
            return results = 2;
        if(grilleMorpion[0][2] == 1 && grilleMorpion[1][1] == 1 && grilleMorpion[2][0] == 1)
            return results = 1;
        if(grilleMorpion[0][2] == 2 && grilleMorpion[1][1] == 2 && grilleMorpion[2][2] == 2)
            return results = 2;
        return results = -1;
    }

    public int SearchForBestChoiceIA()
    {
        int i;
        int j;
        //Recherche horizontale
        resetCpt();
        for(i = 0; i < 3; i++)
            for(j = 0; j < 3; j++)
                cptCases(i, j);
        if (cptCroix == 2 && caseVide == 1)
            return searchHorizontaleCaseVide(i);
        if (cptRond == 2 && caseVide == 1)
            return searchHorizontaleCaseVide(i);

        //Recherche Verticale
        resetCpt();
        for(j = 0; j < 3; j++)
            for(i = 0; i < 3; i++)
                cptCases(i, j);
        if (cptCroix == 2 && caseVide == 1)
            return searchHorizontaleCaseVide(j);
        if (cptRond == 2 && caseVide == 1)
            return searchVerticaleCaseVide(j);

        //Recherche Diagonale
        resetCpt();
        for(i=0; i < 3; i++)
            cptCases(i, i);
        if (cptCroix == 2 && caseVide == 1)
            return searchDiagonaleCaseVide();
        if (cptRond == 2 && caseVide == 1)
            return searchDiagonaleCaseVide();

        resetCpt();
        cptCases(0, 2);
        cptCases(1, 1);
        cptCases(2, 0);
        if (cptCroix == 2 && caseVide == 1)
            return searchInvertDiagonaleCaseVide();
        if (cptRond == 2 && caseVide == 1)
            return searchInvertDiagonaleCaseVide();

        //Sinon on prend la premiere case vide qu'on trouve
        for(i=0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (grilleMorpion[i][j] == 0)
                    return (i * 3 + j);
            }
        }

        return -1;
    }

    private void cptCases(int i, int j)
    {
        switch (grilleMorpion[i][j]) {
            case 0:
                caseVide++;
                break;
            case 1:
                cptCroix++;
                break;
            case 2:
                cptRond++;
                break;
        }
    }

    private void resetCpt()
    {
        caseVide = 0;
        cptCroix = 0;
        cptRond = 0;
    }


    private int searchHorizontaleCaseVide(int i)
    {
        for(int j = 0; j < 3; j++)
            if(grilleMorpion[i][j]==0)
                return i * 3 + j;
        return -1;
    }

    private int searchVerticaleCaseVide(int j)
    {
        for(int i = 0; i < 3; i++)
            if(grilleMorpion[i][j]==0)
                return i * 3 + j;
        return -1;
    }
    private int searchDiagonaleCaseVide()
    {
        for(int i = 0; i < 3; i++)
            if(grilleMorpion[i][i]==0)
                return i * 3 + i;
        return -1;
    }

    private int searchInvertDiagonaleCaseVide()
    {
        if(grilleMorpion[0][2] == 0)
            return 2;
        if(grilleMorpion[1][1] == 0)
            return 4;
        if(grilleMorpion[2][0] == 0)
            return 6;
        return -1;
    }
}
