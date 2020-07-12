package config;

import java.io.IOException;
import java.util.logging.*;

public class CsvLogger {

    static Logger logger;
    public Handler fileHandler;
    Formatter plainText;

    private CsvLogger() throws IOException{
        //instance the logger
        logger = Logger.getLogger(CsvLogger.class.getName());
        //instance the filehandler
        fileHandler = new FileHandler("log.txt",true);
        //instance formatter, set formatting, and handler
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);


    }

    private static Logger getLogger(){
        if(logger == null){
            try {
                new CsvLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }
    public static void log(Level level, String msg){
        getLogger().log(level, msg);
        System.out.println(msg);
    }
}


