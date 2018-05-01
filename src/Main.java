import controller.LzwDecompressor;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String fileInputPath = JOptionPane.showInputDialog("File Input Path:");
        String fileOutputPath = "src\\examples\\resultDecoder";

        new LzwDecompressor(fileInputPath, fileOutputPath).run();

    }
}
