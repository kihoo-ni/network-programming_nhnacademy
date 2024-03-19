package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;


public class Jsonpractice6 {
    public static void main(String[] args) {

  //      String [] birds={"갈매기", "참새", "펭귄"};
  //      String [] mamals={"사자", "호랑이", "말"}; 배열만들어서 넣으면 쉽게함



        JSONArray bird=new JSONArray();
        bird.put("갈매기");
        bird.put("참새");
        bird.put("펭귄");

        JSONObject birds=new JSONObject();
        birds.put("조류", bird);

        JSONArray mammalia=new JSONArray();
        mammalia.put("사자");
        mammalia.put("호랑이");
        mammalia.put("말");

        JSONObject mammalias=new JSONObject();
        mammalias.put("포유류", mammalia);


        JSONArray animalArray= new JSONArray();
        animalArray.put(mammalias);
        animalArray.put(birds);
     //   animalArray.put(0, birds); 인덱스로 바꾸기 가능

        JSONObject animal=new JSONObject();
        animal.put("동물", animalArray);


        System.out.println(animal);
    }
}
