import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class TestSimpleRegexMatches {

    public static int runTest(String regex, String text) {
        int matches = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {
        String source = "foofoo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        assertTrue(matcher.find());
    }

    @Test
    public void givenText_whenMatchesWithDotMetach_thenCorrect() {
        String source = "foofoo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }

        assertEquals(matches, 2);
    }

    @Test
    public void givenText_whenMatchesWithDotMetach_thenCorrect2() {
        int matches = runTest(".", "foo"); // .을 정규식에 넣으면 전체문자 길이가 나온다

        assertTrue(matches > 0);
    }

    @Test
    public void givenORSet_whenMatchesAny_thenCorrect() {
        int matches = runTest("[abc]", "b");

        assertEquals(matches, 1);
    }

    @Test
    public void givenORSet_whenMatchesAllCombinations_thenCorrect() {
        int matches = runTest("[bcr]at", "bat cat rat");

        assertEquals(matches, 3);
    }

    @Test
    public void givenNORSet_whenMatchesNon_thenCorrect() {
        int matches = runTest("[^abc]", "ag"); // abc를 제외한 나머지가 있는가? 캐릭터 하나씩 찾음

        assertTrue(matches > 0);
    }

    
    @Test
    public void givenLowerCaseRange_whenMatchesLowerCase_thenCorrect() {
        int mathcer = runTest("[a-z]", "Two Uppercase alphabets 34 overall");

        assertEquals(26, mathcer);
    }

    @Test
    public void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
        int matches = runTest("[A-z]", "Two Uppercase alphabets 34 overall");

        assertEquals(28, matches);
    }

    @Test
    public void union1() {
        int mathces = runTest("[1-3[7-9]]", "123456789");

        assertEquals(mathces, 6);
    }

    @Test
    public void givenBraceQuantifierWithRange_whenMatches_thenCorrect() {
        int matches = runTest("a{2}", "aaaa");

        assertEquals(matches, 2);
    }

    @Test
    public void givenZeroOrOneQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a{0,}", "hi");
        assertEquals(matches, 3);
    }

    @Test
    public void givenOneOrManyQuantifier_whenMatches_thenCorrect2() {
        int matches = runTest("\\a{1,}", "hi");

        assertFalse(matches > 0);
    }

    @Test
    public void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect() {
        int mathces = runTest("(\\d\\d)\\1\\1\\1", "12121212");
        // \\1 은 그룹의 번호를 의미함
        assertEquals(mathces, 1);
    }

    @Test
    public void givenText_whenMatchesAtBeginning_thenCorrect() {
        int matches = runTest("^dog", "dogs are friendly");

        assertTrue(matches > 0);
    }

    @Test
    public void givenText_whenMatchesAtBeginning_thenCorrect1() {
        int matches = runTest("^dog", "dogs are friendly");

        assertTrue(matches > 0);
    }

    @Test
    public void integerPatter_Check(){
        String regex="\\b[+-]?(0|[1-9][0-9]{0,9})\\b";
        
        assertEquals(1, runTest(regex, "0"));
        assertEquals(1, runTest(regex, "1"));
        assertEquals(1, runTest(regex, "123"));
        assertEquals(0, runTest(regex, "12345678901"));
        assertEquals(1, runTest(regex, "+0"));
        assertEquals(1, runTest(regex, "+1"));
        assertEquals(0, runTest(regex, "+01"));
        assertEquals(1, runTest(regex, "+123"));
        assertEquals(0, runTest(regex, "+12345678901"));
        assertEquals(1, runTest(regex, "-0"));
        assertEquals(1, runTest(regex, "-1"));
        assertEquals(0, runTest(regex, "-01"));
        assertEquals(1, runTest(regex, "-123"));
        assertEquals(0, runTest(regex, "-12345678901"));
    }

}
