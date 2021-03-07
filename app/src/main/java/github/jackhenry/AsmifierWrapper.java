package github.jackhenry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class AsmifierWrapper {

    public static void asmify(String filePath, PrintWriter printWriter) throws IOException {
        FileInputStream classFileStream = new FileInputStream(filePath);
        TraceClassVisitor visitor = new TraceClassVisitor(null, new ASMifier(), printWriter);
        ClassReader classReader = new ClassReader(classFileStream);

        classReader.accept(visitor, ClassReader.EXPAND_FRAMES);
    }
}
