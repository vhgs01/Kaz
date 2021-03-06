package br.com.kaz.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kaz.R
import br.com.kaz.contract.ChecklistContract
import br.com.kaz.util.JsonManipulation
import br.com.kaz.view.adapter.ChecklistAdapter
import com.google.android.youtube.player.YouTubeBaseActivity
import kotlinx.android.synthetic.main.activity_checklist.*

class ChecklistActivity : YouTubeBaseActivity(), ChecklistContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        val stepPosition = intent.getIntExtra("stepPosition", 99)

        setListeners()
        configureAdapter(modulePosition, stepPosition)
    }

    override fun configureAdapter(modulePosition: Int, stepPosition: Int) {
        val recyclerView = checklistList
        recyclerView.adapter =
            JsonManipulation.getCourseKaz(applicationContext)
                ?.let {
                    ChecklistAdapter(
                        it,
                        this,
                        modulePosition,
                        stepPosition,
                        checklistYouTubePlayerView
                    )
                }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun setListeners() {
        checklistBack.setOnClickListener {
            finish()
        }
    }
}