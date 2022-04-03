//package top.kret11.day5;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
 
public class Main {
 
  public static void main(String[] args) throws IOException {
    if (args == null || args.length == 0) {
      throw new RuntimeException("Need to provide file path");
    }
    String filePath = args[0];
    BufferedReader reader = Files.newBufferedReader(Path.of(filePath));
    List<String> lines = reader.lines().toList();
    int[][] array = new int[1000][1000];
    for (String line : lines) {
      String[] parts = line.split(" -> ");
      int[] leftParts = Stream.of(parts[0].split(",")).mapToInt(Integer::valueOf).toArray();
      int[] rightParts = Stream.of(parts[1].split(",")).mapToInt(Integer::valueOf).toArray();
      int x1 = leftParts[0];
      int x2 = rightParts[0];
      int y1 = leftParts[1];
      int y2 = rightParts[1];
 
      if (x1 == x2) {
        int from = Math.min(y1, y2);
        int to = Math.max(y1, y2);
        IntStream.rangeClosed(from, to).forEach(i -> array[x1][i]++);
      } else if (y1 == y2) {
        int from = Math.min(x1, x2);
        int to = Math.max(x1, x2);
        IntStream.rangeClosed(from, to).forEach(i -> array[i][y1]++);
      } else {
        int dx = Math.abs(x1 - x2);
        AtomicInteger i = new AtomicInteger(x1);
        AtomicInteger j = new AtomicInteger(y1);
        Function<AtomicInteger, Integer> yIterator = y1 > y2 ? decrement : increment;
        Function<AtomicInteger, Integer> xIterator = x1 > x2 ? decrement : increment;
        for (int k = 0; k <= dx; k++) {
          array[xIterator.apply(i)][yIterator.apply(j)]++;
        }
      }
    }
    int count = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (array[i][j] > 1) {
          count++;
        }
      }
    }
    System.out.println(count);
  }
 
  private static Function<AtomicInteger, Integer> increment = i -> i.getAndIncrement();
  private static Function<AtomicInteger, Integer> decrement = i -> i.getAndDecrement();
 
}
 