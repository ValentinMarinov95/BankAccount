import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String CREATE_COMMAD = "Create";
        final String DEPOSIT_COMMAND = "Deposit";
        final String SET_INTEREST_COMMAND = "SetInterest";
        final String GET_INTEREST_COMMAND = "GetInterest";
        final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist";
        Scanner scanner = new Scanner(System.in);

        Map<Integer, BankAccount> allAccounts = new HashMap<>();

        String[] input = scanner.nextLine().split(" ");
        String command = input[0];
        while (!command.equals("End")){
            String output = null;

            switch (command){
                case CREATE_COMMAD:
                    BankAccount account = new BankAccount();
                    allAccounts.put(account.getId(), account);
                    output = String.format("Account ID%d created", account.getId());
                    break;
                case DEPOSIT_COMMAND: {
                    int accountId = Integer.parseInt(input[1]);
                    double amount = Double.parseDouble(input[2]);

                    BankAccount current = allAccounts.get(accountId);
                    if (current == null) {
                        output = ACCOUNT_DOES_NOT_EXIST;
                    } else {
                        current.deposit(amount);
                        allAccounts.put(accountId, current);
                        output = String.format("Deposited %.0f to ID%d", amount, accountId);
                    }
                }
                    break;
                case SET_INTEREST_COMMAND:
                    double interest = Double.parseDouble(input[1]);
                    BankAccount.setInterestRate(interest);
                    break;
                case GET_INTEREST_COMMAND:
                    int accountId = Integer.parseInt(input[1]);
                    int numberOfYears = Integer.parseInt(input[2]);
                    BankAccount current = allAccounts.get(accountId);
                    if (current == null) {
                        output = ACCOUNT_DOES_NOT_EXIST;
                    } else {
                        output = String.format("%.2f", current.getProfitAfterYears(numberOfYears));
                        allAccounts.put(accountId, current);
                    }
                    break;

            }
            if (output != null){
                System.out.println(output);
            }
            System.out.println(output);
            input = scanner.nextLine().split(" ");
            command = input[0];
        }
    }
}
