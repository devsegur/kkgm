package me.evertonfs.infra.input

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.application.service.TableService
import org.springframework.beans.factory.annotation.Autowired

@Controller("/")
//@Secured(SecurityRule.IS_ANONYMOUS)
class RestController(@Autowired val service: TableService) {

    @Get
    fun rest(): MutableList<TableEntity> = service.listTables()
}