package com.maximbravo.characterpractice;

/**
 * Created by Maxim Bravo on 3/23/2017.
 */

public class Word {
    private String chinese;
    private String pinyin;
    private String english;
    public Word(String c, String p, String e){
        chinese = c;
        pinyin = p;
        english = e;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String toString(){
        return chinese + " - " + pinyin + " - " + english;
    }
}
