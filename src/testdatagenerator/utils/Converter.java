package testdatagenerator.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @projectName TestDataGenerator
 * @package testdatagenerator.utils
 * @filename Utils.java
 * @encoding UTF-8
 * @author Ernesto Moyano
 * @date 10/09/2014 22:58:27
 */
public class Converter {

    private int iterations = 10;
    public static final String PROP_ITERATIONS = "iterations";
    private String path;
    public static final String PROP_PATH = "path";
    private int startIndex = 0;
    public static final String PROP_STARTINDEX = "startIndex";
    private int endIndex = 0;
    public static final String PROP_ENDINDEX = "endIndex";

    /**
     * Get the value of endIndex
     *
     * @return the value of endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Set the value of endIndex
     *
     * @param endIndex new value of endIndex
     */
    public void setEndIndex(int endIndex) {
        int oldEndIndex = this.endIndex;
        this.endIndex = endIndex;
        propertyChangeSupport.firePropertyChange(PROP_ENDINDEX, oldEndIndex, endIndex);
    }

    /**
     * Get the value of startIndex
     *
     * @return the value of startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Set the value of startIndex
     *
     * @param startIndex new value of startIndex
     */
    public void setStartIndex(int startIndex) {
        int oldStartIndex = this.startIndex;
        this.startIndex = startIndex;
        propertyChangeSupport.firePropertyChange(PROP_STARTINDEX, oldStartIndex, startIndex);
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        String oldPath = this.path;
        this.path = path;
        propertyChangeSupport.firePropertyChange(PROP_PATH, oldPath, path);
    }

    /**
     * Get the value of iterations
     *
     * @return the value of iterations
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Set the value of iterations
     *
     * @param iterations new value of iterations
     */
    public void setIterations(int iterations) {
        int oldIterations = this.iterations;
        this.iterations = iterations;
        propertyChangeSupport.firePropertyChange(PROP_ITERATIONS, oldIterations, iterations);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void fixedLenght() {

        if (iterations < 10000000) {
            BufferedReader buffer = null;

            try {

                String inputLine;

                buffer = new BufferedReader(new FileReader(path));

                inputLine = buffer.readLine();
                try (FileWriter fileWriter = new FileWriter("output.txt")) {

                    int id = 10000000;

                    String replace;

                    for (int i = 0; i < iterations; i++) {

                        StringBuffer original = new StringBuffer(inputLine);

                        replace = id + "";

                        original.replace(startIndex, endIndex, replace);

                        fileWriter.write(original + System.getProperty("line.separator"));

                        id++;

                    }
                }
                JOptionPane.showMessageDialog(null, "Done");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maximun Number of rows reduce the iterations");
        }

    }

}
