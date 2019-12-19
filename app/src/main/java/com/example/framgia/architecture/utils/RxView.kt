package com.example.framgia.architecture.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.framgia.architecture.Constants
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

object RxView {

    fun RxView() {
        // No-Op
    }

    fun clicks(view: View): Observable<View> {
        val source: ObservableOnSubscribe<View> = ObservableOnSubscribe { emitter ->
            emitter.setCancellable {
                view.setOnClickListener(null)
                emitter.onComplete()
            }

            view.setOnClickListener {
                emitter.onNext(view)
            }
        }
        return Observable.create(source).throttleFirst(1, TimeUnit.SECONDS,
                AndroidSchedulers.mainThread())
    }

    fun search(editText: EditText): Observable<String> {
        val subject = PublishSubject.create<String>()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //No-Op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                subject.onNext(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                //No-Op
            }
        })
        return subject
    }

    fun input(editText: EditText): Observable<String> {
        val subject = PublishSubject.create<String>()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //No-Op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                subject.onNext(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                //No-Op
            }
        })
        return subject
                .debounce(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
    }
}