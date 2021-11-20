package urbn8.object;

import com.google.gson.JsonArray;
import lombok.Data;

@Data
public class ManuafactureDetail {
    private String Address;
    private String Address2;
    private String City;
    private String ContactEmail;
    private String ContactFax;
    private String ContactPhone;
    private String Country;
    private String DBAs;
    private JsonArray EquipmentItems;
    private String LastUpdated;
    private JsonArray ManufacturerTypes;
    private String Mfr_CommonName;
    private String Mfr_ID;
    private String Mfr_Name;
    private String OtherManufacturerDetails;
    private String PostalCode;
    private String PrimaryProduct;
    private String PrincipalFirstName;
    private String PrincipalLastName;
    private String PrincipalPosition;
    private String StateProvince;
    private String SubmittedName;
    private String SubmittedOn;
    private String SubmittedPosition;
    private JsonArray VehicleTypes;

}
