package utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class RandomStringGenerator {
    private RandomStringGenerator() {
    }

    private static char getRandomCharInRange(char start, char end) {
        Random random = new Random();
        return (char) (random.nextInt(end - start + 1) + start);
    }

    private static String createStringFromCharRange(int length, char start, char end) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(getRandomCharInRange(start, end));
        }
        return stringBuilder.toString();
    }

    /**
     * Creates string of latin characters in uppercase of {@code length} length
     * For example: if length == 4, return "HQPB"
     *
     * @param length the length
     * @return built string
     */
    public static String createRandomLatinUppercaseString(int length) {
        return createStringFromCharRange(length, 'A', 'Z');
    }

    /**
     * Creates string containing {@code wordCount} words of latin characters separated by spaces
     * For example: if wordCount == 4, return "Tyrion Cersei Sansa Jorah"
     *
     * @param wordCount the word count
     * @return built string
     */
    public static String createRandomLatinSentence(int wordCount) {
        Faker faker = new Faker();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            stringBuilder.append(faker.gameOfThrones().character());
            if (i != wordCount - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
