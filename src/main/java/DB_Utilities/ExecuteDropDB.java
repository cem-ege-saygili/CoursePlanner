package DB_Utilities;

import java.io.File;

public class ExecuteDropDB {

    public static void executeDropDB_ifExists(String dbLocation) {

        File file = new File(dbLocation);

        file.delete();

    }


}