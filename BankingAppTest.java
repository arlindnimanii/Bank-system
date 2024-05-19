import com.example.bnaka20242.BankingApp;
import org.junit.Assert;
import org.junit.Test;

public class BankingAppTest {

    @Test
    public void testUserCreation() {
        // Test data for user creation
        String bankName = "Test Bank";
        double flatFee = 10.0;
        double percentFee = 0.5;
        String accNo = "123456";
        String accType = "Savings";
        String name = "John Doe";
        double initialBalance = 1000.0;

        // Create a BankingApp instance
        BankingApp bankingApp = new BankingApp();

        // Call the user creation method with test data
        boolean userCreated = bankingApp.createUser(bankName, flatFee, percentFee, accNo, accType, name, initialBalance);

        // Assertion to check if the user was created successfully
        Assert.assertTrue(userCreated);
    }
}
