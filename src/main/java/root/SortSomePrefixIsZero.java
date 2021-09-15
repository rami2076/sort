package root;

import java.util.List;
import java.util.regex.Pattern;

public class SortSomePrefixIsZero {

    private static final String prefix = "0";
    private static final char prefixChar = '0';
    private static final Pattern prefixAndNumberPattern = Pattern.compile("[\\d]+");
    private static final String BLANK = "";

    public List<String> exec(List<String> target) {

        var l = target.stream().sorted((one, two) -> {
                    var eOne = extractNumberingPart(one);
                    var eTwo = extractNumberingPart(two);
                    if (!eOne.isEmpty() && !eTwo.isEmpty()) {
                        //ナンバリングされている場合、以下の判定を実施

                        if (eOne.length() == 1 && eTwo.length() == 1) {
                            //1桁の場合 数値の昇順
                            return compareWithNumber(eOne, eTwo);
                        }

                        if (eOne.startsWith(prefix) && !eTwo.startsWith(prefix)) {
                            //eOneのみprefix始まりの場合、eOneの位置を+1
                            return 1;
                        }
                        if (!eOne.startsWith(prefix) && eTwo.startsWith(prefix)) {
                            //eTwoのみprefix始まりの場合、eOneの位置を-1
                            return -1;
                        }
                        if (countPrefix(eOne) == countPrefix(eTwo)) {
                            //どちらもprefixから始まるかつ、prefixの個数が同じ場合、数値の昇順
                            return compareWithNumber(eOne, eTwo);
                        }
                        if (countPrefix(eOne) != countPrefix(eTwo)) {
                            //どちらもprefixから始まるかつ、prefixの個数が異なる場合、桁数で昇順
                            return Integer.compare(eOne.length(), eTwo.length());
                        }
                        //どちらもprefixから始まらない場合、数値の昇順
                        return compareWithNumber(eOne, eTwo);
                    }
                    //どちらかが数値で無い場合、文字列の昇順
                    return one.compareTo(two);
                })
                .toList();
        return l;
    }

    private String extractNumberingPart(String str) {

        return extractNumberingPartWithPrefix(str);

    }

    private String extractNumberingPartWithPrefix(String str) {
        var matcher = prefixAndNumberPattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return SortSomePrefixIsZero.BLANK;
        }
    }

    private long countPrefix(String addingPrefixNumber) {
        var count = addingPrefixNumber.chars()
                .takeWhile(c -> c == prefixChar)
                .count();

        if (count == addingPrefixNumber.length()) {
            return --count;
        }

        return count;
    }

    private int compareWithNumber(String left, String right) {
        var leftNum = Integer.valueOf(left);
        var rightNum = Integer.valueOf(right);
        return leftNum.compareTo(rightNum);
    }
}
