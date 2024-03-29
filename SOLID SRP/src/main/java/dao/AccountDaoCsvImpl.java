package main.java.dao;

import main.java.model.Account;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDaoCsvImpl implements AccountDao {

    private static final String FILE_NAME = "src/main/resources/database.csv";
    private static final String OUTPUT_FILE_NAME = "src/main/resources/output.csv";

    @Override
    public void createReport() {
        List<String> accounts = readFileContents();
        try {
            Files.write(Path.of(OUTPUT_FILE_NAME), accounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getAccount(String accountNumber) {
        List<String> accounts = readFileContents();
        return accounts.stream()
                .map(this::getFromCsvRow)
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst().get();
    }

    @Override
    public void update(Account updatedAccount) {
        List<String> lines = readFileContents();
        List<String> updatedLines = updateAccountInLines(lines, updatedAccount);
        writeFileContents(updatedLines);
    }

    private Account getFromCsvRow(String line) {
        String[] fields = line.split(",");
        Account account = new Account();
        account.setAccountNumber(fields[0]);
        account.setAmount(new BigDecimal(fields[1]));
        account.setType(Account.Type.valueOf(fields[2]));
        return account;
    }

    @Override
    public void deleteAccount(Account account) {

    }

    private List<String> readFileContents() {
        try {
            return Files.readAllLines(Path.of(FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + FILE_NAME, e);
        }
    }

    private List<String> updateAccountInLines(List<String> lines, Account updatedAccount) {
        return lines.stream()
                .map(line -> updateLineIfAccountMatches(line, updatedAccount))
                .collect(Collectors.toList());
    }

    private String updateLineIfAccountMatches(String line, Account updatedAccount) {
        Account account = getFromCsvRow(line);
        if (account.getAccountNumber().equals(updatedAccount.getAccountNumber())) {
            return formatAccountAsCsvRow(updatedAccount);
        }
        return line;
    }

    private String formatAccountAsCsvRow(Account account) {
        return String.join(",", account.getAccountNumber(),
                String.valueOf(account.getAmount()),
                account.getType().toString());
    }

    private void writeFileContents(List<String> updatedLines) {
        try {
            Files.write(Path.of(FILE_NAME), updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + OUTPUT_FILE_NAME,
                    e);
        }
    }
}
