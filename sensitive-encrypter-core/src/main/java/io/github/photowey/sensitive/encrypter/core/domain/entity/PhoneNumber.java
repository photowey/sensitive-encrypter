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
package io.github.photowey.sensitive.encrypter.core.domain.entity;

import java.util.Objects;

/**
 * {@code PhoneNumber}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/02
 */
public class PhoneNumber implements EncryptableNumber {

    private static final long serialVersionUID = -3408834010914754931L;

    private String number;
    private String segmented;
    private String encrypted;

    public static PhoneNumberBuilder builder() {
        return new PhoneNumberBuilder();
    }

    public String getNumber() {
        return this.number;
    }

    public String getSegmented() {
        return this.segmented;
    }

    public String getEncrypted() {
        return this.encrypted;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSegmented(String segmented) {
        this.segmented = segmented;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(number, that.number) && Objects.equals(segmented, that.segmented) && Objects.equals(encrypted, that.encrypted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, segmented, encrypted);
    }

    @Override
    public String toString() {
        return "PhoneNumber(number=" + this.getNumber() + ", segmented=" + this.getSegmented() + ", encrypted=" + this.getEncrypted() + ")";
    }

    public PhoneNumber() {}

    public PhoneNumber(String number, String segmented, String encrypted) {
        this.number = number;
        this.segmented = segmented;
        this.encrypted = encrypted;
    }

    public static class PhoneNumberBuilder {

        private String number;
        private String segmented;
        private String encrypted;

        PhoneNumberBuilder() {}

        public PhoneNumberBuilder number(String number) {
            this.number = number;
            return this;
        }

        public PhoneNumberBuilder segmented(String segmented) {
            this.segmented = segmented;
            return this;
        }

        public PhoneNumberBuilder encrypted(String encrypted) {
            this.encrypted = encrypted;
            return this;
        }

        public PhoneNumber build() {
            return new PhoneNumber(this.number, this.segmented, this.encrypted);
        }

        @Override
        public String toString() {
            return "PhoneNumber.PhoneNumberBuilder(number=" + this.number + ", segmented=" + this.segmented + ", encrypted=" + this.encrypted + ")";
        }
    }
}
