package me.evertonfs.infra.input

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import me.evertonfs.domain.model.Table
import me.evertonfs.domain.service.TableService
import reactor.core.publisher.Flux

@Controller("/")
@Secured(SecurityRule.IS_ANONYMOUS)
class RestController(var service: TableService) {

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun get(): List<Table?> {

        return service.listTables()
    }

    @Post(value = "save", consumes = [MediaType.APPLICATION_JSON])
    fun save(@Body table: Table): Table {
        return service.saveTable(table)
    }

    @Post(value = "save/all")
    fun saveAll(tables: Flux<Table>): Flux<Table> {
        return service.saveAllTables(tables)
    }

}