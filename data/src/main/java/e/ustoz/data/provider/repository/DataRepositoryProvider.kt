package e.ustoz.data.provider.repository

import e.ustoz.data.repository.state.StateRepository

interface DataRepositoryProvider {

    //val authenticateRepository: AuthenticateRepository

    val stateRepository:StateRepository

}