package com.sunny.programmingChallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Compare if two files have the same content
 */
public class FileContentComparision {

    public static void main(String[] args) {
        /** load file from resources or args[] */
        FileContentComparision fileContentComparision = new FileContentComparision();

        File file1 = null;
        File file2 = null;

//        file1 = new File(fileContentComparision.getClass().getClassLoader().getResource("img1.JPG").getFile());
//        file2 = new File(fileContentComparision.getClass().getClassLoader().getResource("img2.JPG").getFile());

        if (fileContentComparision.sameContent(file1, file2)) {
            System.out.println("Same content");
        } else {
            System.out.println("Different content");
        }
    }

    /**
     * A simple comparision of size, date creation/modified does not give us fool proof method of comparing the
     * actual content of files. The most effective way is to examine and compare the content.
     *
     * Since size of the files to compare may be larger than what can be loaded in memory, streaming approach is better.
     * Files.readAllBytes() may run into OutOfMemoryError for larger files.
     *
     * Input stream reader only reads a character at a time, using a buffered reader around it will be faster.
     *
     * Switch out IllegalArgumentException with checked exception if it is expected that the caller would want
     * to catch and handle the error
     *
     * Approach:
     *  Open file streams to both, buffer characters and then compare lines.
     *
     * @param file1 file to compare
     * @param file2 file to compare against
     * @return true if files have exact content, false otherwise
     * @throws IllegalArgumentException runtime exception can be exchanged for checked exception
     */
    public boolean sameContent(File file1, File file2) throws IllegalArgumentException {
        if (file1 == null || file2 == null) {
            throw new IllegalArgumentException("Exception: Input files cannot be null");
        }
        /** try with resources ensure that resources are closed after program completion */
        try (
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)))) {

            String file1Line = bufferedReader1.readLine();
            String file2Line = bufferedReader2.readLine();
            while (file1Line != null || file2Line != null) {
                if (!file1Line.equals(file2Line)) {
                    return false;
                }
                file1Line = bufferedReader1.readLine();
                file2Line = bufferedReader2.readLine();
            }
            /** if one file has reached EOF but not the other, they are not equal */
            if ((file1Line == null && file2Line != null) || (file1Line != null && file2Line == null)) {
                return false;
            }

            return true;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Exception: file not found => " + e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception: unable to open file => " + e.getMessage());
        }
    }
}
