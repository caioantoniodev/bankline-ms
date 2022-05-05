package edu.studyzone.banklinems.application.http.charge;

import edu.studyzone.banklinems.application.dto.charge.ChargeRequest;
import edu.studyzone.banklinems.application.dto.charge.ChargeResponse;
import edu.studyzone.banklinems.application.usecase.charge.CreateCharge;
import edu.studyzone.banklinems.application.usecase.charge.FindTransactions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charge/v1/transactions")
public class ChargeRest {

    private final FindTransactions findTransactions;
    private final CreateCharge createCharge;

    public ChargeRest(FindTransactions findTransactions, CreateCharge createCharge) {
        this.findTransactions = findTransactions;
        this.createCharge = createCharge;
    }

    @GetMapping
    public ResponseEntity<List<ChargeResponse>> findAll(@RequestParam(required = false, name = "_offset") Integer offSet,
                                                        @RequestParam(required = false, name = "_limit") Integer limit) {

        var chargeResponseList = findTransactions.findAll(offSet, limit);

        return ResponseEntity.ok(chargeResponseList);
    }

    @PostMapping
    public ResponseEntity<ChargeResponse> charge(@RequestBody ChargeRequest request) {

        var payload = createCharge.charge(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }
}
