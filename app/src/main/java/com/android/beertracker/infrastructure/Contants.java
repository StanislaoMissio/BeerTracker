package com.android.beertracker.infrastructure;

public class Contants {

    private Contants(){}

    public interface BeerTrackerAPI {
        String HOST = "localhost:5000/";
    }

    public static final class ErrorCodes {
        public static final String ERROR_PLACES_UNAVAILABLE = "10";
    }

    public static final class NetworkIntegrator {
        public static final String CONTENT_TYPE_JSON = "aplication/json";
        public static final int READ_TIMEOUT = 20000;
        public static final int CONNECT_TIMEOUT = 20000;
        public static final String METHOD_GET = "GET";
    }

    public interface Services {
        interface Tag{
            String COMMAND = "command";
            String RESULT_RECIEVER = "reciever";
            String ERROR_MESSAGE = "error message";
            String BULK_LIST_ESTILO = "bulkInsertCount";
        }

        interface Status {
            int FINISH = 1;
            int ERROR = 2;
        }

        interface SyncCommand {
            int PLACE_ALL = 0;
        }
    }

}