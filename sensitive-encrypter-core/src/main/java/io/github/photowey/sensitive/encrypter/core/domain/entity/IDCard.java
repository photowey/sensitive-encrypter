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
 * {@code IDCard}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/02
 */
public class IDCard implements EncryptableNumber {

    private static final long serialVersionUID = 1636238157116261270L;

    private String number;
    private String segmented;
    private String encrypted;

    public static IDCardBuilder builder() {
        return new IDCardBuilder();
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
        IDCard idCard = (IDCard) o;
        return Objects.equals(number, idCard.number) && Objects.equals(segmented, idCard.segmented) && Objects.equals(encrypted, idCard.encrypted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, segmented, encrypted);
    }

    @Override
    public String toString() {
        return "IDCard(number=" + this.getNumber() + ", segmented=" + this.getSegmented() + ", encrypted=" + this.getEncrypted() + ")";
    }

    public IDCard() {}

    public IDCard(String number, String segmented, String encrypted) {
        this.number = number;
        this.segmented = segmented;
        this.encrypted = encrypted;
    }

    public static class IDCardBuilder {

        private String number;
        private String segmented;
        private String encrypted;

        IDCardBuilder() {}

        public IDCardBuilder number(String number) {
            this.number = number;
            return this;
        }

        public IDCardBuilder segmented(String segmented) {
            this.segmented = segmented;
            return this;
        }

        public IDCardBuilder encrypted(String encrypted) {
            this.encrypted = encrypted;
            return this;
        }

        public IDCard build() {
            return new IDCard(this.number, this.segmented, this.encrypted);
        }

        @Override
        public String toString() {
            return "IDCard.IDCardBuilder(number=" + this.number + ", segmented=" + this.segmented + ", encrypted=" + this.encrypted + ")";
        }
    }
}
