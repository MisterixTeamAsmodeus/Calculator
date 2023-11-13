package org.example.parser.api;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфес для генераци regex для проверки валидности выражения и разделения его на токены
 */
public interface RegexCompiler {
    /**
     * Получения regex для проверки валидности выражения
     * @return
     */
    @NotNull
    String getRegex();

    /**
     * Получения списка символов которые являтся разделителями в выражении
     * @return
     */
    @NotNull
    String getDelim();
}
