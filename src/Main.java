//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nPilih operasi:");
            System.out.println("1. Write");
            System.out.println("2. Show");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Exit");
            System.out.print("Masukkan pilihan Anda: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Masukkan teks: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case 2:
                    editor.show();
                    break;
                case 3:
                    editor.undo();
                    break;
                case 4:
                    editor.redo();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan Text Editor!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}

class TextEditor {
    private StringBuilder currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    
    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    public void write(String text) {
        undoStack.push(currentText.toString());
        currentText.append(text);
        redoStack.clear();
    }
    
    public void show() {
        System.out.println("Isi Text Editor: " + currentText.toString());
    }
    
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }
    
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }
}
