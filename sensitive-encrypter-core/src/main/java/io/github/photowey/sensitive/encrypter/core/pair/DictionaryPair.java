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
package io.github.photowey.sensitive.encrypter.core.pair;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@code DictionaryPair}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/08
 */
public class DictionaryPair implements Serializable {

    private static final long serialVersionUID = 5291980910411096631L;

    private String segment;
    private String encrypted;

    public static DictionaryPairBuilder builder() {
        return new DictionaryPairBuilder();
    }

    public String getSegment() {
        return this.segment;
    }

    public String getEncrypted() {
        return this.encrypted;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String segment() {
        return this.segment;
    }

    public String encrypted() {
        return this.encrypted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DictionaryPair that = (DictionaryPair) o;
        return Objects.equals(segment, that.segment) && Objects.equals(encrypted, that.encrypted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, encrypted);
    }

    @Override
    public String toString() {
        return "DictionaryPair(segment=" + this.getSegment() + ", encrypted=" + this.getEncrypted() + ")";
    }

    public DictionaryPair() {}

    public DictionaryPair(String segment, String encrypted) {
        this.segment = segment;
        this.encrypted = encrypted;
    }

    public static class DictionaryPairBuilder {

        private String segment;
        private String encrypted;

        DictionaryPairBuilder() {}

        public DictionaryPairBuilder segment(String segment) {
            this.segment = segment;
            return this;
        }

        public DictionaryPairBuilder encrypted(String encrypted) {
            this.encrypted = encrypted;
            return this;
        }

        public DictionaryPair build() {
            return new DictionaryPair(this.segment, this.encrypted);
        }

        @Override
        public String toString() {
            return "DictionaryPair.DictionaryPairBuilder(segment=" + this.segment + ", encrypted=" + this.encrypted + ")";
        }
    }
}