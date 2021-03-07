# asmifier-cli
A simple CLI wrapper around ObjectWeb's [Asmifier](https://asm.ow2.io/javadoc/org/objectweb/asm/util/ASMifier.html).

Pass a java .class file to the CLI to generate the ASM code necessary to generate the .class file.

## Installation
Releases are available [here](https://github.com/jackhenry/asmifier-cli/releases/tag/v0.1)

## Build 
```
git clone git@github.com:jackhenry/asmifier-cli.git
cd asmifier-cli
./gradlew distZip
```
A distribution zip will be generated in `./app/build/libs/distributions`

## Usage
```
Usage: asmifier cli [-o[=<outputFilePath>]] [-p[=<parseOption>]] <classFilePath>
Simple CLI wrapper around asmifier. Convert a class file to ASM code that
generates the class file.
      <classFilePath>   File path to the class file which will be asmified
  -o, --output[=<outputFilePath>]
                        Optional file path to write asmifier output into
  -p, --parseoption[=<parseOption>]
                        Parse Options: SKIP_CODE, SKIP_DEBUG, SKIP_FRAMES,
                          EXPAND_FRAMES
                        Default: EXPAND_FRAMES
```

## Example
```
./asmifier-cli path/to/HelloWorld.class
```

**HelloWorld.class** 
```java
Classfile HelloWorld.class
  Last modified Mar 7, 2021; size 485 bytes
  SHA-256 checksum f748d932217a27d1ae2dee9af8918294849511b5521002f76bf931512b8c0cc7
  Compiled from "HelloWorld.java"
public class HelloWorld
  minor version: 65535
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #5                          // HelloWorld
  super_class: #6                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 2, attributes: 1
Constant pool:
   #1 = Methodref          #6.#17         // java/lang/Object."<init>":()V
   #2 = Fieldref           #18.#19        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #20            // Hello, World!
   #4 = Methodref          #21.#22        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #23            // HelloWorld
   #6 = Class              #24            // java/lang/Object
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               LocalVariableTable
  #12 = Utf8               this
  #13 = Utf8               LHelloWorld;
  #14 = Utf8               greeting
  #15 = Utf8               SourceFile
  #16 = Utf8               HelloWorld.java
  #17 = NameAndType        #7:#8          // "<init>":()V
  #18 = Class              #25            // java/lang/System
  #19 = NameAndType        #26:#27        // out:Ljava/io/PrintStream;
  #20 = Utf8               Hello, World!
  #21 = Class              #28            // java/io/PrintStream
  #22 = NameAndType        #29:#30        // println:(Ljava/lang/String;)V
  #23 = Utf8               HelloWorld
  #24 = Utf8               java/lang/Object
  #25 = Utf8               java/lang/System
  #26 = Utf8               out
  #27 = Utf8               Ljava/io/PrintStream;
  #28 = Utf8               java/io/PrintStream
  #29 = Utf8               println
  #30 = Utf8               (Ljava/lang/String;)V
{
  public HelloWorld();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHelloWorld;

  public void greeting();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String Hello, World!
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 3: 0
        line 4: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   LHelloWorld;
}
SourceFile: "HelloWorld.java"
```
**output**
```java
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

public class HelloWorldDump implements Opcodes {

   public static byte[] dump() throws Exception {

      ClassWriter classWriter = new ClassWriter(0);
      FieldVisitor fieldVisitor;
      RecordComponentVisitor recordComponentVisitor;
      MethodVisitor methodVisitor;
      AnnotationVisitor annotationVisitor0;

      classWriter.visit(-65481, ACC_PUBLIC | ACC_SUPER, "HelloWorld", null, "java/lang/Object", null);

      classWriter.visitSource("HelloWorld.java", null);

      {
         methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
         methodVisitor.visitCode();
         Label label0 = new Label();
         methodVisitor.visitLabel(label0);
         methodVisitor.visitLineNumber(1, label0);
         methodVisitor.visitVarInsn(ALOAD, 0);
         methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
         methodVisitor.visitInsn(RETURN);
         Label label1 = new Label();
         methodVisitor.visitLabel(label1);
         methodVisitor.visitLocalVariable("this", "LHelloWorld;", null, label0, label1, 0);
         methodVisitor.visitMaxs(1, 1);
         methodVisitor.visitEnd();
      }
      {
         methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "greeting", "()V", null, null);
         methodVisitor.visitCode();
         Label label0 = new Label();
         methodVisitor.visitLabel(label0);
         methodVisitor.visitLineNumber(3, label0);
         methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
         methodVisitor.visitLdcInsn("Hello, World!");
         methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
         Label label1 = new Label();
         methodVisitor.visitLabel(label1);
         methodVisitor.visitLineNumber(4, label1);
         methodVisitor.visitInsn(RETURN);
         Label label2 = new Label();
         methodVisitor.visitLabel(label2);
         methodVisitor.visitLocalVariable("this", "LHelloWorld;", null, label0, label2, 0);
         methodVisitor.visitMaxs(2, 1);
         methodVisitor.visitEnd();
      }
      classWriter.visitEnd();

      return classWriter.toByteArray();
   }
}
```
