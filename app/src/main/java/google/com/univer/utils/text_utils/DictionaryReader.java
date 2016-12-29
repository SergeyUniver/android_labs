package google.com.univer.utils.text_utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import google.com.univer.model.TagedWord;

/**
 * Created by Sergey on 21.09.2016.
 */
public class DictionaryReader {

    public static final String DEFAULT_DICTIONARY = "sdcard/text.txt";
    public static final String DEFAULT_TAGGER = "sdcard/Tagged.txt";
    private static final String TAG = DictionaryReader.class.getSimpleName();

    public static ArrayList<String> read(String fileName) {
        ArrayList<String> lineList = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineList.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    public static ArrayList<String> getDefaultText(int maxLine){
        ArrayList<String> textList = new ArrayList<>();
        try {
            File file = new File(DEFAULT_DICTIONARY);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();
            String line;
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if(currentLine >= maxLine){
                    currentLine = 0;
                    textList.add(builder.toString());
                    builder = new StringBuilder();
                }
                builder.append(line);
                currentLine ++;
            }
            textList.add(builder.toString());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textList;
    }

    public static HashMap<String, HashMap<String, Integer>> generateTaggedList(String path) {
        HashMap<String, HashMap<String, Integer>> mapWords = new HashMap<>();
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                updateMapWords(getTaggedListFormList(line), mapWords);
                Log.d(TAG, "line: " + i);
                i ++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapWords;
    }

    private static void updateMapWords(ArrayList<TagedWord> taggedListFormList, HashMap<String, HashMap<String, Integer>> mapWords) {
        for(TagedWord word : taggedListFormList){
            if(mapWords.containsKey(word.getWord())){
                Integer count = 0;
                if(mapWords.get(word.getWord()).containsKey(word.getTag())){
                    count = mapWords.get(word.getWord()).get(word.getTag());
                }
                count ++;
                mapWords.get(word.getWord()).put(word.getTag(), count);
            }else{
                HashMap<String, Integer> hashSet = new HashMap<>();
                hashSet.put(word.getTag(), 1);
                mapWords.put(word.getWord(), hashSet);
            }
        }
    }

    private static ArrayList<TagedWord> getTaggedListFormList(String line) {
        ArrayList<TagedWord> list = new ArrayList<>();
        String[] splittedText = line.split(" ");
        for(int i = 0; i < splittedText.length; i ++){
            String[] tager = splittedText[i].split("/");
            if(tager.length == 2) {
                list.add(new TagedWord(tager[0], tager[1]));
            }
        }
        return list;
    }
}
