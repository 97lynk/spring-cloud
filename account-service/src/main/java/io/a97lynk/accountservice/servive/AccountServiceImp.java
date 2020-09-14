package com.kt3.accountservice.servive;

import com.kt3.accountservice.model.Account;
import com.kt3.accountservice.model.Profile;
import com.kt3.accountservice.reponsitory.AccountRepository;
import com.kt3.accountservice.reponsitory.ProfileReponsitory;
import com.kt3.accountservice.reponsitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileReponsitory profileReponsitory;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Profile> selectProfiles() {
        return profileReponsitory.findAll();
    }

    @Override
    public Profile selectProfileByUserName(String userName) {
        return accountRepository.findByUserName(userName).getProfile();
    }

    @Override
    public Profile selectProfileById(int id) {
        return profileReponsitory.findOne(id);
    }

    @Override
    public Profile insertProfile(Profile profile) {
        Profile newProfile = new Profile(profile.getFirstName(), profile.getLastName(), profile.getBirthDay(),
                profile.getEmailAddress(), profile.getAccount_id());
        return profileReponsitory.save(newProfile);
    }

    @Override
    public void deleteProfile(Integer id) throws NoSuchElementException {
        if (!profileReponsitory.exists(id))
            throw new NoSuchElementException("This profile does not exist");

        profileReponsitory.delete(id);
    }

    @Override
    public Profile updateProfile(Profile profile) throws NoSuchElementException {
        if (!profileReponsitory.exists(profile.getId()))
            throw new NoSuchElementException("This profile does not exist");

        Profile oldProfile = profileReponsitory.findOne(profile.getId());
        oldProfile.setFirstName(profile.getFirstName());
        oldProfile.setLastName(profile.getLastName());
        oldProfile.setBirthDay(profile.getBirthDay());
        oldProfile.setEmailAddress(profile.getEmailAddress());

        return profileReponsitory.save(oldProfile);
    }

    /////////////////////////////////////////////
    @Override
    public List<Account> selectAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account selectAccountByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public Account selectAccountById(int id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account updateAccount(Account account) throws NoSuchElementException {
        if (!accountRepository.exists(account.getId()))
            throw new NoSuchElementException("This account does not exist");

        Account oldAccount = accountRepository.findOne(account.getId());
        oldAccount.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        oldAccount.setEnabled(account.isEnabled());
        return accountRepository.save(oldAccount);
    }

    @Override
    public Account insertAccount(Account account) {
        Account newAccount = new Account(account.getUserName(), BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        newAccount.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));
        newAccount.setEnabled(true);
        return accountRepository.save(newAccount);
    }

    @Override
    public void deleteAccount(Integer id) throws NoSuchElementException {
        if (!accountRepository.exists(id))
            throw new NoSuchElementException("This acount does not exist");

        accountRepository.delete(id);
    }

    @Override
    public boolean existAccount(String userName) {
        return accountRepository.existsAccountByUserName(userName);
    }


}
