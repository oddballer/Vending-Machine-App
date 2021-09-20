package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    private File file;
    private LocalDateTime clock = LocalDateTime.now();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    private DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("MM_dd_yy_hh_mm_a");
    private String timeStamp = clock.format(format);
    private String salesReport = clock.format(fileFormat) + "_sales_report.txt";

    public LogWriter(String fileName) {
        this.file = new File(fileName);
    }

    public LogWriter() {
        String fileName = salesReport;
        this.file = new File(fileName);
    }

    PrintWriter printWriter;

    public void logTransaction(String lineOfText) {
        if (this.file.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                printWriter = new PrintWriter(fileOutputStream);
                printWriter.println(">" + this.timeStamp + lineOfText);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                printWriter = new PrintWriter(file);
                printWriter.println(">" + this.timeStamp + lineOfText);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        printWriter.flush();
        printWriter.close();
    }

    public void logSales(String lineOfText) {
        if (this.file.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                printWriter = new PrintWriter(fileOutputStream);
                printWriter.println(lineOfText);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                printWriter = new PrintWriter(file);
                printWriter.println(lineOfText);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        printWriter.flush();
        printWriter.close();
    }
}