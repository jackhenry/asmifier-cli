package github.jackhenry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "asmifier cli", description = "Simple CLI wrapper around asmifier. Convert a java .class file to ASM code that generates the .class file.")
public class CLI implements Callable<Integer> {
    @Parameters(index = "0", description = "File path to the class file which will be asmified")
    private String classFilePath;

    enum ParseOption {
        SKIP_CODE, SKIP_DEBUG, SKIP_FRAMES, EXPAND_FRAMES
    }

    @Option(names = { "-p", "--parseoption" }, arity = "0..1", defaultValue = "EXPAND_FRAMES", description = {
            "Parse Options: ${COMPLETION-CANDIDATES}", "Default: ${DEFAULT-VALUE}" })
    private ParseOption parseOption = ParseOption.EXPAND_FRAMES;

    @Option(names = { "-o",
            "--output" }, arity = "0..1", description = "Optional file path to write asmifier output into")
    private String outputFilePath = null;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Output a usage message")
    boolean helpRequested;

    @Override
    public Integer call() throws Exception {
        PrintWriter printWriter;

        if (!(new File(classFilePath).exists())) {
            throw new FileNotFoundException("Unable to find a class file at '" + classFilePath + "'");
        }
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
