package com.example.el3gost.td02ex02;

import java.util.List;

public class GrilleStatus
{
    private int[][] grilleMorpion;

    public GrilleStatus()
    {
        grilleMorpion = new int[3][3];
        for(int i=0; i < 3; i++)
            for(int j=0; j < 3; j++)
                grilleMorpion[i][j] = 0;
    }

    public void setGrilleValue(int i, int j, int value)
    {
        grilleMorpion[i][j] = value;
    }

    public int getGrilleValue(int i, int j)
    {
        return grilleMorpion[i][j];
    }

    public String printPlaintTxtGrille()
    {
        StringBuilder str = new StringBuilder();
        for(int i=0; i < 3; i++)
        {
            for(int j=0; j < 3; j++)
            {
                if(grilleMorpion[i][j] == 0)
                    str.append('_');
                else if(grilleMorpion[i][j] == 1)
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
            return 0;
        //Ligne horizontale
        for(int i = 0; i < 3; i++)
        {
            if(grilleMorpion[i][0] == 1 && grilleMorpion[i][1] == 1 && grilleMorpion[i][2] == 1)
                return 1;
            if(grilleMorpion[i][0] == 2 && grilleMorpion[i][1] == 2 && grilleMorpion[i][2] == 2)
                return 2;
        }
        //Ligne Verticale
        for(int i = 0; i < 3; i++)
        {
            if(grilleMorpion[0][i] == 1 && grilleMorpion[1][i] == 1 && grilleMorpion[2][i] == 1)
                return 1;
            if(grilleMorpion[0][i] == 2 && grilleMorpion[1][i] == 2 && grilleMorpion[2][i] == 2)
                return 2;
        }
        //Diagonale
        if(grilleMorpion[0][0] == 1 && grilleMorpion[1][1] == 1 && grilleMorpion[2][2] == 1)
            return 1;
        if(grilleMorpion[0][0] == 2 && grilleMorpion[1][1] == 2 && grilleMorpion[2][2] == 2)
            return 2;
        if(grilleMorpion[0][2] == 1 && grilleMorpion[1][1] == 1 && grilleMorpion[2][0] == 1)
            return 1;
        if(grilleMorpion[0][2] == 2 && grilleMorpion[1][1] == 2 && grilleMorpion[2][2] == 2)
            return 2;
        return -1;
    }

    public int SearchForBestChoiceIA()
    {
        int cptCroix;
        int cptRond;
        int caseVide;
        int i;
        int j;
        //Recherche horizontale
        for(i = 0; i < 3; i++)
        {
            cptCroix=0;
            cptRond=0;
            caseVide=0;
            for(j = 0; j < 3; j++) {
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
                if (cptCroix == 2 && caseVide == 1)
                    return searchHorizontaleCaseVide(i);
                if (cptRond == 2 && caseVide == 1)
                    return searchHorizontaleCaseVide(i);
            }
        }
        //Recherche Verticale
        for(j = 0; j < 3; j++)
        {
            cptCroix=0;
            cptRond=0;
            caseVide=0;
            for(i = 0; i < 3; i++) {
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
                if (cptCroix == 2 && caseVide == 1)
                    return searchHorizontaleCaseVide(j);
                if (cptRond == 2 && caseVide == 1)
                    return searchVerticaleCaseVide(j);
            }
        }
        //Recherche Diagonale
        caseVide =0;
        cptCroix = 0;
        cptRond = 0;
        for(i=0; i < 3; i++) {
            switch (grilleMorpion[i][i]) {
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
            if (cptCroix == 2 && caseVide == 1)
                return searchDiagonaleCaseVide();
            if (cptRond == 2 && caseVide == 1)
                return searchDiagonaleCaseVide();
        }
        //Sinon on prend la premiere case vide qu'on trouve
        for(i=0; i < 3; i++)
        {
            for(j=0; j < 3; j++)
            {
                if(grilleMorpion[i][j] == 0)
                    return (i * 3 + j);
            }
        }
        return -1;
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
}
