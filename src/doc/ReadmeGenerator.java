
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.perunlabs.jsolid.d3.Solid;
import com.perunlabs.jsolid.d3.Stl;

public class ReadmeGenerator {
  private static final String STLS_DIR = "/home/mikoch/src/jsolid/doc/";

  public static void main(String[] args) throws IOException {
    List<Example> examples = readExamples();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./README.md")))) {
      writeIntroduction(writer, examples);
      writeExamples(writer, examples);
    }
    writeStls();
  }

  private static List<Example> readExamples() throws IOException, FileNotFoundException {
    List<Example> examples = new ArrayList<>();
    Iterator<String> it = linesOf("./src/doc/Examples.java").iterator();
    do {
      Example example = new Example();
      example.name = findNextMethod(it);
      if (it.hasNext()) {
        example.code = getCode(it);
        examples.add(example);
      }
    } while (it.hasNext());
    return examples;
  }

  private static List<String> linesOf(String filePath) throws IOException, FileNotFoundException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
      String line = null;
      while (null != (line = reader.readLine())) {
        lines.add(line.trim());
      }
    }
    return lines;
  }

  private static String findNextMethod(Iterator<String> lines) {
    String prefix = "public static Solid ";
    String suffix = "() {";
    while (lines.hasNext()) {
      String line = lines.next();
      if (line.contains(prefix)) {
        assertThat(line.endsWith(suffix));
        return line.substring(prefix.length(), line.length() - suffix.length());
      }
    }
    return "";
  }

  private static List<String> getCode(Iterator<String> it) {
    String prefix = "return ";
    String firstLine = it.next();
    assertThat(firstLine.startsWith(prefix));
    ArrayList<String> result = new ArrayList<>();
    result.add(firstLine.substring(prefix.length()));
    if (firstLine.endsWith(";")) {
      return result;
    }
    while (it.hasNext()) {
      String line = it.next();
      result.add("  " + line);
      if (line.endsWith(";")) {
        return result;
      }
    }
    throw new RuntimeException("Return statement without matching ';'");
  }

  private static void writeIntroduction(BufferedWriter writer, List<Example> examples)
      throws IOException {
    for (String line : linesOf("./src/doc/introduction.md")) {
      writer.write(line);
      writer.newLine();
    }
  }

  private static void writeExamples(BufferedWriter writer, List<Example> examples)
      throws IOException {
    for (Example example : examples) {
      writer.write(example.readableName());
      writer.write(" ([preview](./doc/" + example.name + ".stl))\n\n");
      writer.write("```\n");
      for (String codeLine : example.code) {
        writer.write(codeLine);
        writer.newLine();
      }
      writer.write("```\n\n");
    }
  }

  private static void assertThat(boolean condition) {
    if (!condition) {
      throw new RuntimeException();
    }
  }

  private static class Example {
    public String name;
    public List<String> code;

    public String readableName() {
      StringBuilder builder = new StringBuilder();
      for (Character character : name.toCharArray()) {
        if (isUpperCase(character)) {
          builder.append(" ");
          builder.append(toLowerCase(character));
        } else {
          builder.append(character);
        }
      }
      return builder.toString();
    }
  }

  private static void writeStls() throws IOException {
    for (Method method : Examples.class.getDeclaredMethods()) {
      if (Modifier.isPublic(method.getModifiers())) {
        try {
          save((Solid) method.invoke(null), method.getName());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private static void save(Solid cuboid, String name) throws IOException {
    new File(STLS_DIR).mkdirs();
    Path path = Paths.get(STLS_DIR + name + ".stl");
    Stl.toStl(cuboid, path);
  }
}
