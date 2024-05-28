/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.sensitive.encrypter.core.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@code Spliter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/05/28
 */
public class Spliter implements Serializable {

    private static final long serialVersionUID = -7068588255642535454L;
    private static final int DEFAULT_SEGMENT_LENGTH = 3;
    private static final String DEFAULT_SEGMENT_PADDING_CHAR = "0";

    private String sensitive;
    private Integer length;

    public static SpliterBuilder builder() {
        return new SpliterBuilder();
    }

    public String getSensitive() {
        return this.sensitive;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setSensitive(String sensitive) {
        this.sensitive = sensitive;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    // ----------------------------------------------------------------

    public String sensitive() {
        return this.sensitive;
    }

    public Integer length() {
        return this.length;
    }

    // ----------------------------------------------------------------

    public Integer determineSegmentLength() {
        if (Objects.isNull(this.length)) {
            return DEFAULT_SEGMENT_LENGTH;
        }

        return this.length;
    }

    // ----------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spliter spliter = (Spliter) o;
        return Objects.equals(sensitive, spliter.sensitive) && Objects.equals(length, spliter.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensitive, length);
    }

    // ----------------------------------------------------------------

    public Spliter() {}

    public Spliter(String sensitive, Integer length) {
        this.sensitive = sensitive;
        this.length = length;
    }

    // ----------------------------------------------------------------

    public static class SpliterBuilder {

        private String sensitive;
        private Integer length;

        SpliterBuilder() {}

        public SpliterBuilder sensitive(String sensitive) {
            this.sensitive = sensitive;
            return this;
        }

        public SpliterBuilder length(Integer length) {
            this.length = length;
            return this;
        }

        public Spliter build() {
            return new Spliter(this.sensitive, this.length);
        }

        @Override
        public String toString() {
            return "Spliter.SpliterBuilder(sensitive=" + this.sensitive + ", length=" + this.length + ")";
        }
    }

    // ----------------------------------------------------------------

    public List<String> split() {
        this.check();
        Integer segmentLength = this.determineSegmentLength();

        List<String> segments = new ArrayList<>();
        int delta = this.sensitive.length() - segmentLength;
        if (delta < 0) {
            String padded = this.padding(this.sensitive(), delta);
            segments.add(padded);

            return segments;
        }

        for (int i = 0; i <= delta; i++) {
            String member = this.sensitive.substring(i, i + segmentLength);
            segments.add(member);
        }

        return segments;
    }

    private void check() {
        if (null == this.sensitive() || this.sensitive().isEmpty()) {
            throw new IllegalArgumentException("Sensitive field is invalid");
        }
    }

    private String padding(String segment, int delta) {
        Padding padding = Padding.builder()
                .segment(segment)
                .delta(delta)
                .pad(DEFAULT_SEGMENT_PADDING_CHAR)
                .build();

        return padding.rightPadding();
    }
}
