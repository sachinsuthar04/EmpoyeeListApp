package com.example.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.network.model.MineUserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class EmployeeViewModel : BaseViewModel() {

    private lateinit var disposable: Disposable

    val employeeResponse: MutableLiveData<MineUserEntity.VoEmployeedata> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        if (::disposable.isInitialized) disposable.dispose()
    }

    fun getEmployee() {
        disposable =
            apiInterface.getemployeelist().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                    { result -> onRetrievePostListRecordSuccess(result) },
                    { result -> onRetrievePostListError(result) }
                )
    }

    private fun onRetrievePostListRecordSuccess(response: MineUserEntity.VoEmployeedata) {
        employeeResponse.value = response
    }

}