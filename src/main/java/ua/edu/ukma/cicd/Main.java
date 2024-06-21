package ua.edu.ukma.cicd;

import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;

public final class Main {
  private static final int SECURE_RANDOM_HASH_SIZE = 16;

  private Main() {
  }

  @SneakyThrows
  public static void main(final String[] args) {
    var message = "Hello hashing";

    System.out.println("Hashes with MessageDigest:");
    System.out.printf("MD5: %s%n", messageToHash(message, "MD5"));
    System.out.printf("SHA1: %s%n", messageToHash(message, "SHA-1"));
    System.out.printf("SHA256: %s%n%n", messageToHash(message, "SHA-256"));

    System.out.println("Hashes with SecureRandom:");
    System.out.printf("SHA1PRNG: %s%n", messageToHashWithSecureRandom(message, "SHA1PRNG"));
    System.out.printf("DRBG: %s%n", messageToHashWithSecureRandom(message, "DRBG"));
    // yields different hashes every time, no good
    System.out.printf("Windows-PRNG: %s%n%n", messageToHashWithSecureRandom(message, "Windows-PRNG"));

    var data = new Data(1, "Some data");
    System.out.println("Testing hashmap with correct equals() and hashCode():");
    testWithHashMap(data);

    var dataBuggy = new DataBuggy(1, "Some buggy data");
    System.out.println("Testing hashmap with faulty equals() and hashCode():");
    testWithHashMap(dataBuggy);
  }

  @SneakyThrows
  private static String messageToHash(final String message, final String algorithm) {
    var messageDigest = MessageDigest.getInstance(algorithm);

    messageDigest.update(message.getBytes());

    return bytesToHex(messageDigest.digest());
  }

  @SneakyThrows
  private static String messageToHashWithSecureRandom(final String message, final String algorithm) {
    var secureRandom = SecureRandom.getInstance(algorithm);
    var hex = new byte[SECURE_RANDOM_HASH_SIZE];

    secureRandom.setSeed(message.getBytes());
    secureRandom.nextBytes(hex);

    return bytesToHex(hex);
  }

  private static <T> void testWithHashMap(final T data) {
    var dataMap = new HashMap<T, Long>();
    dataMap.put(data, System.currentTimeMillis());
    dataMap.put(data, System.currentTimeMillis() + 1);

    System.out.printf("Map size after two inserts: %d%n", dataMap.size());
    dataMap.remove(data);
    System.out.printf("Map size after removal: %d%n", dataMap.size());
  }

  private static String bytesToHex(final byte[] bytes) {
    StringBuilder out = new StringBuilder();
    for (byte b : bytes) {
      out.append(String.format("%02X", b));
    }
    return out.toString();
  }
}