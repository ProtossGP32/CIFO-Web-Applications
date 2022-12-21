package org.labSe01Part1;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {
    private static AccountManager instance;
    private HashMap<Person, ArrayList<Account>> clients;
    private AccountManager() {
        this.clients = new HashMap<>();
        // Empty constructor?
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    // clearAccounts only for testing purposes
    public void clearAccounts() {
        this.clients = new HashMap<>();
    }

    // Private methods: any method that requires Person or Account objects as input parameters
    private boolean createAccount(Person client, Account newAccount) {
        if (!this.clients.containsKey(client)) {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(newAccount);
            this.clients.put(client, accounts);
            return true;
        }
        else if (!this.clients.get(client).contains(newAccount)) {
            this.clients.get(client).add(newAccount);
            return true;
        }
        return false;
    }

    private boolean removeAccount(Person client, Account accountToDelete) {
        if (this.clients.containsKey(client) && this.clients.get(client).contains(accountToDelete)) {
            this.clients.get(client).remove(accountToDelete);
            System.out.println("Account " + accountToDelete.getAccountNumber() + " removed from the system.");
            return true;
        }
        return false;
    }

    private boolean removeClient(Person client) {
        if (this.clients.containsKey(client)) {
            this.clients.remove(client);
            System.out.println("Client " + client.getName() + " removed from the system.");
            return true;
        }
        return false;
    }

    private ArrayList<Account> getAccounts(Person client) {
        return this.clients.getOrDefault(client, null);
    }

    private Person getClient(String clientName) {
        for (Person client : this.clients.keySet()) {
            if (client.getName().equals(clientName)) {
                return client;
            }
        }
        return null;
    }

    private Account getAccount(Person client, String accountNumber) {
        if (this.clients.containsKey(client)) {
            for (Account account : this.clients.get(client)) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    private double getClientBalance(Person client) {
        double totalBalance = 0.0;
        if (this.clients.containsKey(client)) {
            for (Account account : this.clients.get(client)) {
                totalBalance += account.getBalance();
            }
        }
        return totalBalance;
    }

    private boolean withdrawal(Account account, double amount) {
        // Check if there's enough money to withdraw
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal completed. New balance: " + account.getBalance());
            return true;
        }
        // Not enough money to withdraw
        System.out.println("Not enough money on client's account. Withdrawal operation cancelled.");
        return false;
    }

    private boolean transfer(Account senderAccount, Account receiverAccount, double amount) {
        // Check if we can transfer the money
        if (amount > 0 && amount <= senderAccount.getBalance()) {
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            System.out.println("Transfer completed.");
            System.out.println("New sender balance: " + senderAccount.getBalance());
            System.out.println("New receiver balance: " + receiverAccount.getBalance());
            return true;
        }
        // Not enough money for the transfer
        System.out.println("Not enough money on sender's account. Transfer operation cancelled.");
        return false;
    }

    private boolean deposit(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit completed. New balance: " + account.getBalance());
            return true;
        }
        System.out.println("Bad amount of money. Deposit cancelled.");
        return false;
    }

    private boolean changePin(Account account, String oldPin, String newPin) {
        if (account.getPin().equals(oldPin)) {
            account.setPin(newPin);
            System.out.println("Pin correctly changed.");
            return true;
        }
        return false;
    }

    // Public methods: only basic data types accepted, no Person or Account
    public boolean createAccount(String clientName, String clientAddress, int age, String accountNumber, String accountPin, double balance) {
        return this.createAccount(new Person(clientName, clientAddress, age), new Account(accountNumber, accountPin, balance));
    }
    public boolean createAccount(String clientName, String clientAddress, int age, String accountNumber, String accountPin) {
        return this.createAccount(clientName, clientAddress, age, accountNumber, accountPin, 0.0);
    }

    public boolean removeAccount(String clientName, String accountNumber) {
        Person client = this.getClient(clientName);
        if (client == null) {
            return false;
        }
        Account account = this.getAccount(client, accountNumber);
        if (account == null) {
            return false;
        }
        return this.removeAccount(client, account);
    }

    public boolean removeClient(String clientName) {
        Person client = this.getClient(clientName);
        if (client == null) {
            return false;
        }

        return this.removeClient(client);
    }

    public Account getAccount(String clientName, String accountNumber) {
        Person client = this.getClient(clientName);
        if (client == null) {
            return null;
        }
        return this.getAccount(client, accountNumber);
    }

    public double getAccountBalance(String clientName, String accountNumber) {
        Account account = this.getAccount(clientName, accountNumber);
        if (account == null) {
            return 0.0;
        }
        return account.getBalance();
    }

    public double getClientBalance(String clientName) {
        Person client = this.getClient(clientName);
        if (client == null) {
            System.out.println("Client " + clientName + " does not exist.");
            return 0.0;
        }
        return this.getClientBalance(client);
    }

    public boolean withdrawal(String clientName, String accountNumber, double amount) {
        // Retrieve the account from that client
        Account account = this.getAccount(clientName, accountNumber);
        if (account == null) {
            System.out.println("Client " + clientName + "doesn't have an account with number " + accountNumber +". Withdrawal operation cancelled.");
            return false;
        }

        return this.withdrawal(account, amount);
    }

    public boolean transfer(String senderName, String senderAccountNumber, String receiverName, String receiverAccountNumber, double amount) {
        // Retrieve the sender account
        Account senderAccount = this.getAccount(senderName, senderAccountNumber);
        if (senderAccount == null) {
            System.out.println("Client " + senderName + "doesn't have an account with number " + senderAccountNumber + ". Transfer operation cancelled.");
            return false;
        }
        // Retrieve the receiver account
        Account receiverAccount = this.getAccount(receiverName, receiverAccountNumber);
        if (receiverAccount == null) {
            System.out.println("Client " + senderName + "doesn't have an account with number " + senderAccountNumber + ". Transfer operation cancelled.");
            return false;
        }

        return this.transfer(senderAccount, receiverAccount, amount);
    }

    public boolean deposit(String clientName, String accountNumber, double amount){
        Account clientAccount = this.getAccount(clientName, accountNumber);
        if (clientAccount == null) {
            System.out.println("Client " + clientName + " doesn't have an account with number " + accountNumber + ". Deposit operation cancelled.");
            return false;
        }

        return this.deposit(clientAccount, amount);
    }

    public boolean changePin(String clientName, String accountNumber, String oldPin, String newPin) {
        Account account = this.getAccount(clientName, accountNumber);
        if (account == null) {
            return false;
        }
        return this.changePin(account, oldPin, newPin);
    }
}
