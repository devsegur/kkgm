package me.evertonfs.application.config

import io.micronaut.context.annotation.Factory
import me.evertonfs.domain.repository.ColumnRepository
import me.evertonfs.domain.repository.TableRepository
import me.evertonfs.domain.service.TableService
import javax.inject.Inject
import javax.inject.Singleton

@Factory
class ServiceBean {
    @Singleton
    @Inject
    fun getService(repositoryImpl: TableRepository, repository: ColumnRepository): TableService {
        return TableService(repository = repositoryImpl, repo = repository)
    }
}