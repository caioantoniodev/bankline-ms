package edu.studyzone.banklinems.application.http.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.application.usecase.person.FindAccountHolders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account-holder/v1/persons")
public class AccountHolderRest {

    private final FindAccountHolders findAccountHolders;

    public AccountHolderRest(FindAccountHolders findAccountHolders) {
        this.findAccountHolders = findAccountHolders;
    }

    @GetMapping
    public ResponseEntity<List<AccountHolderResponse>> findAll(@RequestParam(required = false, name = "_offset") Integer offSet,
                                                               @RequestParam(required = false, name = "_limit") Integer limit) {

        var accountHolderResponses = findAccountHolders.findAll(offSet, limit);

        return ResponseEntity.ok(accountHolderResponses);
    }
}
