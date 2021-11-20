package urbn8.apiengine;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import urbn8.object.Manuafacture;
import urbn8.object.ManuafactureDetail;
import urbn8.route.ManufactureRoute;

import java.util.Locale;


public class ManuafactureEngine extends BaseAPI{
    private Gson gson = new Gson();

    public Response getAllManuafacture(){
        return requestSpec()
                .queryParam("ManufacturerType", "Intermediate")
                .queryParam("format", "json")
                .get(ManufactureRoute.ALL_MANUFACTURE);
    }

    private JsonObject responeToJsonObject(Response response){
        String respon = response.asString();
        return gson.fromJson(respon, JsonObject.class);
    }

    public boolean isManuafactureExist(String manuafactureName){
        int count = 0;
        JsonObject jsonObject = responeToJsonObject(getAllManuafacture());
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        for(int i=0; i< resultArray.size(); i++){
             JsonElement name = resultArray.get(i).getAsJsonObject().get("Mfr_CommonName");
            if(!name.isJsonNull() && name.getAsString().equals(manuafactureName)){
                count ++;
            }
        }
        return (count == 1 ) ? true : false;
    }

    public Manuafacture saveManuafactureFromListByName(Response response, String manuafactureName){
        JsonObject jsonObject = responeToJsonObject(response);
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        Manuafacture manuafacture = new Manuafacture();
        for(int i=0; i< resultArray.size(); i++){
            JsonElement name = resultArray.get(i).getAsJsonObject().get("Mfr_CommonName");
            if(!name.isJsonNull() && name.getAsString().equals(manuafactureName)){
                manuafacture.setCountry(resultArray.get(i).getAsJsonObject().get("Country").getAsString());
                manuafacture.setMfr_CommonName(resultArray.get(i).getAsJsonObject().get("Mfr_CommonName").getAsString());
                manuafacture.setMfr_ID(resultArray.get(i).getAsJsonObject().get("Mfr_ID").getAsString());
                manuafacture.setMfr_Name(resultArray.get(i).getAsJsonObject().get("Mfr_Name").getAsString());
                manuafacture.setVehicleTypes(resultArray.get(i).getAsJsonObject().get("VehicleTypes").getAsJsonArray());
            }
        }
        return manuafacture;
    }

    public boolean isTotalManuafacturs(int total) {
        JsonObject jsonObject = responeToJsonObject(getAllManuafacture());
        int count = jsonObject.getAsJsonObject().get("Count").getAsInt();
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        return (count == resultArray.size() && count == total) ? true : false;
    }

    public Response getManuafactureDetailByName(String manuafacturename) {
        return requestSpec()
                .queryParam("format", "json")
                .pathParam("manufacturer_name", manuafacturename)
                .get(ManufactureRoute.MANUFACTURE_DETAIL);
    }

    public String getResponeMessage(Response response){
        JsonObject jsonObject = responeToJsonObject(response);
        return jsonObject.getAsJsonObject().get("Message").getAsString();
    }

    public boolean isNameOfDetailListSame(Response response, String name) {
        boolean check = true;
        JsonObject jsonObject = responeToJsonObject(response);
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        for (int i=0; i<resultArray.size(); i++){
            if(!resultArray.get(i).getAsJsonObject().get("Mfr_Name").getAsString().toLowerCase(Locale.ROOT)
                    .contains(name.toLowerCase(Locale.ROOT))){
                check = false;
                break;
            }
        }
        return check;
    }

    public Response getManuafactureByID(String id) {
        return requestSpec()
                .queryParam("format", "json")
                .pathParam("manufacturer_id", id)
                .get(ManufactureRoute.MANUFACTURE_ID);
    }

    public boolean isFindOneManuafactureById(Response response) {
        JsonObject jsonObject = responeToJsonObject(response);
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        return (resultArray.size() == 1)? true : false;
    }

    public ManuafactureDetail getManuafactureDetail(Response response){
        ManuafactureDetail manuafactureDetail = new ManuafactureDetail();
        JsonObject jsonObject = responeToJsonObject(response);
        JsonArray resultArray = jsonObject.getAsJsonObject().get("Results").getAsJsonArray();
        JsonObject jsonElement = resultArray.get(0).getAsJsonObject();
        manuafactureDetail.setCountry(jsonElement.get("Country").getAsString());
        manuafactureDetail.setAddress(jsonElement.get("Address").getAsString());
        manuafactureDetail.setMfr_CommonName(jsonElement.get("Mfr_CommonName").getAsString());
        manuafactureDetail.setMfr_Name(jsonElement.get("Mfr_Name").getAsString());
        manuafactureDetail.setVehicleTypes(jsonElement.get("VehicleTypes").getAsJsonArray());
        return manuafactureDetail;
    }
}
