package template.testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import template.Solution;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class SolutionTest {

    ///////////// Test-specific codes: you only need to modify these lines. /////////////////
    // Solutions to test.
    // Java 8 is required to use 'Consumer' interface,
    Consumer<InputStream> SOLUTION_TO_TEST_1 = istream -> Solution.solution1(istream);
    Consumer<InputStream> SOLUTION_TO_TEST_2 = istream -> Solution.solution2(istream);

    // Input and answer files.
    private final String[] INPUT_FILE_PATHS = {
            "src/template/testcases/input1.txt",
            "src/template/testcases/input2.txt"
    };
    private final String[] ANSWER_FILE_PATHS = {
            "src/template/testcases/output1.txt",
            "src/template/testcases/output2.txt"
    };

    // '@Test' function should call 'runTest(...)'
    // Parameter of 'runTest(...)':
    //      1st: Input/answer file index in INPUT_FILE_PATHS & ANSWER_FILE_PATHS
    //      2nd: Solution function to test.
    @Test private void testSolution1_1() throws Exception {   runTest(0, SOLUTION_TO_TEST_1);   }
    @Test private void testSolution1_2() throws Exception {   runTest(1, SOLUTION_TO_TEST_1);   }
    @Test private void testSolution2_1() throws Exception {   runTest(0, SOLUTION_TO_TEST_2);   }
    @Test private void testSolution2_2() throws Exception {   runTest(1, SOLUTION_TO_TEST_2);   }
    /////////////////////////////////////////////////////////////////////////////////////////

    private final int numOfTestCases;
    private FileInputStream istream;
    private ByteArrayOutputStream outContent;
    private Path answerFilePath;
    private long[] runningTimes;
    PrintStream original = System.out;

    private SolutionTest() {
        numOfTestCases = INPUT_FILE_PATHS.length;
        runningTimes = new long[numOfTestCases];
    }

    // The annotated method will be run after all the test methods in the current class have been run.
    @AfterClass
    public void printRunningTimes() {
        // Print after stdout is restored. During tests, stdout is directed to ByteArrayOutputStream.
        for (int i = 0; i < numOfTestCases; i++) {
            System.out.println("Test case #" + (i+1) + " took " + Long.toString(runningTimes[i])  + "ms to run.");
        }
    }

    private void runTest(int testIdx, Consumer<InputStream> solutionToTest) throws Exception {
        long startTime, stopTime;
        outContent =  new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        istream = new FileInputStream(INPUT_FILE_PATHS[testIdx]);
        answerFilePath = Paths.get(ANSWER_FILE_PATHS[testIdx]);

        startTime = System.currentTimeMillis();
        solutionToTest.accept(istream);
        stopTime = System.currentTimeMillis();
        runningTimes[testIdx] = stopTime - startTime;

        String answer = new String(Files.readAllBytes(answerFilePath)).trim();
        String result = outContent.toString().trim();
        answer = answer.replaceAll("\\r", "");  // Remove carrige return from the answer
        result = result.replaceAll("\\r", "");  // Remove carrige return from the result

        Assert.assertEquals(result, answer, "Test case #" + (testIdx+1) + " is failed.");

        istream.close();
        outContent.close();

        System.setOut(original);
    }
}