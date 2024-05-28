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
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * {@code Padding}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/05/28
 */
public class Padding implements Serializable {

    private static final long serialVersionUID = 3053683331936418403L;

    private String segment;
    private Integer delta;
    private String pad;

    // ----------------------------------------------------------------

    public static PaddingBuilder builder() {
        return new PaddingBuilder();
    }

    public String getSegment() {
        return this.segment;
    }

    public Integer getDelta() {
        return this.delta;
    }

    public String getPad() {
        return this.pad;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Padding padding = (Padding) o;
        return Objects.equals(segment, padding.segment) && Objects.equals(delta, padding.delta) && Objects.equals(pad, padding.pad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, delta, pad);
    }

    public Padding() {}

    public Padding(String segment, Integer delta, String pad) {
        this.segment = segment;
        this.delta = delta;
        this.pad = pad;
    }

    public static class PaddingBuilder {

        private String segment;
        private Integer delta;
        private String pad;

        PaddingBuilder() {}

        public PaddingBuilder segment(String segment) {
            this.segment = segment;
            return this;
        }

        public PaddingBuilder delta(Integer delta) {
            this.delta = delta;
            return this;
        }

        public PaddingBuilder pad(String pad) {
            this.pad = pad;
            return this;
        }

        public Padding build() {
            return new Padding(this.segment, this.delta, this.pad);
        }

        @Override
        public String toString() {
            return "Padding.PaddingBuilder(segment=" + this.segment + ", delta=" + this.delta + ", pad=" + this.pad + ")";
        }
    }

    // ----------------------------------------------------------------

    public String segment() {
        return segment;
    }

    public Integer delta() {
        return delta;
    }

    public String pad() {
        return pad;
    }

    // ----------------------------------------------------------------

    public String leftPadding() {
        return this.padding((x, y) -> {}, StringBuilder::append);
    }

    public String rightPadding() {
        return this.padding(StringBuilder::append, (x, y) -> {});
    }

    public String padding(BiConsumer<StringBuilder, String> leftFx, BiConsumer<StringBuilder, String> rightFx) {
        this.check();
        StringBuilder buf = new StringBuilder();
        leftFx.accept(buf, this.segment());
        for (int i = 0; i < Math.abs(this.delta()); i++) {
            buf.append(this.pad());
        }
        rightFx.accept(buf, this.segment());

        return buf.toString();
    }

    private void check() {
        if (null == this.segment() || this.segment().isEmpty()) {
            throw new IllegalArgumentException("segment field is invalid");
        }

        if (null == this.pad() || this.pad().isEmpty()) {
            throw new IllegalArgumentException("Pad field is invalid");
        }
    }
}