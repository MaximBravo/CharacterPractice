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
    private ArrayList<Word> hsk2;
    private Context context;
    public WordExtractor(Context context){
        this.context = context;
        hsk1 = new ArrayList<>();
        hsk2 = new ArrayList<>();
    }
    public void extractWords(int level) {
        InputStream inputStream = null;
        if(level == 1) {
            inputStream = context.getResources().openRawResource(R.raw.hsk1);
        }
        if(level == 2){
            inputStream = context.getResources().openRawResource(R.raw.hsk2);
        }
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                if(level == 1){
                    String[] wordParts = line.split(cvsSplitBy);
                    Word word = new Word(wordParts[0], wordParts[1], wordParts[2]);
                    hsk1.add(word);
                }
                if(level == 2) {
                    // use comma as separator
                    String[] wordParts = line.split(cvsSplitBy);
                    String chineseAndPinyin = wordParts[0];
                    int divider = chineseAndPinyin.indexOf(' ');
                    String chinese = chineseAndPinyin.substring(0, divider);
                    String pinyin = chineseAndPinyin.substring(divider, chineseAndPinyin.length());
                    Word word = new Word(chinese, pinyin, wordParts[1]);
                    hsk2.add(word);
                }
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
    public ArrayList<Word> getHskLevel(int level){
        if(level == 1) {
            return hsk1;
        }
        if(level == 2){
            return hsk2;
        }
        return null;
    }

    public String getHskLevelAsString(int level){
        if(level == 1){
            return getHskLevelAsString(hsk1);
        }
        if(level == 2){
            return getHskLevelAsString(hsk2);
        }
        return null;
    }
    private String getHskLevelAsString(ArrayList<Word> hsk){
        if(hsk != null) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < hsk.size(); i++) {
                result.append(hsk.get(i) + "\n");
            }
            return result.toString();
        }
        return "Hsk is empty";
    }
}
