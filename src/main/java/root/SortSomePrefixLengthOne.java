package root;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;

public class SortSomePrefixLengthOne {

    public SortSomePrefixLengthOne(char prefix) {
        this.prefix = String.valueOf(prefix);
        this.prefixChar = prefix;
        this.prefixAndNumberPattern = Pattern.compile(prefix + "+[1-9][0-9]{0,}");
    }

    private final String prefix;
    private final char prefixChar;
    private final Pattern prefixAndNumberPattern;

    private static final Pattern NUMBER_PATTERN = Pattern.compile("[1-9][0-9]{0,}");
    private static final String BLANK = "";


    public List<String> exec(List<String> target) {

        var l = target.stream().sorted((one, two) -> {
                    var eOne = extractNumberingPart(one);
                    var eTwo = extractNumberingPart(two);
                    if (!eOne.isEmpty() && !eTwo.isEmpty()) {
                        //ナンバリングされている場合

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
                            return omitPrefixAndToInteger(eOne).compareTo(omitPrefixAndToInteger(eTwo));
                        }
                        if (countPrefix(eOne) != countPrefix(eTwo)) {
                            //どちらもprefixから始まるかつ、prefixの個数が異なる場合、桁数で昇順
                            return Integer.compare(eOne.length(), eTwo.length());
                        }
                        //どちらもprefixから始まらない場合、数値の昇順
                        return Integer.valueOf(eOne).compareTo(Integer.valueOf(eTwo));
                    }
                    //どちらかが数値で無い場合、文字列の昇順
                    return one.compareTo(two);
                })
                .toList();
        return l;
    }


    private String extractNumberingPart(String str) {

        var first = extractNumberingPartWithPrefix(str);

        if (first.isEmpty()) {
            return extractNumberingPartPlain(str);
        }
        return first;

    }

    private String extractNumberingPartPlain(String str) {
        var matcher = SortSomePrefixLengthOne.NUMBER_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return SortSomePrefixLengthOne.BLANK;
        }
    }

    private String extractNumberingPartWithPrefix(String str) {
        var matcher = prefixAndNumberPattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return SortSomePrefixLengthOne.BLANK;
        }
    }

    private Integer omitPrefixAndToInteger(String addingPrefixNumber) {
        var join = addingPrefixNumber.chars()
                .filter(c -> c != prefixChar)
                .mapToObj(c -> (char) c)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString
                ));
        return Integer.parseInt(join);
    }

    private long countPrefix(String addingPrefixNumber) {
        var count = addingPrefixNumber.chars()
                .takeWhile(c -> c == prefixChar)
                .count();
        return count;
    }

}
