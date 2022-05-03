package edu.studyzone.banklinems.domain.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class OffsetLimitPageable extends PageRequest {

    private final int offset;

    public OffsetLimitPageable(int offset, int limit) {
        super(offset, limit, Sort.unsorted());
        this.offset = offset;
    }

    @Override
    public long getOffset() {
        return this.offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OffsetLimitPageable that = (OffsetLimitPageable) o;
        return offset == that.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), offset);
    }
}
