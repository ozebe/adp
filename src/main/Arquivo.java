
package main;


import static main.Ui.barraProgresso;
import static out.transparent.error.ErrorOutJOptions.error01Transparent32px;


/**
 *
 * @author Wesley
 */
class FileProcessor implements Runnable {
//modificar
    public void run() {
        processFile();
    }

    public void processFile() {
        barraProgresso.setVisible(true);
        barraProgresso.setIndeterminate(false);
        barraProgresso.setVisible(false);
        }
    }

