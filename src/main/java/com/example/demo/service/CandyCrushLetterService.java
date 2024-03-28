package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CandyCrushLetterService {

    /**
     * remove character more than 3
     * Example:
     * Input: aabcccbbad
     * Output:
     * -> aabbbad
     * -> aaad
     * -> d
     * @param str
     */
    public void removeCharacters(String str) {
        replaceLetter(str, 0, false);
    }

    /**
     * replace characters with character that comes before it alphabetically.
     * Example:
     *  Input: abcccbad
     *  Output:
     *  -> abbbad, ccc is replaced by b
     *  -> aaad, bbb is replaced by a
     *  -> d
     * @param str
     */
    public void replaceByPreviousLetter(String str) {
        replaceLetter(str, 0, true);
    }

    /**
     * main
     * @param str
     * @param start
     * @param replaceByPreviousLetter
     * @return
     */
    private String replaceLetter(String str, int start, boolean replaceByPreviousLetter) {
        int minLength = 3;
        while (start < str.length() && start + minLength <= str.length()){
            String subStr = str.substring(start, start + minLength);
            if (isIdenticalCharacters(subStr)) {
                for (int j = start + minLength; j <= str.length(); j++) {
                    subStr = str.substring(start, j);
                    // check whether the subStr is same
                    if (!isIdenticalCharacters(subStr) || j == str.length()) {
                        // if current character is the end, process the whole, else process except the latest character
                        boolean justEnd = isIdenticalCharacters(subStr) && j == str.length();
                        // if replace, get replace character
                        String replaceCharacter = (replaceByPreviousLetter ? getPreviousLetter(subStr.charAt(0)) : "");
                        str = str.substring(0, start)
                                + replaceCharacter
                                + str.substring(j - (justEnd ? 0 : 1));
                        String toReplaceCharacters = subStr.substring(0, subStr.length() - (justEnd ? 0 : 1));
                        String extraInfo = ", " + toReplaceCharacters + " is replaced by " + replaceCharacter;
                        System.out.print(str);
                        System.out.println(!ObjectUtils.isEmpty(replaceCharacter)  ? extraInfo : "");
                        // word changes, repeat
                        return replaceLetter(str, 0, replaceByPreviousLetter);
                    }
                }
            }
            start++;
        }
        return str;
    }

    /**
     * @param character
     * @return if 'a' return "" else return the previous letter
     */
    private String getPreviousLetter(char character) {
        char first = 'a';
        if (character == first) {
            return "";
        }
        return String.valueOf((char) (character - 1));
    }

    /**
     * whether the str is identical
     *
     * @param str
     * @return true identical characters or false
     */
    private boolean isIdenticalCharacters(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char firstLetter = str.charAt(0);
        for (char l : str.toCharArray()) {
            if (l != firstLetter) {
                return false;
            }
        }
        return true;
    }

}
