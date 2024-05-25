
package com.compass.utils;

import com.compass.domain.exceptions.NoItemsRegisteredException;

import java.util.Map;
import java.util.Scanner;

public class LeitorDeDados {

    private static final Scanner scanner = new Scanner(System.in);

    public static int lerIntInterval(String errorMsg, int min, int max) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.err.print("Valor fora do intervalo permitido! " + errorMsg);
                }
            } catch (NumberFormatException e) {
                System.err.print(errorMsg);
            }
        }
        return value;
    }

    public static Long lerLongInterval(String errorMsg, long min, long max) {
        Long value;
        while (true) {
            try {
                value = Long.parseLong(scanner.nextLine());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.err.print("Valor fora do intervalo permitido! " + errorMsg);
                }
            } catch (NumberFormatException e) {
                System.err.print(errorMsg);
            }
        }
        return value;
    }

    public static int lerInt(String errorMsg) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.print(errorMsg);
            }
        }
        return value;
    }

    public static <T> T selecionarItem(String mensagem, String itemType, Map<Integer, T> items) throws NoItemsRegisteredException {
        if (items.isEmpty()) {
            throw new NoItemsRegisteredException(itemType);
        }
        System.out.println(mensagem);
        imprimirMap(items);
        System.out.print("Selecione o item: ");
        T item;
        do {
            int id = lerInt("ID invalido:");
            item = items.get(id);
        } while (item == null);

        return item;
    }

    public static Long lerLong(String errorMsg) {
        Long value;
        while (true) {
            try {
                value = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.print(errorMsg);
            }
        }
        return value;
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem + ": ");
        return scanner.nextLine();
    }

    public static <K, V> void imprimirMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void close() {
        scanner.close();
    }

}
