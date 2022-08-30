package tests.form;

import org.testng.annotations.Test;
import test_flows.form.FormFlow;
import tests.BaseTest;

public class FormTest extends BaseTest {

    @Test
    public void testFormFlow() {
        FormFlow formFlow = new FormFlow(getDriver());
        formFlow.gotoFormScreen();
        formFlow.fillTheForm();
        formFlow.verifyFormDisplay();
    }
}
