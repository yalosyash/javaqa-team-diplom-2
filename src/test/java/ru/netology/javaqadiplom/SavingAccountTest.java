package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // Пополнение баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000 + 3_000, actual);
    }

    @Test
    public void emptyReplenishment() { // пустое поступление средств
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }

    @Test
    public void overMaxBalanceReplenishment() { // Пополнение средств сверх максимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_000);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }

    @Test
    public void testNegativeRate() { // сообщение о негативной ставке

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    0,
                    10_000,
                    -5);
        });
    }

    @Test
    public void testInitialBalanceLessMin() { // сообщение о начальном балансе ниже минимального баланса

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void testInitialOverMaxBalance() { // сообщение о начальном баласне выше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    5_000,
                    500,
                    1_000,
                    5);
        });
    }

    @Test
    public void testMinOverMaxBalance() { // сообщение о минимальном баласне выше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    500,
                    5_000,
                    1_000,
                    5);
        });
    }

    @Test
    public void testNegativeMinBalance() { // сообщение о негативном минимальном баласне

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    500,
                    -5_000,
                    1_000,
                    5);
        });
    }

    @Test
    public void testNegativeInitialBalance() { // сообщение о негативном минимальном баласне

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    -500,
                    1_000,
                    5_000,
                    5);
        });
    }

    @Test
    public void testZeroMaxBalance() { // сообщение о нулевом максимальном балансе

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    0,
                    0,
                    0,
                    5);
        });
    }

    @Test
    public void testPayAmountNormal() { //Отработка метода выше минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);
        int actual = account.getBalance();

        Assertions.assertEquals(1_500, actual);
    }

    @Test
    public void testPayZero() { //Отработка метода, когда нет трат
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);
        int actual = account.getBalance();

        Assertions.assertEquals(2_000, actual);
    }

    @Test
    public void testPayAmountNegativeMinBalance() { // Отработка метода ниже минимального баланса
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(500));
    }

    @Test
    public void RateYearChange() { // Процент накоплений
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(2_000);

        Assertions.assertEquals(3_000 / 100 * 5, account.yearChange());
    }

    @Test
    public void testGetMinBalance() { //Вызов Минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int actual = account.getMinBalance();

        Assertions.assertEquals(1_000, actual);
    }

    @Test
    public void testGetMaxBalance() { //Вызов Минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int actual = account.getMaxBalance();

        Assertions.assertEquals(10_000, actual);
    }

    @Test
    public void shouldNotReckonPercentIfBalanceIsPositive() {
        SavingAccount account = new SavingAccount(99, 0, 5_000, 80);
        Assertions.assertEquals(79, account.yearChange());
    }
}