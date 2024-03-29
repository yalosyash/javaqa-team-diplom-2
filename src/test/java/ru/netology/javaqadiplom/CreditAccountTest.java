package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // Тесты на конструктор CreditAccount ------------------------------------------------------------------------------
    @Test
    public void shouldCreateAccount() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        int[] expected = {0, 5_000, 15};
        int[] actual = {account.getBalance(), account.getCreditLimit(), account.getRate()};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotCreateNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-5_000, 5_000, 15);
        });
    }

    @Test
    public void shouldNotCreateNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5_000, -5_000, 15);
        });
    }

    @Test
    public void shouldNotCreateNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, 5_000, -15);
        });
    }

    // Тесты на метод pay ----------------------------------------------------------------------------------------------
    @Test
    public void shouldPay() {
        CreditAccount account = new CreditAccount(5_000, 5_000, 15);
        account.pay(3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayAnyTimes() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 15);
        account.pay(3_000);
        account.pay(3_000);
        account.pay(3_000);
        account.pay(3_000);
        account.pay(3_000);
        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void shouldPayIfBalanceWillBeUnderZero() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 15);
        Assertions.assertEquals(false, account.pay(3_000));
    }

    @Test
    public void shouldNotChangeBalanceIfAmountLessThanCreditAndBalance() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 15);
        account.pay(3_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountLessThanCreditAndBalance() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 15);
        Assertions.assertEquals(false, account.pay(3_000));
    }

    // Тесты на метод add ----------------------------------------------------------------------------------------------
    @Test
    public void shouldAddMoneyOnBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddMoneyAnyTimes() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(3_000);
        account.add(3_000);
        account.add(3_000);
        Assertions.assertEquals(9_000, account.getBalance());
    }

    @Test
    public void shouldAddNullOnBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(0);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotAddUnderZeroOnBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(false, account.add(-1_000));
    }

    // Тесты на метод yearChange ---------------------------------------------------------------------------------------
    @Test
    public void shouldReckonPercentIfBalanceIsNegative() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(200);
        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldNotReckonPercentIfBalanceIsPositive() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }
}