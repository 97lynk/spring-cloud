package io.a97lynk.accountservice.rest;

import io.a97lynk.accountservice.model.Account;
import io.a97lynk.accountservice.model.Profile;
import io.a97lynk.accountservice.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author 97lynk
 */
@RestController
@RequestMapping("/account")
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = Logger.getLogger(AccountAPI.class.getName());

    AbstractMap.SimpleEntry successMessage = new AbstractMap.SimpleEntry<>("message", "success");

    /**
     * lấy hết danh sách account
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("#oauth2.hasAnyScope('READ')")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.selectAccounts());
    }

    /**
     * Thêm account mới - hiện thực chức năng đăng kí
     *
     * @param account
     */
    @PostMapping
    @PreAuthorize("#oauth2.hasAnyScope('READ')")
    public ResponseEntity<?> addNewAccount(@RequestBody Account account) {
        accountService.insertAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    /**
     * Thêm account mới - hiện thực chức năng đăng kí
     *
     * @param account
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registrationAccount(@RequestBody Account account) {
        HashMap<String, String> res = new HashMap<>();
        if(accountService.existAccount(account.getUserName())){
            res.put("status", "error");
            res.put("message", "Số điện thoại này đã đăng kí.");
            return ResponseEntity.ok(res);
        }

        Account newAccount = accountService.insertAccount(account);
        Profile newProfile =  account.getProfile();
        newProfile.setAccount_id(newAccount.getId());
        accountService.insertProfile(newProfile);

        res.put("status", "success");
        res.put("message", "Tài khoản đăng kí thành công");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /**
     * Thay đổi 1 account cụ thể - hiện thực chức năng đổi mật khẩu
     *
     * @param account profile có id cũ, có các thuộc tính mới sẽ cập nhật
     */
    @PutMapping("/{id}")
    @PreAuthorize("#oauth2.hasAnyScope('READ')")
    public ResponseEntity<?> changeAccount(@PathVariable("id") int accountId, @RequestBody Account account) {
        account.setId(accountId);
        accountService.updateAccount(account);
        return ResponseEntity.ok(successMessage);
    }

    /**
     * Xóa 1 account cụ thể
     *
     * @param accountId
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("#oauth2.hasAnyScope('READ')")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok(successMessage);

    }

}
