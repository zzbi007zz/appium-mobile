package test_data;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static <T> T  buildDataObject(String filePath, Class<T> datatype){
        T returnedData;

        String absoluteFilePath = System.getProperty("user.dir").concat(filePath);
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath));
                )
        {
            Gson gson = new Gson();
            returnedData = gson.fromJson(reader, datatype);

        }catch (NoSuchFileException noSuchFileException) {
            throw new RuntimeException("[ERR] can't locate the file: ".concat(absoluteFilePath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return returnedData;
    }

}
