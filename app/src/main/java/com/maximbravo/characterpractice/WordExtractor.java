package com.maximbravo.characterpractice;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Maxim Bravo on 3/23/2017.
 */

public class WordExtractor {
    private ArrayList<Word> hsk1;
    private Context context;
    public WordExtractor(Context context){
        this.context = context;
        hsk1 = new ArrayList<>();
    }
    public void extractWords() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.hsk1);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] wordParts = line.split(cvsSplitBy);
                Word word = new Word(wordParts[0], wordParts[1], wordParts[2]);
                hsk1.add(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public ArrayList<Word> getHsk1(){
        return hsk1;
    }

    public String getHsk1AsString(){
        if(hsk1 != null) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < hsk1.size(); i++) {
                result.append(hsk1.get(i) + "\n");
            }
            return result.toString();
        }
        return "Hsk1 is empty";
    }
}
