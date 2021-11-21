package Urbn8.TestCase;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import urbn8.apiengine.ManuafactureEngine;
import urbn8.object.Manuafacture;
import urbn8.object.ManuafactureDetail;
import urbn8.util.Data;

import static org.assertj.core.api.Assertions.assertThat;


public class TestManuafactor {

    private ManuafactureEngine manuafactureEngine = new ManuafactureEngine();
    private Manuafacture manuafacture = null;
    private Response response;

    @Test(priority = 1)
    public void VerifyTheNumberOfManuafacturesReturnCorrect() {
        response = manuafactureEngine.getAllManuafacture();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(manuafactureEngine.getResponeMessage(response)).isEqualTo(Data.RESPONE_MESS);
        assertThat(manuafactureEngine.isTotalManuafacturs(100)).isTrue();
        manuafacture = manuafactureEngine.saveManuafactureFromListByName(response, Data.MANUAFACTURENAME);
    }

    @Test(priority = 2)
    public void VerifyTheOneManuafactureExistedInList()  {
        assertThat(manuafacture).isNotNull();
    }

    @Test(priority = 3)
    public void VerifyGetManuafactureDetailByName(){
        response = manuafactureEngine.getManuafactureDetailByName(manuafacture.getMfr_CommonName());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(manuafactureEngine.getResponeMessage(response)).isEqualTo(Data.RESPONE_MESS);
        boolean checkName = manuafactureEngine.isNameOfDetailListSame(response, manuafacture.getMfr_CommonName());
        assertThat(checkName).isTrue();
    }

    @Test(priority = 4)
    public void VerifyGetManuafactureByID(){
        response = manuafactureEngine.getManuafactureByID(manuafacture.getMfr_ID());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(manuafactureEngine.getResponeMessage(response)).isEqualTo(Data.RESPONE_MESS);
        boolean isOneItem = manuafactureEngine.isFindOneManuafactureById(response);
        assertThat(isOneItem).isTrue();
        ManuafactureDetail manuafactureDetail = manuafactureEngine.getManuafactureDetail(response);
        assertThat(manuafactureDetail)
                .extracting("Country", "Mfr_CommonName", "Mfr_Name", "VehicleTypes")
                .containsExactly(manuafacture.getCountry(), manuafacture.getMfr_CommonName(), manuafacture.getMfr_Name(), manuafacture.getVehicleTypes());
    }
}
