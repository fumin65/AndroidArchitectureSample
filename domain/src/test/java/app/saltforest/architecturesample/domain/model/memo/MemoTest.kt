package app.saltforest.architecturesample.domain.model.memo

import io.mockk.mockk
import org.junit.jupiter.api.assertThrows
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class MemoTest : Spek({

    val memo by memoized {
        Memo(
            MemoId("test"),
            "title",
            "content",
            mockk()
        )
    }

    describe("changing title and content") {
        context("with empty title") {
            it("should throw IllegalArgumentException") {
                assertThrows<IllegalArgumentException> {
                    memo.changeTitleAndContent("", "content", mockk())
                }
            }
        }
        context("if title's length is over 100") {
            it("should throw IllegalArgumentException") {
                assertThrows<IllegalArgumentException> {
                    val title = (0..100).joinToString("") { "a" }
                    memo.changeTitleAndContent(title, "content", mockk())
                }
            }
        }
    }
})