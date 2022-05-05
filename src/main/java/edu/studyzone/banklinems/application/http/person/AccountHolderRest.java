package edu.studyzone.banklinems.application.http.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderRequest;
import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.application.usecase.person.CreateAccountHolder;
import edu.studyzone.banklinems.application.usecase.person.FindAccountHolders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-holder/v1/persons")
public class AccountHolderRest {

    private final FindAccountHolders findAccountHolders;
    private final CreateAccountHolder createAccountHolder;

    public AccountHolderRest(FindAccountHolders findAccountHolders, CreateAccountHolder createAccountHolder) {
        this.findAccountHolders = findAccountHolders;
        this.createAccountHolder = createAccountHolder;
    }

    @GetMapping
    public ResponseEntity<List<AccountHolderResponse>> findAll(@RequestParam(required = false, name = "_offset") Integer offSet,
                                                               @RequestParam(required = false, name = "_limit") Integer limit) {

        var accountHolderResponses = findAccountHolders.findAll(offSet, limit);

        return ResponseEntity.ok(accountHolderResponses);
    }

    @PostMapping
    public ResponseEntity<AccountHolderResponse> create(@RequestBody AccountHolderRequest request) {

        var payload = createAccountHolder.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }
}
