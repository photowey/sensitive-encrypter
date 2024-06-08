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
package io.github.photowey.sensitive.encrypter.api.encrypter;

import io.github.photowey.sensitive.encrypter.core.domain.model.Padding;
import io.github.photowey.sensitive.encrypter.core.domain.model.Spliter;

import java.util.List;
import java.util.function.Function;

/**
 * {@code PhoneNumberEncrypter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/02
 */
public interface PhoneNumberEncrypter extends NumberEncrypter {

    default int phoneNumberLength() {
        return 11;
    }

    default int splitSegmentLength() {
        return 3;
    }

    // ----------------------------------------------------------------

    default String tryEncrypt(String phoneNumber) {
        return this.tryEncrypt(phoneNumber, this::encrypt);
    }

    default String tryEncrypt(String phoneNumber, Function<String, String> fx) {
        StringBuffer buffer = new StringBuffer();
        List<String> segments = this.split(phoneNumber);
        segments.forEach(member -> {
            String segment = fx.apply(member);
            buffer.append(segment);
        });

        return buffer.toString();
    }

    default String tryFuzzEncrypt(String input) {
        StringBuffer buffer = new StringBuffer();
        String padded = this.padding(input);

        List<String> segments = this.splitRelax(padded);
        segments.forEach(member -> {
            String segment = this.encrypt(member);
            buffer.append(segment);
        });

        return buffer.toString();
    }

    // ----------------------------------------------------------------

    default List<String> split(String phoneNumber) {
        this.check(phoneNumber, this.phoneNumberLength());
        int segmentLength = this.splitSegmentLength();

        Spliter spliter = Spliter.builder()
                .sensitive(phoneNumber)
                .length(segmentLength)
                .build();

        return spliter.split();
    }

    default List<String> splitRelax(String phoneNumber) {
        this.checkRelax(phoneNumber);
        int segmentLength = this.splitSegmentLength();

        Spliter spliter = Spliter.builder()
                .sensitive(phoneNumber)
                .length(segmentLength)
                .build();

        return spliter.split();
    }

    default String padding(String input) {
        int length = input.length();
        int segmentLength = this.splitSegmentLength();
        int remainder = length % segmentLength;

        if (remainder == 0) {
            return input;
        }

        int paddingLength = segmentLength - remainder;

        Padding padding = Padding.builder()
                .segment(input)
                .delta(paddingLength)
                .pad(DEFAULT_PADDING_SEQUENCE)
                .build();

        return padding.rightPadding();
    }
}
