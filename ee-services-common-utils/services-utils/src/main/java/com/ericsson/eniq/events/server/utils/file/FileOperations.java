/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.file;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Utility class to handle file operations 
 * 
 * 
 * @author eemecoy
 *
 */
@Stateless
@Local
public class FileOperations {

    /**
     * Read contents of all files matching the provided fileExtension in the folder pathToFiles
     * The path of files must be a fully qualified folder name     
     * Each line in each file is an entry in the list of strings returned
     * 
     * Note any empty lines in the files will be ignored
     * 
     * @param pathToFiles  path to the parent folder for files
     * @param fileExtension file extension to filter on
     * @return contents of all files matching fileExtension in pathToFiles
     */
    public List<String> readFiles(final String pathToFiles, final String fileExtension) throws IOException {
        final List<String> filesInFolderMatchingExtension = getFilesMatchingFilter(pathToFiles, fileExtension);
        final List<String> allFileContents = new ArrayList<String>();
        for (final String fileName : filesInFolderMatchingExtension) {
            final InputStream inputStream = this.getClass().getResourceAsStream(PATH_SEPARATOR + fileName);
            final List<String> fileContents = readInputStream(inputStream);
            allFileContents.addAll(fileContents);
        }
        return allFileContents;

    }

    private List<String> getFilesMatchingFilter(final String pathToFiles, final String fileExtension) {
        final String[] files = getFilesInPath(pathToFiles);
        final List<String> filesMatchingFilter = new ArrayList<String>();

        for (final String fileName : files) {
            if (fileName.endsWith(fileExtension)) {
                filesMatchingFilter.add(fileName);
            }
        }
        return filesMatchingFilter;
    }

    /**
     * Get list of files in pathToFiles
     * Has protected in order to use from test class
     * 
     * @param pathToFiles
     * @return list of files
     */
    protected String[] getFilesInPath(final String pathToFiles) {
        return getFilesInFolder(pathToFiles);
    }

    /**
     * Get list of files in the folder pathToFiles
     * Has protected in order to use from test class
     * 
     * @param folderName
     * @return list of files
     */
    protected String[] getFilesInFolder(final String folderName) {
        final File folder = createFileObject(folderName);
        final String[] files = folder.list();
        if (files == null) {
            ServicesLogger.detailed(this.getClass().getName(), "getFilesMatchingFilter", "No files found  in folder "
                    + folderName);
            return new String[] {};
        }
        return files;
    }

    File createFileObject(final String folderName) {
        return new File(folderName);
    }

    /**
     * Get the path to the parent folder of a given file name
     * This method uses the classloader to determine the fully qualified name
     * of the given file name, and the parent folder name is extracted from that 
     * 
     * @param fileName          name of file
     * @return fully qualified path to parent folder of given file
     */
    public String getPathToParentFolder(final String fileName) {
        final String urlPath = getUrlPath(fileName);
        final int endIndex = urlPath.lastIndexOf(PATH_SEPARATOR);
        return urlPath.substring(0, endIndex);
    }

    String getUrlPath(final String fileName) {
        final URL url = this.getClass().getResource(fileName);
        try {
			return URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
            ServicesLogger.detailed(this.getClass().getName(), "getUrlPath", "Invalid character found in UrlPath "
                    + e.getMessage());
		};
		return url.getPath();
    }

    private List<String> readInputStream(final InputStream fileInputStream) throws IOException {

        final List<String> fileContents = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = createBufferedReader(fileInputStream);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    fileContents.add(line);
                }
            }

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return fileContents;
    }

    BufferedReader createBufferedReader(final InputStream fileInputStream) {
        return new BufferedReader(new InputStreamReader(fileInputStream));
    }

}
