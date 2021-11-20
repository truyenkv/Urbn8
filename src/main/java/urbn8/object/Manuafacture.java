package urbn8.object;

import com.google.gson.JsonArray;
import lombok.Data;

@Data
public class Manuafacture {
    private String Country;
    private String Mfr_CommonName;
    private String Mfr_ID;
    private String Mfr_Name;
    private JsonArray VehicleTypes;
}
