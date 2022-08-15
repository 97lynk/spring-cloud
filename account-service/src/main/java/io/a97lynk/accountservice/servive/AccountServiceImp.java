package io.a97lynk.accountservice.servive;

import io.a97lynk.accountservice.model.Account;
import io.a97lynk.accountservice.model.Profile;
import io.a97lynk.accountservice.reponsitory.AccountRepository;
import io.a97lynk.accountservice.reponsitory.ProfileRepository;
import io.a97lynk.accountservice.reponsitory.RoleRepository;
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
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Profile> selectProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile selectProfileByUserName(String userName) {
        return accountRepository.findByUserName(userName).getProfile();
    }

    @Override
    public Profile selectProfileById(int id) {
        return profileRepository.findOne(id);
    }

    @Override
    public Profile insertProfile(Profile profile) {
        Profile newProfile = new Profile(profile.getFirstName(), profile.getLastName(), profile.getBirthDay(),
                profile.getEmailAddress(), profile.getAccount_id());
        return profileRepository.save(newProfile);
    }

    @Override
    public void deleteProfile(Integer id) throws NoSuchElementException {
        if (!profileRepository.exists(id))
            throw new NoSuchElementException("This profile does not exist");

        profileRepository.delete(id);
    }

    @Override
    public Profile updateProfile(Profile profile) throws NoSuchElementException {
        if (!profileRepository.exists(profile.getId()))
            throw new NoSuchElementException("This profile does not exist");

        Profile oldProfile = profileRepository.findOne(profile.getId());
        oldProfile.setFirstName(profile.getFirstName());
        oldProfile.setLastName(profile.getLastName());
        oldProfile.setBirthDay(profile.getBirthDay());
        oldProfile.setEmailAddress(profile.getEmailAddress());

        return profileRepository.save(oldProfile);
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
