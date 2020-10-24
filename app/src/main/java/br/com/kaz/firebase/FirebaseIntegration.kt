package br.com.kaz.firebase

import br.com.kaz.domain.EntityErrorResult
import br.com.kaz.domain.EntityResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class FirebaseIntegration(private val firebaseAuth: FirebaseAuth) {

    fun getCurrentlyUserSignedIn(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun createUser(
        email: String,
        password: String,
        action: (entityResult: EntityResult<Unit>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    action(EntityResult.Success(Unit))
                } else {
                    action(EntityResult.Error(handleException(task)))
                }
            }
    }

    fun loginWithUser(
        email: String,
        password: String,
        action: (entityResult: EntityResult<Unit>) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    action(EntityResult.Success(Unit))
                } else {
                    action(EntityResult.Error(handleException(task)))
                }
            }
    }

    fun singOutUser() {
        firebaseAuth.signOut()
    }

    private fun handleException(task: Task<AuthResult>): EntityErrorResult {
        return when (task.exception!!) {
            is FirebaseAuthWeakPasswordException -> EntityErrorResult.WeakPassword
            is FirebaseAuthInvalidCredentialsException -> EntityErrorResult.InvalidCredentials
            is FirebaseAuthInvalidUserException -> EntityErrorResult.InvalidUser
            is FirebaseAuthUserCollisionException -> EntityErrorResult.UserCollision
            else -> EntityErrorResult.OtherException
        }
    }
}