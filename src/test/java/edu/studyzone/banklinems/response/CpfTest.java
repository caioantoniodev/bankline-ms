package edu.studyzone.banklinems.response;

import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.domain.person.AccountHolder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpfTest {

    private static final String CPF_FORMATTED = "412.219.800/33";

    private static final String CPF_WITHIN_SPECIAL_CHARACTERS = "41221980033";

    @Test
    void shouldReturnCpfWithinSpecialsCharacters() {
        AccountHolder accountHolder = AccountHolder.builder().withCpf(CPF_FORMATTED).build();

        assertEquals(CPF_WITHIN_SPECIAL_CHARACTERS, accountHolder.getCpf());
    }

    @Test
    void shouldReturnCpfFormatWhenNumberCorrect() {
        AccountHolderResponse accountHolderResponse = new AccountHolderResponse();
        accountHolderResponse.setCpf(CPF_WITHIN_SPECIAL_CHARACTERS);

        assertEquals(CPF_FORMATTED, accountHolderResponse.getCpf());
    }
}
