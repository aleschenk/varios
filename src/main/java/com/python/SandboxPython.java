package com.python;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;

public class SandboxPython {

  public static void main(String[] args) throws Exception {
//    usingProcessBuilder();
    float f = 0.1f;
    float d = 10f;
    System.out.println(1.03d - .42d);
    System.out.println(1.03f - .42f);
    System.out.println(new BigDecimal("1.03").subtract(new BigDecimal(".42")));
  }

  public static void usingProcessBuilder() throws IOException {
    ProcessBuilder pb = new ProcessBuilder("python", "numbers.py");
    pb.directory(new File("."));
    File log = new File("log");
    pb.redirectErrorStream(true);
    pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
    Process p = pb.start();

    assert pb.redirectInput() == ProcessBuilder.Redirect.PIPE;
    assert pb.redirectOutput().file() == log;
    assert p.getInputStream().read() == -1;

  }

  public static void usingJython() throws ScriptException, IOException {
    StringWriter writer = new StringWriter(); //ouput will be stored here

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptContext context = new SimpleScriptContext();

    context.setWriter(writer); //configures output redirection
    ScriptEngine engine = manager.getEngineByName("python");
    engine.eval(new FileReader("numbers.py"), context);
    System.out.println(writer.toString());
  }

}
