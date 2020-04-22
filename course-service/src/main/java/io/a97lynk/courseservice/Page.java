package io.a97lynk.courseservice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Page<T> {

    private int page;

    private int size;

    private List<T> content;

    @Builder
    public Page(int page, int size, List<T> content) {
        this.page = page;
        this.size = size;
        this.content = content;
    }

    public int getOffset() {
        return this.page * this.size;
    }
}
