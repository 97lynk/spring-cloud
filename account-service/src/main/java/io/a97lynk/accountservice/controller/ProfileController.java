package io.a97lynk.accountservice.rest;

import io.a97lynk.accountservice.model.Profile;
import io.a97lynk.accountservice.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author 97lynk
 */
@RestController
@RequestMapping("/profile")
@PreAuthorize("#oauth2.hasScope('READ')")
public class ProfileAPI {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = Logger.getLogger(ProfileAPI.class.getName());

    AbstractMap.SimpleEntry successMessage = new AbstractMap.SimpleEntry<>("message", "success");

    /**
     * Lấy danh sách profiles
     *
     * @return Profie
     * @scope
     * @privilege
     */
    @GetMapping
    public ResponseEntity<List<Profile> > getAllProfiles() {
        return ResponseEntity.ok(accountService.selectProfiles());
    }

    /**
     * Lấy 1 profile theo username (chứng thực)
     *
     * @param auth
     * @return Profie
     * @scope
     * @privilege
     */
    @GetMapping("/owner")
    public ResponseEntity<Profile> getOwnerProfile(OAuth2Authentication auth) {
        return ResponseEntity.ok(accountService.selectProfileByUserName(auth.getName()));
    }

    /**
     * Lấy profile theo id
     * @param profileId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") int profileId) {
        return ResponseEntity.ok(accountService.selectProfileById(profileId));
    }

    /**
     * Thêm 1 profile
     *
     * @param profile profile cần thêm
     */
    @PostMapping
    public ResponseEntity<?> addNewProfile(@RequestBody Profile profile) {
        accountService.insertProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    /**
     * Thay đổi 1 profile cụ thể
     *
     * @param profile profile có id cũ, có các thuộc tính mới sẽ cập nhật
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> changeProfile(@PathVariable("id") int profileId, @RequestBody Profile profile) {
        logger.info(profile.getBirthDay().toString());
        profile.setId(profileId);
        accountService.updateProfile(profile);
        return ResponseEntity.ok(successMessage);
    }


    /**
     * Xóa 1 profile cụ thể
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable int id) {
        accountService.deleteProfile(id);
        return ResponseEntity.ok(successMessage);
    }


    //@PreAuthorize("#oauth2.hasScope('CUST_INFO') and #oauth2.hasScope('READ')")
    //@PreAuthorize("#contact.name == authentication.name")
    //@PostFilter("filterObject.account.userName == authentication.name")
//    @PreAuthorize("#oauth2.hasScope('READ')")
//    @GetMapping("/user")
//    public OAuth2Authentication getExtraInfo(OAuth2Authentication auth) {
//        return auth;
//    }
}
