package com.kinder.kindergarten.util;

public class Hangul {
//초성 검색용 데이터 변환 작업
  private static final String[] CHOSUNG_LIST = {
          "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
          "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
  };

  public static String extractChosung(String text) {
    StringBuilder result = new StringBuilder();

    for (char ch : text.toCharArray()) {
      if (ch >= '가' && ch <= '힣') {
        int chosungIndex = (ch - '가') / (21 * 28);
        result.append(CHOSUNG_LIST[chosungIndex]);
      } else {
        result.append(ch);
      }
    }

    return result.toString();
  }

  public static boolean matchesChosung(String text, String chosung) {
    String textChosung = extractChosung(text);
    return textChosung.contains(chosung);
  }

}
