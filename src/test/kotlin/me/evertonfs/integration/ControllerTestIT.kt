//package me.evertonfs.integration
//
//import io.kotest.core.config.AbstractProjectConfig
//import io.kotest.core.spec.style.AnnotationSpec
//import io.kotest.matchers.shouldBe
//import io.micronaut.http.client.HttpClient
//import io.micronaut.http.client.annotation.Client
//import io.micronaut.test.extensions.kotest.MicronautKotestExtension
//import io.micronaut.test.extensions.kotest.annotation.MicronautTest
//
//object ProjectConfig : AbstractProjectConfig() {
//    override fun listeners() = listOf(MicronautKotestExtension)
//    override fun extensions() = listOf(MicronautKotestExtension)
//}
//
//@MicronautTest
//open class ControllerTestIT(
//    @Client("/")
//    val client: HttpClient
//) : AnnotationSpec() {
//
//    @Test
//    fun test() {
//        val result = client.toBlocking().exchange<String>("/")
//        result shouldBe 8
//    }
//}