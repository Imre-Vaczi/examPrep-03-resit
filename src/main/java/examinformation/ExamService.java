package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExamService {

    private Map<String, ExamResult> results = new TreeMap<>();
    private int theoryMax;
    private int practiceMax;

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public void readFromFIle(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String line;
            populateResults(bf);
        }
        catch (IOException ioException) {
            throw new IllegalArgumentException("Cannot read file: " + path.toString(), ioException);
        }
    }

    private void populateResults(BufferedReader bf) throws IOException {
        String line;
        while((line= bf.readLine()) != null) {
            if (line.split(";").length == 1) {
                theoryMax = Integer.parseInt(String.valueOf(line.split(" ")[0]));
                practiceMax = Integer.parseInt(String.valueOf(line.split(" ")[1]));
            } else {
                addToResults(line);
            }
        }
    }

    private void addToResults(String line) {
        String name = line.split(";")[0];
        int resTheory = Integer.parseInt(String.valueOf(line.split(";")[1].split(" ")[0]));
        int resPractice = Integer.parseInt(String.valueOf(line.split(";")[1].split(" ")[1]));
        results.put(name, new ExamResult(resTheory, resPractice));
    }

    public List<String> findPeopleFailed() {
        List<String> failedParticipants = new ArrayList<>();
        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            boolean isPracticeFailed = calculateResult(practiceMax, entry.getValue().getPractice()) < 51;
            boolean isTheoryFailed = calculateResult(theoryMax, entry.getValue().getTheory()) < 51;
            if (isPracticeFailed || isTheoryFailed) {
                failedParticipants.add(entry.getKey());
            }
        }
        return failedParticipants;
    }

    private int calculateResult(int maxPoint, int achieved) {
        return (int) ((achieved/(maxPoint*1.0))*100);
    }

    public String findBestPerson() {
        int tempMax = Integer.MIN_VALUE;
        String tempWinner = null;

        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            boolean isPracticeFailed = calculateResult(practiceMax, entry.getValue().getPractice()) < 51;
            boolean isTheoryFailed = calculateResult(theoryMax, entry.getValue().getTheory()) < 51;
            int tempSum = entry.getValue().getPractice() + entry.getValue().getTheory();
            if (!isPracticeFailed & !isTheoryFailed & tempSum > tempMax) {
                tempMax = tempSum;
                tempWinner = entry.getKey();
            }
        }
        return tempWinner;
    }
}
