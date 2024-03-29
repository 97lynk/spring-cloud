package io.a97lynk.accountservice.servive;

import io.a97lynk.accountservice.model.Account;
import io.a97lynk.accountservice.model.Profile;

import java.util.List;
import java.util.NoSuchElementException;

public interface AccountService {

    // CRUD với profile

    List<Profile> selectProfiles();

    Profile selectProfileByUserName(String userName);

    Profile selectProfileById(int id);

    Profile updateProfile(Profile profile) throws NoSuchElementException;

    Profile insertProfile(Profile profile);

    void deleteProfile(Integer id) throws NoSuchElementException;

    /// CRUD với account

    List<Account> selectAccounts();

    Account selectAccountByUserName(String userName);

    Account selectAccountById(int id);

    Account updateAccount(Account account) throws NoSuchElementException;

    Account insertAccount(Account account);

    void deleteAccount(Integer id) throws NoSuchElementException;

    boolean existAccount(String userName);
}
