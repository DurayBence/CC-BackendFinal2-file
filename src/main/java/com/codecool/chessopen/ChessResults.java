package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> contenders = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        List<String> resultList = new ArrayList<>();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        if (fileReader == null || bufferedReader == null) {
            return null;
        }

        try {
            while (true) {
                String currentLine = bufferedReader.readLine();
                if (currentLine == null) {
                    break;
                }

                String[] lineParts = currentLine.split(",");
                contenders.add(lineParts[0]);

                int currentContenderScore = 0;
                for (int i = 1; i < lineParts.length; i++) {
                    try {
                        currentContenderScore += Integer.parseInt(lineParts[i]);
                    } catch (NumberFormatException e) {
                        //skip current linePart element (not integer)
                    }
                }
                scores.add(currentContenderScore);
            }

            bubbleSortListPairByValue(contenders, scores);

            for (int i = 0; i < contenders.size(); i++) {
                resultList.add(contenders.get(i));
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("File reading failed");
        }

        return resultList;
    }

    private static void bubbleSortListPairByValue(List<String> keys, List<Integer> values) {
        for (int i = 0; i < values.size() - 1; i++) {
            for (int j = 0; j < values.size() - i - 1; j++) {
                if (values.get(j) < values.get(j + 1)) {
                    int tempInt = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, tempInt);

                    String tempStr = keys.get(j);
                    keys.set(j, keys.get(j + 1));
                    keys.set(j + 1, tempStr);
                } else if (values.get(j) == values.get(j + 1)) {
                    if (keys.get(j).compareTo(keys.get(j + 1)) < 0) {
                        int tempInt = values.get(j);
                        values.set(j, values.get(j + 1));
                        values.set(j + 1, tempInt);

                        String tempStr = keys.get(j);
                        keys.set(j, keys.get(j + 1));
                        keys.set(j + 1, tempStr);
                    }
                }
            }
        }
    }

}
