package br.com.kaz.firebase

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*


object FirebaseIntegration {

    private var firebaseAuth: FirebaseAuth? = null

    fun initializeFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun getCurrentlyUserSignedIn(): FirebaseUser? {
        return firebaseAuth?.currentUser
    }

    fun createUser(context: Context, email: String, password: String, action: ( ) -> Unit) {
        firebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    action()
                } else {
                    Toast.makeText(context, "Falha ao criar usuário.", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun loginWithUser(context: Context, email: String, password: String) {
        firebaseAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val user = firebaseAuth?.currentUser
                } else {
                    Toast.makeText(context, "Falha na autenticação.", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun singOutUser() {
        firebaseAuth?.signOut()
    }
}