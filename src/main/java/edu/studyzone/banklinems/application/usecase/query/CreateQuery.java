package edu.studyzone.banklinems.application.usecase.query;

import edu.studyzone.banklinems.domain.query.OffsetLimitPageable;
import org.springframework.stereotype.Component;

@Component
public class CreateQuery {

    public OffsetLimitPageable getOffsetLimitPageable(Integer offset, Integer limit) {

        if (offset != null && limit == null)
            return new OffsetLimitPageable(offset, 50);

        if (limit != null && offset == null)
            return new OffsetLimitPageable(0, limit);

        if (limit != null && offset != null)
            return new OffsetLimitPageable(offset, limit);

        return new OffsetLimitPageable(0, 50);
    }
}
