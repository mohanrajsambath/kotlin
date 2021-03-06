/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SECTIONS: constant-literals, boolean-literals
 * PARAGRAPH: 1
 * SENTENCE: [2] These are strong keywords which cannot be used as identifiers unless escaped.
 * NUMBER: 20
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the infixFunctionCall.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

infix fun Int.`true`(value: Int) = value > 100

infix fun Int.`false`(value: Int): Int {
    return value - 90
}

fun box(): String? {
    if (1 + 1 `true` -1001020) return null
    if (1 + 1 `false` 2004 `true` -0) return null

    return "OK"
}
