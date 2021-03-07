/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package github.jackhenry;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "neatlang", description = "neatlang compiler")
public class CLI implements Callable<Integer> {

    @Option(names = { "-c", "--class" })
    private String classFilePath;

    enum ParseOption {
        SKIP_CODE, SKIP_DEBUG, SKIP_FRAMES, EXPAND_FRAMES
    }

    @Option(names = { "-p", "--parseoption" }, arity = "0..1", description = { "(Optional)",
            "Parse Options: ${COMPLETION-CANDIDATES}", "Default: EXPAND_FRAMES" })
    private ParseOption parseOption = ParseOption.EXPAND_FRAMES;

    @Option(names = { "-o",
            "--output" }, arity = "0..1", description = "Optional file path to write asmifier output into")
    private String outputFilePath = null;

    @Override
    public Integer call() throws Exception {
        PrintWriter printWriter;

        // Write output to standard out if --output option is not passed
        if (outputFilePath == null) {
            printWriter = new PrintWriter(System.out);
        } else {
            printWriter = new PrintWriter(new File(outputFilePath));
        }

        AsmifierWrapper.asmify(classFilePath, printWriter);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
